package com.fixplz.complaint.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProcessingStatusResponse {
    Long complaintNo;
    String processingStatusText;
    int processingStatusNum;
}
