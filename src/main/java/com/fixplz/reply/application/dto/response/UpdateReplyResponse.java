package com.fixplz.reply.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReplyResponse {

    private Long replyNo;

    public UpdateReplyResponse(Long replyNo) {
        this.replyNo = replyNo;
    }

}
