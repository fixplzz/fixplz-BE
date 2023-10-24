package com.fixplz.reply.application.dto.response;

public record DeleteReplyResponse (
        Long replyNo
) {
    public static DeleteReplyResponse from(Long replyNo) {
        return new DeleteReplyResponse(
                replyNo
        );
    }
}
