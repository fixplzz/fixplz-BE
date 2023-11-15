package com.fixplz.complaint.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor()
public class UpdateProcessingStatusRequest {

    private int processingStatusInt;
    private Long complaintNo;

}
