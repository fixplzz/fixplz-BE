package com.fixplz.complaint.application.dto.response;

import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.facility.domain.aggregate.enumtype.FacilityCategory;

import java.text.SimpleDateFormat;

public record GetComplaintResponse (
        Long complaintNo,
        String complaintContext,
        String phoneNumber,
        String complaintImage,
        String date,
        String processingStatus,
        Long facilityNo,
        String filterCategory,
        GetFacilityInfo facilityInfo
){

    public static GetComplaintResponse getFacilityInfo(GetComplaintResponse response, GetFacilityInfo getFacilityInfo) {
        return new GetComplaintResponse(
                response.complaintNo,
                response.complaintContext,
                response.phoneNumber,
                response.complaintImage,
                response.date,
                response.processingStatus,
                response.facilityNo,
                response.filterCategory,
                getFacilityInfo
        );
    }


    public static GetComplaintResponse toDto(Complaint complaint) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");

        return new GetComplaintResponse(
                complaint.getComplaintNo(),
                complaint.getComplaintContent(),
                complaint.getPhoneNumber(),
                complaint.getComplaintImage(),
                dateFormat.format(complaint.getDate()),
                complaint.getProcessingStatus().getText(),
                complaint.getFacilityNoVO().getFacilityNo(),
                complaint.getFilterCategory().getText(),
                null
        );
    }

}
