package com.fixplz.facility.domain.aggregate.enumtype;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FacilityCategory {

    SMART_SHELTER("스마트 쉼터"),
    SMART_SMOKING_ROOM("스마트 흡연부스");

    private final String categoryName;

    FacilityCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public static FacilityCategory fromCategoryName(String categoryName) {
        return Arrays.stream(
                        values()).filter(value -> value.categoryName.equals(categoryName)
                )
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 카테고리가 없습니다."));
    }
}
