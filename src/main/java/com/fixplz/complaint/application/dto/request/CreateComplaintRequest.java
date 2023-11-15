package com.fixplz.complaint.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateComplaintRequest {

    private String complaintContent;
    private int filterCategory;
    private String phoneNumber;
    private Long facilityNo;

}
