package com.fixplz.facility.domain.aggregate.entity;

import com.fixplz.facility.domain.aggregate.vo.FacilityCategoryNoVO;
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
    private String facilityLatitude;

    @Column
    private String facilityLongitude;

    @Column
    private String facilityAddress;

    @Column
    private Enum<FacilityStatus> facilityStatus;

    @Column
    private int identityNumber;

    @Embedded
    private FacilityCategoryNoVO facilityCategoryNoVO;

    private Facility(Builder builder) {
        this.facilityNo = builder.facilityNo;
        this.facilityName = builder.facilityName;
        this.facilityLatitude = builder.facilityLatitude;
        this.facilityLongitude = builder.facilityLongitude;
        this.facilityAddress = builder.facilityAddress;
        this.facilityStatus = builder.facilityStatus;
        this.identityNumber = builder.identityNumber;
        this.facilityCategoryNoVO = builder.facilityCategoryNoVO;
    }

    public static class Builder {
        private Long facilityNo;
        private String facilityName;
        private String facilityLatitude;
        private String facilityLongitude;
        private String facilityAddress;
        private Enum<FacilityStatus> facilityStatus;
        private int identityNumber;
        private FacilityCategoryNoVO facilityCategoryNoVO;

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

        public Builder facilityLatitude(String facilityLatitude) {
            this.facilityLatitude = facilityLatitude;
            return this;
        }

        public Builder facilityLongitude(String facilityLongitude) {
            this.facilityLongitude = facilityLongitude;
            return this;
        }

        public Builder facilityAddress(String facilityAddress) {
            this.facilityAddress = facilityAddress;
            return this;
        }

        public Builder facilityStatus(Enum<FacilityStatus> facilityStatus) {
            this.facilityStatus = facilityStatus;
            return this;
        }

        public Builder identityNumber(int identityNumber) {
            this.identityNumber = identityNumber;
            return this;
        }

        public Builder facilityCategoryNoVO(FacilityCategoryNoVO facilityCategoryNoVO) {
            this.facilityCategoryNoVO = facilityCategoryNoVO;
            return this;
        }

        public Facility build() {
            return new Facility(this);
        }

    }

    public void updateStatus(Enum<FacilityStatus> facilityStatus) {
        this.facilityStatus = facilityStatus;
    }



}
