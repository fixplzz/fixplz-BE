package com.fixplz.admin.application.dto.response;

import com.fixplz.admin.domain.aggregate.entity.Admin;

public record LoginResponse(
        String token
) {
}
