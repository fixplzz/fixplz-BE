package com.fixplz.complaint.application.dto.response;

import com.fixplz.complaint.domain.aggregate.entity.Complaint;

import java.text.SimpleDateFormat;

public record GetComplaintListResponse (
        Long complaintNo,
        String complaintContext,
        String complaintImage,
        String date,
        String processingStatus,
        Long facilityNo
){
    public static GetComplaintListResponse toDto(Complaint complaint) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");

        return new GetComplaintListResponse(
                complaint.getComplaintNo(),
                complaint.getComplaintContent(),
                complaint.getComplaintImage(),
                dateFormat.format(complaint.getDate()),
                complaint.getProcessingStatus().getText(),
                complaint.getFacilityNoVO().getFacilityNo()
        );
    }

}
