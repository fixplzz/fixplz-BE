package com.fixplz.complaint.application.dto.response;

import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetComplaintResponse {
    private Long complaintNo;
    private String complaintContext;
    private String phoneNumber;
    private String complaintImage;
    private String processingStatus;
    private Long facilityNo;
    private String filterCategory;

    public static GetComplaintResponse toDto(Complaint complaint) {
        GetComplaintResponse response = new GetComplaintResponse();

        response.complaintNo = complaint.getComplaintNo();
        response.complaintContext = complaint.getComplaintContent();
        response.phoneNumber = complaint.getPhoneNumber();
        response.complaintImage = complaint.getComplaintImage();
        response.processingStatus = complaint.getProcessingStatus().getText();
        response.facilityNo = complaint.getFacilityNoVO().getFacilityNo();
        response.filterCategory = complaint.getFilterCategory().getText();

        return response;
    }
}
