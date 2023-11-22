package com.fixplz.facility.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FacilityDeleteRequest(
        @NotNull(message = "최소한 한 개 이상의 시설물을 선택해주세요.")
        List<Long> facilityNoList
) {
}
