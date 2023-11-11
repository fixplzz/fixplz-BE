package com.fixplz.complaint.domain.aggregate.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FilterCategory {
    CLEANING("청소가 필요해요", "1"),
    REPAIRING("수리가 필요해요", "2"),
    INSTALLATION("설치를 건의해요", "3"),
    INCONVENIENCE("이용이 불편해요", "4"),
    OTHER("기타 사항", "5");

    private String text;
    private String code;

    FilterCategory(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public static FilterCategory ofCode(String dbDate) {
        return Arrays.stream(FilterCategory.values())
                .filter(v -> v.getCode().equals(dbDate))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("상태 코드가 올바르지않습니다."));
    }

}
