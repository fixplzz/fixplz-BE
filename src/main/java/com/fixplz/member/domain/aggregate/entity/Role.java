package com.fixplz.member.domain.aggregate.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER,
    ADMIN
}
