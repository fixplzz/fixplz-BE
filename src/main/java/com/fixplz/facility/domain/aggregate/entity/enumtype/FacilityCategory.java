package com.fixplz.facility.domain.aggregate.entity.enumtype;

import lombok.Getter;

@Getter
public enum FacilityCategory {

    SMART_SHELTER("스마트 쉼터"),
    SMART_SMOKING_ROOM("스마트 흡연부스");

    private final String categoryName;

    FacilityCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
