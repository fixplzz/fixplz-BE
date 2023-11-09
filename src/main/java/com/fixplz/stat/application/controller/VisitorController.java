package com.fixplz.stat.application.controller;

import com.fixplz.common.response.ApiResponse;
import com.fixplz.stat.application.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 */
@RestController
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;

    @GetMapping("/")
    public ApiResponse getVisitorCount(@RequestParam(required = false) String visitorKey) {
        return ApiResponse.success("방문자 수 성공적으로 증가됐습니다.", visitorService.getVisitorCount(visitorKey));
    }
}
