package com.fixplz.complaint.application.dto.response;

import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetComplaintList {
    private Long complaintNo;
    private String complaintContext;
    private String complaintImage;
    private ProcessingStatus processingStatus;
    private Long facilityNo;
}
