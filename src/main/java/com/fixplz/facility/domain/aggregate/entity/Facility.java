package com.fixplz.facility.domain.aggregate.entity;

import com.fixplz.facility.domain.aggregate.entity.enumtype.FacilityCategory;
import com.fixplz.facility.domain.aggregate.vo.CoordinateVO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TBL_FACILITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityNo;

    @Column
    private String facilityName;

    @Column
    private String facilityQRUrl;

    @Column
    private String administrativeDong;

    @Column
    private String facilityAddress;

    @Column
    private String departmentName;

    @Column
    private String departmentCallNumber;

    @Embedded
    private CoordinateVO coordinateVO;

    @Enumerated(EnumType.STRING)
    private FacilityCategory facilityCategory;

    private Facility(Builder builder) {
        this.facilityNo = builder.facilityNo;
        this.facilityName = builder.facilityName;
        this.facilityQRUrl = builder.facilityQRUrl;
        this.administrativeDong = builder.administrativeDong;
        this.facilityAddress = builder.facilityAddress;
        this.departmentName = builder.departmentName;
        this.departmentCallNumber = builder.departmentCallNumber;
        this.coordinateVO = builder.coordinateVO;
        this.facilityCategory = builder.facilityCategory;
    }

    public static class Builder {
        private Long facilityNo;
        private String facilityName;
        private String facilityQRUrl;
        private String administrativeDong;
        private String facilityAddress;
        private String departmentName;
        private String departmentCallNumber;
        private CoordinateVO coordinateVO;
        private FacilityCategory facilityCategory;

        public static Builder builder() {
            return new Builder();
        }

        public Builder facilityNo(Long facilityNo) {
            this.facilityNo = facilityNo;
            return this;
        }

        public Builder facilityName(String facilityName) {
            this.facilityName = facilityName;
            return this;
        }

        public Builder facilityQRUrl(String facilityQRUrl) {
            this.facilityQRUrl = facilityQRUrl;
            return this;
        }

        public Builder administrativeDong(String administrativeDong) {
            this.administrativeDong = administrativeDong;
            return this;
        }

        public Builder facilityAddress(String facilityAddress) {
            this.facilityAddress = facilityAddress;
            return this;
        }

        public Builder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public Builder departmentCallNumber(String departmentCallNumber) {
            this.departmentCallNumber = departmentCallNumber;
            return this;
        }

        public Builder coordinateVO(CoordinateVO coordinateVO) {
            this.coordinateVO = coordinateVO;
            return this;
        }

        public Builder facilityCategory(FacilityCategory facilityCategory) {
            this.facilityCategory = facilityCategory;
            return this;
        }

        public Facility build() {
            return new Facility(this);
        }

    }

}
