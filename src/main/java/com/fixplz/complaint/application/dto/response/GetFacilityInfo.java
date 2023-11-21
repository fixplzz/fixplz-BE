package com.fixplz.complaint.application.dto.response;

import com.fixplz.facility.domain.aggregate.entity.Facility;
import com.fixplz.facility.domain.aggregate.enumtype.Department;
import com.fixplz.facility.domain.aggregate.enumtype.FacilityCategory;

public record GetFacilityInfo (
        Long facilityNo,
        String administrativeDong,
        double latitude,
        double longitude,
        Department department,
        String facilityAddress,
        FacilityCategory facilityCategory,
        String facilityName,
        String facilityQRUrl
){
    public static GetFacilityInfo of(Facility facility) {
        return new GetFacilityInfo(
                facility.getFacilityNo(),
                facility.getAdministrativeDong(),
                facility.getCoordinateVO().getLatitude(),
                facility.getCoordinateVO().getLongitude(),
                facility.getDepartment(),
                facility.getFacilityAddress(),
                facility.getFacilityCategory(),
                facility.getFacilityName(),
                facility.getFacilityQRUrl()

        );
    }
}
