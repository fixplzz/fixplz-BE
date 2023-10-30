package com.fixplz.category.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequest (
        @NotBlank(message = "카테고리 명을 입력해주세요.")
        String categoryName,
        @NotBlank(message = "카테고리에 대한 예시를 등록해주세요.")
        String categoryExample
){
    public static CategoryRequest of (String categoryName, String categoryExample) {
        return new CategoryRequest(
                categoryName,
                categoryExample
        );
    }
}
