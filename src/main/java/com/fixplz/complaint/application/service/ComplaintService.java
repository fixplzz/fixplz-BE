package com.fixplz.complaint.application.service;

import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
import com.fixplz.complaint.application.dto.response.CreateComplaintResponse;
import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.complaint.domain.aggregate.vo.FacilityNoVO;
import com.fixplz.complaint.domain.aggregate.vo.FilterCategory;
import com.fixplz.complaint.domain.aggregate.vo.FilterCategoryConverter;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import com.fixplz.complaint.domain.repository.ComplaintRepository;
import com.fixplz.facility.domain.aggregate.entity.Facility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public CreateComplaintResponse createComplaint(CreateComplaintRequest request) {

        FilterCategory filterCategory = FilterCategory.ofCode(String.valueOf(request.getFilterCategory()));
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
}
