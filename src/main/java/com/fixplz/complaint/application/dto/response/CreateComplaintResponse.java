package com.fixplz.complaint.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class CreateComplaintResponse {
    Long complaintNo;
    String complaintContent;

    public CreateComplaintResponse(Long complaintNo, String complaintContent) {
        this.complaintNo = complaintNo;
        this.complaintContent = complaintContent;
    }
}
