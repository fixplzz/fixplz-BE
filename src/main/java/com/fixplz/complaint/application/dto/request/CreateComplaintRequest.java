package com.fixplz.complaint.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record CreateComplaintRequest (
        MultipartFile image,
        String complaintContent,
        int filterCategory,
        String phoneNumber,
        Long facilityNo
) {
    public static CreateComplaintRequest of(MultipartFile image, String complaintContent, int filterCategory, String phoneNumber, Long facilityNo) {
        return new CreateComplaintRequest(
                image,
                complaintContent,
                filterCategory,
                phoneNumber,
                facilityNo
        );
    }
}
