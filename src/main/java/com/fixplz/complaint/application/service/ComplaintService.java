package com.fixplz.complaint.application.service;

import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
import com.fixplz.complaint.application.dto.request.UpdateProcessingStatusRequest;
import com.fixplz.complaint.application.dto.response.CreateComplaintResponse;
import com.fixplz.complaint.application.dto.response.GetComplaintListResponse;
import com.fixplz.complaint.application.dto.response.GetComplaintResponse;
import com.fixplz.complaint.application.dto.response.UpdateProcessingStatusResponse;
import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.complaint.domain.aggregate.vo.FacilityNoVO;
import com.fixplz.complaint.domain.aggregate.vo.FilterCategory;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import com.fixplz.complaint.domain.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    // 민원 생성
    public CreateComplaintResponse createComplaint(CreateComplaintRequest request) {

        FilterCategory filterCategory = FilterCategory.fromInt(request.getFilterCategory());

        FacilityNoVO facilityNoVO = new FacilityNoVO(request.getFacilityNo());

        // 이미지 처리?

        Complaint complaint = new Complaint.Builder()
                .complaintContent(request.getComplaintContent())
                .phoneNumber(request.getPhoneNumber())
                .filterCategory(filterCategory)
                .facilityNoVO(facilityNoVO)
                .build();

        Complaint createdComplaint = complaintRepository.save(complaint);

        CreateComplaintResponse response = new CreateComplaintResponse(createdComplaint.getComplaintNo(),
                                                                       createdComplaint.getComplaintContent());


        // 알림톡 보내기

        return response;
    }

    // 민원 업데이트 : 처리 상태
    public UpdateProcessingStatusResponse updateProcessingStatus(UpdateProcessingStatusRequest request) {

        Complaint findComplaint = complaintRepository.findById(request.getComplaintNo())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 민원이 존재하지않습니다"));

        ProcessingStatus processingStatus = ProcessingStatus.fromInt(request.getProcessingStatusInt());

        findComplaint.updateProcessingStatus(processingStatus);

        UpdateProcessingStatusResponse response = new UpdateProcessingStatusResponse(findComplaint.getComplaintNo(),
                                                                                     findComplaint.getProcessingStatus().getText(),
                                                                                     findComplaint.getProcessingStatus().ordinal());

        return response;
    }

    // 민원 전체 조회 : 페이징
    public Page<GetComplaintListResponse> getComplaintList(Pageable pageable) {

        Page<GetComplaintListResponse> response = complaintRepository.findAll(pageable).map(GetComplaintListResponse::toDto);

        return response;
    }

    // 민원 상세 조회
    public GetComplaintResponse getComplaint(Long complaintNo) {

        GetComplaintResponse response = complaintRepository.findById(complaintNo).map(GetComplaintResponse::toDto)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 민원이 존재하지않습니다."));

        return response;
    }

}
