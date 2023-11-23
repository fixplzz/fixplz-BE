package com.fixplz.complaint.application.dto.response;


public record CreateComplaintResponse (
        Long complaintNo,
        String complaintContent
){

    public static CreateComplaintResponse of (Long complaintNo, String complaintContent) {
        return new CreateComplaintResponse(
                complaintNo,
                complaintContent
        );
    }
}
