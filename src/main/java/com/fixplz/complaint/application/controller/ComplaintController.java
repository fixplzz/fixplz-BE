package com.fixplz.complaint.application.controller;

import com.fixplz.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/complaint")
public class ComplaintController {

    @PostMapping
    public ResponseEntity<ApiResponse> createComplaint() {

    }
}
