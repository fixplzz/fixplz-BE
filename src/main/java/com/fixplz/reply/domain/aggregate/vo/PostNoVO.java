package com.fixplz.reply.domain.aggregate.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PostNoVO {

    @Column
    private Long postNo;

}
