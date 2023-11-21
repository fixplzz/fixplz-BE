package com.fixplz.complaint.application.controller;

import com.fixplz.common.response.ApiResponse;
import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
import com.fixplz.complaint.application.dto.request.UpdateProcessingStatusRequest;
import com.fixplz.complaint.application.dto.response.CreateComplaintResponse;
import com.fixplz.complaint.application.dto.response.GetComplaintListResponse;
import com.fixplz.complaint.application.dto.response.GetComplaintResponse;
import com.fixplz.complaint.application.dto.response.UpdateProcessingStatusResponse;
import com.fixplz.complaint.application.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ApiResponse> createComplaint(@RequestBody CreateComplaintRequest request) {

        CreateComplaintResponse response = complaintService.createComplaint(request);

        return ResponseEntity.ok().body(ApiResponse.success("민원 등록 성공", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getComplaintList(Pageable pageable) {

        Page<GetComplaintListResponse> response = complaintService.getComplaintList(pageable);

        return ResponseEntity.ok().body(ApiResponse.success("민원 전체 조회 성공", response));
    }

    @PutMapping("/{complaintNo}")
    public ResponseEntity<ApiResponse> updateProcessingStatus(@PathVariable("complaintNo") Long complaintNO, @RequestBody UpdateProcessingStatusRequest request) {

        UpdateProcessingStatusResponse response = complaintService.updateProcessingStatus(request, complaintNO);

        return ResponseEntity.ok().body(ApiResponse.success("민원 진행 상황 업데이트 성공", response));
    }

    @GetMapping("/{complaintNo}")
    public ResponseEntity<ApiResponse> getComplaint(@PathVariable("complaintNo") Long complaintNo) {

        GetComplaintResponse response = complaintService.getComplaint(complaintNo);

        return ResponseEntity.ok().body(ApiResponse.success("민원 상세조회 성공", response));
    }

}
