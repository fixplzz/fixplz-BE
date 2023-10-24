package com.fixplz.reply.application.dto.request;

public record UpdateReplyRequest (
        Long replyNo,
        String title,
        String content
){ }
