package com.fixplz.complaint.application.dto.response;

import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetComplaintListResponse {
    private Long complaintNo;
    private String complaintContext;
    private String complaintImage;
    private String processingStatus;
    private Long facilityNo;

    public static GetComplaintListResponse toDto(Complaint complaint) {
        GetComplaintListResponse response = new GetComplaintListResponse();

        response.complaintNo = complaint.getComplaintNo();
        response.complaintContext = complaint.getComplaintContent();
        response.complaintImage = complaint.getComplaintImage();
        response.processingStatus = complaint.getProcessingStatus().getText();
        response.facilityNo = complaint.getFacilityNoVO().getFacilityNo();

        return response;
    }

}
