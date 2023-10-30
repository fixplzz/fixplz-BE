package com.fixplz.facilitycategory.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TBL_FACILITY_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class FacilityCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityCategoryNo;

    @Column
    private String facilityCategoryName;

}
