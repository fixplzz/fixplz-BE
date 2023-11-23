package com.fixplz.complaint.application.dto.request;

public record UpdateProcessingStatusRequest (
        int processingStatusInt
){
    public static UpdateProcessingStatusRequest of(int processingStatusInt) {
        return new UpdateProcessingStatusRequest(
                processingStatusInt
        );
    }
}
