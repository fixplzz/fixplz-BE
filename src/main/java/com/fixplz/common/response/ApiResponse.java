package com.fixplz.common.response;

import com.fixplz.common.response.enumtype.ApiStatus;

public record ApiResponse(
        ApiStatus status,
        String message,
        Object data
){
    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(ApiStatus.SUCCESS, message, data);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(ApiStatus.ERROR, message, null);
    }
}
