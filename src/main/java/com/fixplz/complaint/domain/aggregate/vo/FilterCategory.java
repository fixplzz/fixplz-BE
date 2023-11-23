package com.fixplz.complaint.domain.aggregate.vo;

import lombok.Getter;

@Getter
public enum FilterCategory {
    CLEANING("청소가 필요해요"),
    REPAIRING("수리가 필요해요"),
    INSTALLATION("설치를 건의해요"),
    INCONVENIENCE("이용이 불편해요"),
    OTHER("기타 사항");

    private final String text;

    FilterCategory(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static FilterCategory fromInt(int code) {
        return switch (code) {
            case 0 -> FilterCategory.CLEANING;
            case 1 -> FilterCategory.REPAIRING;
            case 2 -> FilterCategory.INSTALLATION;
            case 3 -> FilterCategory.INCONVENIENCE;
            default -> throw new IllegalStateException("잘못된 코드 (필터 카테고리) : " + code);
        };
    }
}
