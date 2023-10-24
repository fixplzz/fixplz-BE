package com.fixplz.reply.application.dto.response;

public record UpdateReplyResponse (
        Long replyNo
){
    public static UpdateReplyResponse from(Long replyNo) {
        return new UpdateReplyResponse(
                replyNo
        );
    }
}
