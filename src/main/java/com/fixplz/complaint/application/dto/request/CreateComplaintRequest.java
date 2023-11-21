package com.fixplz.complaint.application.dto.request;

public record CreateComplaintRequest (
        String complaintContent,
        int filterCategory,
        String phoneNumber,
        Long facilityNo
) {
    public static CreateComplaintRequest of(String complaintContent, int filterCategory, String phoneNumber, Long facilityNo) {
        return new CreateComplaintRequest(
                complaintContent,
                filterCategory,
                phoneNumber,
                facilityNo
        );
    }
}
