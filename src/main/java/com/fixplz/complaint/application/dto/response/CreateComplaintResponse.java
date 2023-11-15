package com.fixplz.complaint.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class CreateComplaintResponse {
    private Long complaintNo;
    private String complaintContent;

    public CreateComplaintResponse(Long complaintNo, String complaintContent) {
        this.complaintNo = complaintNo;
        this.complaintContent = complaintContent;
    }
}
