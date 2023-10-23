package com.fixplz.reply.application.controller;

import com.fixplz.common.response.ApiResponse;
import com.fixplz.reply.application.dto.request.DeleteReplyRequest;
import com.fixplz.reply.application.dto.response.DeleteReplyResponse;
import com.fixplz.reply.application.dto.response.UpdateReplyResponse;
import com.fixplz.reply.application.dto.request.CreateReplyRequest;
import com.fixplz.reply.application.dto.request.UpdateReplyRequest;
import com.fixplz.reply.application.dto.response.CreateReplyResponse;
import com.fixplz.reply.application.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // 답변 생성
    public ResponseEntity<ApiResponse> createReply(CreateReplyRequest request) {

        // TODO -> 사용자가 관리자인지 확인하는 로직

        CreateReplyResponse response = replyService.createReply(request);

        return ResponseEntity.ok(ApiResponse.success("답변 저장 성공", response));
    }

    // 답변 수정
    public ResponseEntity<ApiResponse> updateReply(UpdateReplyRequest request) {

        // TODO -> 사용자가 관리자인지 확인하는 로직

        UpdateReplyResponse response = replyService.updateReply(request);

        return ResponseEntity.ok(ApiResponse.success("답변 수정 성공", response));
    }

    // 답변 삭제
    public ResponseEntity<ApiResponse> deleteReply(DeleteReplyRequest request) {

        // TODO -> 사용자가 관리자인지 확인하는 로직

        DeleteReplyResponse response = replyService.deleteReply(request);

        return ResponseEntity.ok(ApiResponse.success("답변 삭제 성공", response));
    }

}
