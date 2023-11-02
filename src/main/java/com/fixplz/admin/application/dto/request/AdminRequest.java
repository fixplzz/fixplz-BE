package com.fixplz.admin.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AdminRequest(

        @NotBlank(message = "관리자 아이디를 입력해주세요.")
        String adminId,
        @NotBlank(message = "관리자 비밀번호를 입력해주세요.")
        String adminPassword
) {

    public static AdminRequest of (String adminId, String adminPassword) {
        return new AdminRequest(
                adminId,
                adminPassword
        );
    }
}
