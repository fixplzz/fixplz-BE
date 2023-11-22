package com.fixplz.facility.application.service;

import com.fixplz.common.handler.exception.NoSuchDataException;
import com.fixplz.facility.application.dto.request.FacilityRequest;
import com.fixplz.facility.application.dto.response.FacilityResponse;
import com.fixplz.facility.domain.aggregate.entity.Facility;
import com.fixplz.facility.domain.aggregate.vo.CoordinateVO;
import com.fixplz.facility.domain.repository.FacilityRepository;
import com.fixplz.facility.domain.service.QRApiDomainService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final QRApiDomainService qrApiDomainService;

    public Page<FacilityResponse> getFacilitiesList(Pageable pageable) {

        return facilityRepository.findAllFacilityResponse(pageable)
                .orElseThrow(() -> new NoSuchDataException("일치하는 결과가 없습니다."));
    }

    public FacilityResponse getFacilityByFacilityNo(Long facilityNo) {
        Facility facility = facilityRepository.findById(facilityNo)
                .orElseThrow(() -> new NoSuchDataException("일치하는 결과가 없습니다."));
        return FacilityResponse.of(facility);
    }

    public FacilityResponse registFacility(FacilityRequest request) throws IOException {
        try {

//            FacilityCategory facilityCategory = FacilityCategory.fromCategoryName(
//                    request.facilityCategory()
//            );
//            Department department = Department.fromDepartmentNameAndNumber(
//                    request.departmentName(),
//                    request.departmentNumber()
//            );

            Facility facility =
                    new Facility.Builder()
                            .facilityCategory(request.facilityCategory())
                            .facilityName(request.facilityName())
                            .administrativeDong(request.administrativeDong())
                            .facilityAddress(request.facilityAddress())
                            .departmentName(request.departmentName())
                            .departmentNumber(request.departmentNumber())
                            .coordinateVO(new CoordinateVO(
                                    request.latitude(),
                                    request.longitude()
                            ))
                            .build();

            Facility result = facilityRepository.save(facility);

            byte[] qrByte = qrApiDomainService.createQRcode(result.getFacilityNo());
            String qrImageUrl = qrApiDomainService.uploadQRcodeToS3(qrByte, result.getFacilityName());

            result.updateQRUrl(qrImageUrl);

            return FacilityResponse.of(result);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("중복된 시설물이 존재합니다.");
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }


}
