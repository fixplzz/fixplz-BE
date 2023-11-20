package com.fixplz.facility.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FacilityRequest(

        @NotBlank(message = "카테고리를 선택해주세요.")
        String facilityCategory,

        @NotBlank(message = "시설 명을 기입해주세요.")
        String facilityName,

        @NotBlank(message = "행정 동을 선정해주세요.")
        String administrativeDong,

        @NotBlank(message = "주소를 기입해주세요.")
        String facilityAddress,

        @NotBlank(message = "담당 부서를 선정해주세요.")
        String departmentName,

        @NotBlank(message = "담당 부서의 번호를 기입해주세요.")
        String departmentNumber,

        double latitude,

        double longitude


) {
}
