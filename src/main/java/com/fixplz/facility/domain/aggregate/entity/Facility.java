package com.fixplz.facility.domain.aggregate.entity;

import com.fixplz.facility.domain.aggregate.enumtype.Department;
import com.fixplz.facility.domain.aggregate.enumtype.FacilityCategory;
import com.fixplz.facility.domain.aggregate.vo.CoordinateVO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_FACILITY")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityNo;

    @Enumerated(EnumType.STRING)
    private FacilityCategory facilityCategory;

    @Column
    private String facilityName;

    @Column
    private String facilityQRUrl;

    @Column
    private String administrativeDong;

    @Column
    private String facilityAddress;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Embedded
    private CoordinateVO coordinateVO;

    private Facility(Builder builder) {
        this.facilityNo = builder.facilityNo;
        this.facilityCategory = builder.facilityCategory;
        this.facilityName = builder.facilityName;
        this.facilityQRUrl = builder.facilityQRUrl;
        this.administrativeDong = builder.administrativeDong;
        this.facilityAddress = builder.facilityAddress;
        this.department = builder.department;
        this.coordinateVO = builder.coordinateVO;
    }

    public void update(FacilityCategory facilityCategory, String facilityName, String facilityQRUrl,
                       String administrativeDong, String facilityAddress, Department department, CoordinateVO coordinateVO) {
        this.facilityCategory = facilityCategory;
        this.facilityName = facilityName;
        this.facilityQRUrl = facilityQRUrl;
        this.administrativeDong = administrativeDong;
        this.facilityAddress = facilityAddress;
        this.department = department;
        this.coordinateVO = coordinateVO;
    }

    public void updateQRUrl(String facilityQRUrl) {
        this.facilityQRUrl = facilityQRUrl;
    }

    public static class Builder {
        private Long facilityNo;
        private FacilityCategory facilityCategory;
        private String facilityName;
        private String facilityQRUrl;
        private String administrativeDong;
        private String facilityAddress;
        private Department department;
        private CoordinateVO coordinateVO;

        public static Builder builder() {
            return new Builder();
        }

        public Builder facilityNo(Long facilityNo) {
            this.facilityNo = facilityNo;
            return this;
        }

        public Builder facilityCategory(FacilityCategory facilityCategory) {
            this.facilityCategory = facilityCategory;
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

        public Builder department(Department department) {
            this.department = department;
            return this;
        }

        public Builder coordinateVO(CoordinateVO coordinateVO) {
            this.coordinateVO = coordinateVO;
            return this;
        }

        public Facility build() {
            return new Facility(this);
        }

    }

}
