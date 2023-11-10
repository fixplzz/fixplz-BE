package com.fixplz.complaint.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateComplaintRequest {

    String complaintContent;
    int filterCategory;
    String phoneNumber;
    Long facilityNo;

}
