package com.fixplz.facilitycategory.domain.repository;

import com.fixplz.facilitycategory.domain.aggregate.entity.FacilityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityCategoryRepository extends JpaRepository<FacilityCategory, Long> {
}