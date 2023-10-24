package com.fixplz.reply.application.dto.request;

public record UpdateReplyRequest (
        Long replyNO,
        String title,
        String content
){ }
