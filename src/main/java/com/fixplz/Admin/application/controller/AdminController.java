package com.fixplz.admin.application.controller;

import com.fixplz.admin.application.dto.response.AdminResponse;
import com.fixplz.admin.application.dto.response.LoginResponse;
import com.fixplz.common.response.ApiResponse;
import com.fixplz.admin.application.dto.request.AdminRequest;
import com.fixplz.admin.application.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping(value = "/admin/regist")
    public ResponseEntity<ApiResponse> Signup(@RequestBody AdminRequest request) {

        System.out.println("CONTROLLER request = " + request);

        AdminResponse response = adminService.register(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("성공적으로 등록되었습니다." , response)
        );

    }

    @PostMapping(value = "/admin/login")
    public ResponseEntity<ApiResponse> Login(@RequestBody AdminRequest request) {

        LoginResponse response = adminService.login(request);


        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("성공적으로 로그인되었습니다." , response)
        );
    }
}
