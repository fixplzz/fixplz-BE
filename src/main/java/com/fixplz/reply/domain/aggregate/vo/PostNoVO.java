package com.fixplz.reply.domain.aggregate.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostNoVO {

    @Column
    private Long postNo;

    public PostNoVO(Long postNo) {
        this.postNo = postNo;
    }
}
