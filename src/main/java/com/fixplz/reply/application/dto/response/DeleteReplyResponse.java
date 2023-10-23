package com.fixplz.reply.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteReplyResponse {

    private Long replyNo;

    public DeleteReplyResponse(Long replyNo) {
        this.replyNo = replyNo;
    }

}
