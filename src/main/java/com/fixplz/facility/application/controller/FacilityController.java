package com.fixplz.facility.application.controller;

import com.fixplz.common.response.ApiResponse;
import com.fixplz.facility.application.dto.request.FacilityRequest;
import com.fixplz.facility.application.dto.response.FacilityResponse;
import com.fixplz.facility.application.service.FacilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping
    public ResponseEntity<ApiResponse> getFacilitiesList(@PageableDefault(page = 0, size = 10, sort = "facilityNo", direction = Sort.Direction.ASC) Pageable pageable) {
        // 클라이언트에서 http://localhost:8080/contents?page=2&size=30&sort=title,desc 이런 식으로 요청을 보내야 한다.
        Page<FacilityResponse> facilitiesList = facilityService.getFacilitiesList(pageable);
        return ResponseEntity.ok().body(ApiResponse.success("시설물 전체 조회에 성공하였습니다.", facilitiesList));
    }

    @GetMapping("{facilityNo}")
    public ResponseEntity<ApiResponse> getFacilityByFacilityNo(@PathVariable(name = "facilityNo") Long facilityNo) {
        FacilityResponse facility = facilityService.getFacilityByFacilityNo(facilityNo);
        return ResponseEntity.ok().body(ApiResponse.success("특정 시설물 조회에 성공하였습니다.", facility));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> registFacility(@RequestBody @Valid FacilityRequest request,
                                                      BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ApiResponse.error(bindingResult.getFieldError().getDefaultMessage()));
        }

        FacilityResponse response = facilityService.registFacility(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("시설물 등록에 성공하였습니다.", response));
    }

}
