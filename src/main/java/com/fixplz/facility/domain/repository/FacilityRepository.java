package com.fixplz.facility.domain.repository;

import com.fixplz.facility.domain.aggregate.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {


}
