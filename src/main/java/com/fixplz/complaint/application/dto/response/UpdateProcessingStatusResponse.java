package com.fixplz.complaint.application.dto.response;


public record UpdateProcessingStatusResponse (
        Long complaintNo,
        String processingStatusText,
        int processingStatusNum
){
    public static UpdateProcessingStatusResponse of(Long complaintNo, String processingStatusText, int processingStatusNum) {
        return new UpdateProcessingStatusResponse(
                complaintNo,
                processingStatusText,
                processingStatusNum
        );
    }
}
