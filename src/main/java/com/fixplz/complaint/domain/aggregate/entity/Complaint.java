package com.fixplz.complaint.domain.aggregate.entity;

import com.fixplz.complaint.domain.aggregate.vo.FacilityNoVO;
import com.fixplz.complaint.domain.aggregate.vo.FilterCategory;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Table(name = "TBL_COMPLAINT")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
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

    @Column
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    private ProcessingStatus processingStatus;

    @Enumerated(EnumType.ORDINAL)
    private FilterCategory filterCategory;

    @Embedded
    private FacilityNoVO facilityNoVO;


    public Complaint(Builder builder) {
        this.complaintNo = builder.complaintNo;
        this.complaintContent = builder.complaintContent;
        this.phoneNumber = builder.phoneNumber;
        this.complaintImage = builder.complaintImage;
        this.date = builder.date;
        this.processingStatus = builder.processingStatus;
        this.filterCategory = builder.filterCategory;
        this.facilityNoVO = builder.facilityNoVO;
    }

    public static class Builder {
        private Long complaintNo;
        private String complaintContent;
        private String phoneNumber;
        private String complaintImage;
        private Date date;
        private ProcessingStatus processingStatus;
        private FilterCategory filterCategory;
        private FacilityNoVO facilityNoVO;

        public static Builder builder() {
            return new Builder();
        }

        public Builder complaintNo(Long complaintNo) {
            this.complaintNo = complaintNo;
            return this;
        }

        public Builder complaintContent(String complaintContent) {
            this.complaintContent = complaintContent;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder complaintImage(String complaintImage) {
            this.complaintImage = complaintImage;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder processingStatus(ProcessingStatus processingStatus) {
            this.processingStatus = processingStatus;
            return this;
        }

        public Builder filterCategory(FilterCategory filterCategory) {
            this.filterCategory = filterCategory;
            return this;
        }

        public Builder facilityNoVO(FacilityNoVO facilityNoVO) {
            this.facilityNoVO = facilityNoVO;
            return this;
        }

        public Complaint build() {
            return new Complaint(this);
        }
    }

    public void updateProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }


}
