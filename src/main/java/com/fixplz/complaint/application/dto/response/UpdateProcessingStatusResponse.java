package com.fixplz.complaint.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProcessingStatusResponse {
    private Long complaintNo;
    private String processingStatusText;
    private int processingStatusNum;
}
