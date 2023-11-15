package com.fixplz.complaint.domain.aggregate.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProcessingStatus {
    TODO("처리 전"),
    IN_PROGRESS("처리 중"),
    DONE("처리 완료");

    private final String text;

    ProcessingStatus(String text) {
        this.text = text;
    }

    public static ProcessingStatus fromInt(int code) {
        return switch (code) {
            case 0 -> ProcessingStatus.TODO;
            case 1 -> ProcessingStatus.IN_PROGRESS;
            case 2 -> ProcessingStatus.DONE;
            default -> throw new IllegalStateException("잘못된 코드 (처리 상태) : " + code);
        };
    }
}
