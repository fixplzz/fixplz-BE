package com.fixplz.facility.domain.repository;

import com.fixplz.facility.application.dto.response.FacilityResponse;
import com.fixplz.facility.domain.aggregate.entity.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query("select " +
            "new com.fixplz.facility.application.dto.response.FacilityResponse(" +
            "f.facilityNo, f.facilityCategory, f.facilityName, f.administrativeDong, f.facilityAddress, f.departmentName, f.departmentNumber, f.facilityQRUrl, f.coordinateVO.latitude, f.coordinateVO.longitude" +
            ") from Facility f")
    Optional<Page<FacilityResponse>> findAllFacilityResponse(Pageable pageable);

}
