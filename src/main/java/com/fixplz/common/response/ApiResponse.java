package com.fixplz.common.response;

import com.fixplz.common.response.enumtype.ApiStatus;

public class ApiResponse {
    ApiStatus status;
    String message;
    Object data;

    public ApiResponse(ApiStatus apiStatus, String message, Object data) {
        this.status = apiStatus;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(ApiStatus.SUCCESS, message, data);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(ApiStatus.ERROR, message, null);
    }
}
