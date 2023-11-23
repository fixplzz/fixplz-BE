package com.fixplz.complaint.application.dto.response;

import com.fixplz.facility.domain.aggregate.entity.Facility;

public record GetFacilityInfo (
        Long facilityNo,
        String administrativeDong,
        double latitude,
        double longitude,
        String departmentName,
        String facilityAddress,
        String facilityCategory,
        String facilityName,
        String facilityQRUrl
){
    public static GetFacilityInfo of(Facility facility) {
        return new GetFacilityInfo(
                facility.getFacilityNo(),
                facility.getAdministrativeDong(),
                facility.getCoordinateVO().getLatitude(),
                facility.getCoordinateVO().getLongitude(),
                facility.getDepartmentName(),
                facility.getFacilityAddress(),
                facility.getFacilityCategory(),
                facility.getFacilityName(),
                facility.getFacilityQRUrl()

        );
    }
}
