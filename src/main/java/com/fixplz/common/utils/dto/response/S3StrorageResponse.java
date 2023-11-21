package com.fixplz.common.utils.dto.response;

public record S3StrorageResponse(
        byte[] image,
        String fileName
) {

    public static S3StrorageResponse of(byte[] image, String fileName) {
        return new S3StrorageResponse(image, fileName);
    }
}
