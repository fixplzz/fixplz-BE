package com.fixplz.complaint.domain.aggregate.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FacilityNoVO {

    @Column
    private Long facilityNo;

    public FacilityNoVO(Long facilityNo) {
        this.facilityNo = facilityNo;
    }

}
