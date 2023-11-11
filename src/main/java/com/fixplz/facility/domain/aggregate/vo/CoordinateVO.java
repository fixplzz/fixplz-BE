package com.fixplz.facility.domain.aggregate.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CoordinateVO {

    @Column
    private double latitude;

    @Column
    private double longitude;

    public CoordinateVO(double latitude, double longitude) {

        if (latitude >= 32 && latitude <= 35) {
            this.latitude = latitude;
        } else {
            this.latitude = 0;
        }

        if (longitude >= 124 && longitude <= 128) {
            this.longitude = longitude;
        } else {
            this.longitude = 0;
        }
    }
}
