package com.fixplz.facility.application.service;

import com.fixplz.facility.application.dto.request.FacilityRequest;
import com.fixplz.facility.application.dto.response.FacilityResponse;
import com.fixplz.facility.domain.aggregate.entity.Facility;
import com.fixplz.facility.domain.aggregate.enumtype.Department;
import com.fixplz.facility.domain.aggregate.enumtype.FacilityCategory;
import com.fixplz.facility.domain.aggregate.vo.CoordinateVO;
import com.fixplz.facility.domain.repository.FacilityRepository;
import com.fixplz.facility.domain.service.QRApiDomainService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final QRApiDomainService qrApiDomainService;

    public FacilityResponse registFacility(FacilityRequest request) throws IOException {
        try {

            FacilityCategory facilityCategory = FacilityCategory.fromCategoryName(
                    request.facilityCategory()
            );
            Department department = Department.fromDepartmentNameAndNumber(
                    request.departmentName(),
                    request.departmentNumber()
            );

            Facility facility = new Facility.Builder()
                    .facilityCategory(facilityCategory)
                    .facilityName(request.facilityName())
                    .administrativeDong(request.administrativeDong())
                    .facilityAddress(request.facilityAddress())
                    .department(department)
                    .coordinateVO(new CoordinateVO(
                            request.latitude(),
                            request.longitude()
                    ))
                    .build();

            Facility result = facilityRepository.save(facility);

            byte[] qrByte = qrApiDomainService.createQRcode(result.getFacilityNo());
            String qrImageUrl = qrApiDomainService.uploadQRcodeToS3(qrByte, result.getFacilityName());

            result.updateQRUrl(qrImageUrl);

            return FacilityResponse.of(
                    result.getFacilityNo(),
                    result.getFacilityCategory().getCategoryName(),
                    result.getFacilityName(),
                    result.getAdministrativeDong(),
                    result.getFacilityAddress(),
                    result.getDepartment().getDepartmentName(),
                    result.getDepartment().getDepartmentNumber(),
                    result.getFacilityQRUrl(),
                    result.getCoordinateVO().getLatitude(),
                    result.getCoordinateVO().getLongitude()
            );
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("중복된 시설물이 존재합니다.");
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
