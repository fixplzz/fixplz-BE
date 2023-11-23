package com.fixplz.complaint.infra.service;

import com.fixplz.common.annotation.InfraService;
import com.fixplz.complaint.application.dto.response.GetFacilityInfo;
import com.fixplz.facility.domain.aggregate.entity.Facility;
import com.fixplz.facility.domain.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;

@InfraService
@RequiredArgsConstructor
public class ComplaintInfraService {

    private final FacilityRepository facilityRepository;

    public GetFacilityInfo getFacilityInfo(Long facilityNo) {
        Facility facility = facilityRepository.findById(facilityNo)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 시설물이 없습니다."));

        return GetFacilityInfo.of(facility);
    }
}
