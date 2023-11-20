package com.fixplz.admin.application.dto.response;

import com.fixplz.admin.domain.aggregate.entity.Admin;

public record AdminResponse(
        Long adminNo,
        String adminId

) {

    public static AdminResponse from (Admin admin) {
        return new AdminResponse(admin.getAdminNo(), admin.getAdminId());
    }
}