package com.fixplz.complaint.domain.aggregate.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProcessingStatus {
    TODO("처리 전", "1"),
    IN_PROGRESS("처리 중", "2"),
    DONE("처리 완료", "3");

    private String text;
    private String code;

    ProcessingStatus(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public static ProcessingStatus ofCode(String dbData) {
        return Arrays.stream(ProcessingStatus.values())
                .filter(v -> v.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("상태가 코드가 올바르지않습니다."));
    }
}
