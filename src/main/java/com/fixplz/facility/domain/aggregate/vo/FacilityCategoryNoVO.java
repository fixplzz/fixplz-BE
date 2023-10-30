package com.fixplz.facility.domain.aggregate.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FacilityCategoryNoVO {

    @Column
    private Long facilityCategoryNo;

}
