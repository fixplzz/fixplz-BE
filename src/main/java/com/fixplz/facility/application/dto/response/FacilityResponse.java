package com.fixplz.facility.application.dto.response;

import com.fixplz.facility.domain.aggregate.entity.Facility;

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

    public static FacilityResponse of(Facility facility) {
        return new FacilityResponse(
                facility.getFacilityNo(),
                facility.getFacilityCategory(),
                facility.getFacilityName(),
                facility.getAdministrativeDong(),
                facility.getFacilityAddress(),
                facility.getDepartmentName(),
                facility.getDepartmentNumber(),
                facility.getFacilityQRUrl(),
                facility.getCoordinateVO().getLatitude(),
                facility.getCoordinateVO().getLongitude()
        );
    }
}
