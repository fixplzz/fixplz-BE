package com.fixplz.reply.application.dto.response;

public record CreateReplyResponse (
        Long replyNo
){
    public static CreateReplyResponse from(Long replyNo) {
        return new CreateReplyResponse(
                replyNo
        );
    }
}
