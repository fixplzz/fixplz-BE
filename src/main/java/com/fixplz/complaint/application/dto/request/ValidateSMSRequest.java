package com.fixplz.complaint.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateSMSRequest {

    String phoneNo;
    String code;

}
