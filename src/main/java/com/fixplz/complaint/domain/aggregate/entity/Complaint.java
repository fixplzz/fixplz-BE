package com.fixplz.complaint.domain.aggregate.entity;

import com.fixplz.complaint.domain.aggregate.vo.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TBL_COMPLAINT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintNo;

    @Column
    private String complaintContent;

    @Column
    private String phoneNumber;

    @Column
    private String complaintImage;

    @Convert(converter = ProcessingStatusConverter.class)
    private ProcessingStatus processingStatus;

    @Convert(converter = FilterCategoryConverter.class)
    private FilterCategory filterCategory;

    @Embedded
    private FacilityNoVO facilityNoVO;


    private Complaint(Builder builder) {
        this.complaintNo = builder.complaintNo;
        this.complaintContent = builder.complaintContent;
        this.phoneNumber = builder.phoneNumber;
        this.complaintImage = builder.complaintImage;
        this.processingStatus = builder.processingStatus;
        this.filterCategory = builder.filterCategory;
        this.facilityNoVO = builder.facilityNoVO;
    }

    public static class Builder {
        private Long complaintNo;
        private String complaintContent;
        private String phoneNumber;
        private String complaintImage;
        private ProcessingStatus processingStatus;
        private FilterCategory filterCategory;
        private FacilityNoVO facilityNoVO;

        public static Builder builder() {
            return new Builder();
        }

        private Builder complaintNo(Long complaintNo) {
            this.complaintNo = complaintNo;
            return this;
        }

        private Builder complaintContent(String complaintContent) {
            this.complaintContent = complaintContent;
            return this;
        }

        private Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        private Builder complaintImage(String complaintImage) {
            this.complaintImage = complaintImage;
            return this;
        }

        private Builder processingStatus(ProcessingStatus processingStatus) {
            this.processingStatus = processingStatus;
            return this;
        }

        private Builder filterCategory(FilterCategory filterCategory) {
            this.filterCategory = filterCategory;
            return this;
        }

        private Builder facilityNoVO(FacilityNoVO facilityNoVO) {
            this.facilityNoVO = facilityNoVO;
            return this;
        }

        public Complaint build() {
            return new Complaint(this);
        }
    }


}
