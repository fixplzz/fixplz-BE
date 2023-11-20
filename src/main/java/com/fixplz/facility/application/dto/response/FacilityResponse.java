package com.fixplz.facility.application.dto.response;

public record FacilityResponse(

        Long facilityNo,
        String facilityCategory,
        String facilityName,
        String administrativeDong,
        String facilityAddress,
        String departmentName,
        String departmentNumber,
        String facilityQRUrl,
        double latitude,
        double longitude

) {

    public static FacilityResponse of(
            Long facilityNo,
            String facilityCategory,
            String facilityName,
            String administrativeDong,
            String facilityAddress,
            String departmentName,
            String departmentNumber,
            String facilityQRUrl,
            double latitude,
            double longitude
    ) {
        return new FacilityResponse(
                facilityNo,
                facilityCategory,
                facilityName,
                administrativeDong,
                facilityAddress,
                departmentName,
                departmentNumber,
                facilityQRUrl,
                latitude,
                longitude
        );
    }
}
