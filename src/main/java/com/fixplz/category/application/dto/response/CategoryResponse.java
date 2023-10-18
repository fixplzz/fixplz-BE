package com.fixplz.category.application.dto.response;

public record CategoryResponse(
        Long categoryNo,

        String categoryName,

        String categoryExample
) {

    public static CategoryResponse of (Long categoryNo, String categoryName, String categoryExample) {
        return new CategoryResponse(categoryNo, categoryName,categoryExample);
    }
}
