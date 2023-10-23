package com.fixplz.reply.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReplyResponse {

    private Long replyNo;

    public CreateReplyResponse(Long replyNo) {
        this.replyNo = replyNo;
    }
}
