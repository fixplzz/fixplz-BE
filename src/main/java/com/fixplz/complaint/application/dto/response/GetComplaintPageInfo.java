package com.fixplz.complaint.application.dto.response;

import java.util.List;
import java.util.Map;

public record GetComplaintPageInfo(
        GetFacilityInfo facilityInfo,
        List<Map<Integer, String>> filterCategory
) {
    public static GetComplaintPageInfo of(GetFacilityInfo facilityInfo, List<Map<Integer, String>> filterCategory) {
        return new GetComplaintPageInfo(
                facilityInfo,
                filterCategory
        );
    }
}
