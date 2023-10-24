package com.fixplz.reply.application.dto.request;

public record CreateReplyRequest (
        String title,
        String content,
        Long postNo
) { }
