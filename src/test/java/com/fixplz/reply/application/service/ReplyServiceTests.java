package com.fixplz.reply.application.service;

import com.fixplz.reply.application.dto.request.CreateReplyRequest;
import com.fixplz.reply.application.dto.request.DeleteReplyRequest;
import com.fixplz.reply.application.dto.request.UpdateReplyRequest;
import com.fixplz.reply.application.dto.response.CreateReplyResponse;
import com.fixplz.reply.application.dto.response.DeleteReplyResponse;
import com.fixplz.reply.application.dto.response.UpdateReplyResponse;
import com.fixplz.reply.domain.aggregate.entity.Reply;
import com.fixplz.reply.domain.repository.ReplyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @DisplayName("답변 저장 테스트 : success")
    void createReplyTest() {

        long before = replyRepository.count();

        CreateReplyRequest request = new CreateReplyRequest("제목", "내용", 1L);

        replyService.createReply(request);

        long after = replyRepository.count();

        Assertions.assertEquals(before + 1, after);
    }

    //  TODO -> 답변 저장 테스트 : 게시글이 존재하지않을 때

    @Test
    @DisplayName("답변 수정 테스트 : success")
    void updateReplyTest() {

        CreateReplyRequest replyRequest = new CreateReplyRequest("제목", "내용", 1L);
        CreateReplyResponse replyResponse = replyService.createReply(replyRequest);
        Reply createdReply = replyRepository.findById(replyResponse.replyNo()).get();

        String beforeTitle = createdReply.getTitle();

        UpdateReplyRequest request = new UpdateReplyRequest(replyResponse.replyNo(), "제목 수정", "내용 수정");
        UpdateReplyResponse response = replyService.updateReply(request);
        Reply updateReply = replyRepository.findById(response.replyNo()).get();

        String afterContent = updateReply.getContent();

        Assertions.assertEquals("제목", beforeTitle);
        Assertions.assertEquals("내용 수정", afterContent);

    }

    @Test
    @DisplayName("답변 수정 테스트 : 답변이 존재하지않을 때")
    void checkReplyReplyUpdateTest() {

        long count = replyRepository.count() + 1;

        UpdateReplyRequest request = new UpdateReplyRequest(count, "제목 수정", "내용 수정");

        Assertions.assertThrows(IllegalArgumentException.class, () -> replyService.updateReply(request));

    }

    @Test
    @DisplayName("답변 삭제 테스트 : success")
    void deleteReplyTest() {

        CreateReplyRequest replyRequest = new CreateReplyRequest("제목", "내용", 1L);
        CreateReplyResponse replyResponse = replyService.createReply(replyRequest);
        Reply createdReply = replyRepository.findById(replyResponse.replyNo()).get();

        long beforeCnt = replyRepository.count();

        DeleteReplyRequest request = new DeleteReplyRequest(createdReply.getReplyNo());
        DeleteReplyResponse response = replyService.deleteReply(request);

        long afterCnt = replyRepository.count();

        Assertions.assertEquals(beforeCnt - 1, afterCnt);
        Assertions.assertEquals(createdReply.getReplyNo(), response.replyNo());

    }

    @Test
    @DisplayName("답변 삭제 테스트 : 답변이 존재하지않을 때")
    void checkReplyReplyDeleteTest() {

        long count = replyRepository.count() + 1;
        DeleteReplyRequest request = new DeleteReplyRequest(count);

        Assertions.assertThrows(IllegalArgumentException.class, () -> replyService.deleteReply(request));
    }

    // TODO -> 답변 조회 테스트 : success

    // TODO -> 답변 조회 테스트 : 답변이 존재하지않을 때

}
