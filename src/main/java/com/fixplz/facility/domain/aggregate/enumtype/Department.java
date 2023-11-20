package com.fixplz.facility.domain.aggregate.enumtype;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Department {
    SMART_CITY("스마트 도시과", "02-2286-6648"),
    ARCHITECTURE("건축과", "02-2286-5627");

    private final String departmentName;
    private final String departmentNumber;

    Department(String departmentName, String departmentNumber) {
        this.departmentName = departmentName;
        this.departmentNumber = departmentNumber;
    }

    public static Department fromDepartmentNameAndNumber(String departmentName, String departmentNumber) {
        return Arrays.stream(values()).filter(value ->
                        value.departmentName.equals(departmentName) &&
                                value.departmentNumber.equals(departmentNumber)
                )
                .findAny().orElseThrow(
                        () -> new IllegalArgumentException("일치하는 부서가 없습니다.")
                );
    }
}
