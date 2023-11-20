package com.fixplz.facility.application.controller;

import com.fixplz.common.response.ApiResponse;
import com.fixplz.facility.application.dto.request.FacilityRequest;
import com.fixplz.facility.application.dto.response.FacilityResponse;
import com.fixplz.facility.application.service.FacilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<ApiResponse> registFacility(@RequestBody @Valid FacilityRequest request,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ApiResponse.error(bindingResult.getFieldError().getDefaultMessage()));
        }
        FacilityResponse response = facilityService.registFacility(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("시설물 등록에 성공하였습니다.", response));
    }
}
