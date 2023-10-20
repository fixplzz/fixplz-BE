package com.fixplz.like.domain.aggregate.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class LikeVO implements Serializable {

    @Column
    private Long postNo;

    @Column
    private Long memberNo;

    public LikeVO(Long postNo, Long memberNo) {
        this.postNo = postNo;
        this.memberNo = memberNo;
    }

}
