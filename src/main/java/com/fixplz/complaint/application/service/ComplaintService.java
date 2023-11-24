package com.fixplz.complaint.application.service;

import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
import com.fixplz.complaint.application.dto.request.UpdateProcessingStatusRequest;
import com.fixplz.complaint.application.dto.response.*;
import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.complaint.domain.aggregate.vo.FacilityNoVO;
import com.fixplz.complaint.domain.aggregate.vo.FilterCategory;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import com.fixplz.complaint.domain.repository.ComplaintRepository;
import com.fixplz.complaint.infra.service.ComplaintInfraService;
import com.fixplz.complaint.infra.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintInfraService complaintInfraService;
    private final S3Service s3Service;

    // 민원 생성
    @Transactional
    public CreateComplaintResponse createComplaint(CreateComplaintRequest request) throws IOException {

        FilterCategory filterCategory = FilterCategory.fromInt(request.filterCategory());

        FacilityNoVO facilityNoVO = new FacilityNoVO(request.facilityNo());

        String complaintImageUrl = "none";

        // 이미지 처리
        if (request.image() != null) {
            complaintImageUrl = s3Service.uploadComplaintImage(request.image(), request.phoneNumber());
        }

        Complaint complaint = new Complaint.Builder()
                .complaintContent(request.complaintContent())
                .phoneNumber(request.phoneNumber())
                .date(new Date())
                .filterCategory(filterCategory)
                .facilityNoVO(facilityNoVO)
                .processingStatus(ProcessingStatus.TODO)
                .complaintImage(complaintImageUrl)
                .build();

        Complaint createdComplaint = complaintRepository.save(complaint);

        CreateComplaintResponse response = new CreateComplaintResponse(createdComplaint.getComplaintNo(),
                createdComplaint.getComplaintContent());


        return response;
    }

    // 민원 업데이트 : 처리 상태
    @Transactional
    public UpdateProcessingStatusResponse updateProcessingStatus(UpdateProcessingStatusRequest request, Long complaintNo) {

        Complaint findComplaint = complaintRepository.findById(complaintNo)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 민원이 존재하지않습니다"));

        ProcessingStatus processingStatus = ProcessingStatus.fromInt(request.processingStatusInt());

        findComplaint.updateProcessingStatus(processingStatus);

        complaintRepository.updateComplaint(findComplaint.getComplaintNo(), processingStatus);

        UpdateProcessingStatusResponse response = new UpdateProcessingStatusResponse(findComplaint.getComplaintNo(),
                findComplaint.getProcessingStatus().getText(),
                findComplaint.getProcessingStatus().ordinal());

        return response;
    }

    // 민원 전체 조회 : 페이징
    @Transactional(readOnly = true)
    public Page<GetComplaintListResponse> getComplaintList(Pageable pageable) {

        Page<GetComplaintListResponse> response = complaintRepository.findAll(pageable).map(GetComplaintListResponse::toDto);

        return response;
    }

    // 민원 상세 조회
    @Transactional(readOnly = true)
    public GetComplaintResponse getComplaint(Long complaintNo) {

        GetComplaintResponse tempResponse = complaintRepository.findById(complaintNo).map(GetComplaintResponse::toDto)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 민원이 존재하지않습니다."));

        GetFacilityInfo facilityInfo = complaintInfraService.getFacilityInfo(tempResponse.facilityNo());

        GetComplaintResponse response = GetComplaintResponse.getFacilityInfo(tempResponse, facilityInfo);

        return response;
    }

    // 민원 처리 페이지 진입 시 : 시설물 & 필터 카테고리 조회
    @Transactional(readOnly = true)
    public GetComplaintPageInfo getComplaintPageInfoWithFacility(Long facilityNo) {

        GetFacilityInfo facilityInfo = complaintInfraService.getFacilityInfo(facilityNo);
        List<Map<Integer, String>> filterCategoryList = Stream.of(FilterCategory.values())
                .map(filterCategory -> Map.of(filterCategory.ordinal(), filterCategory.getText()))
                .toList();

        GetComplaintPageInfo response = new GetComplaintPageInfo(facilityInfo, filterCategoryList);

        return response;
    }
}
