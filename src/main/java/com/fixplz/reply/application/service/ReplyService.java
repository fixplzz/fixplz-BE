package com.fixplz.reply.application.service;

import com.fixplz.reply.application.dto.request.CreateReplyRequest;
import com.fixplz.reply.application.dto.request.DeleteReplyRequest;
import com.fixplz.reply.application.dto.request.UpdateReplyRequest;
import com.fixplz.reply.application.dto.response.CreateReplyResponse;
import com.fixplz.reply.application.dto.response.DeleteReplyResponse;
import com.fixplz.reply.application.dto.response.UpdateReplyResponse;
import com.fixplz.reply.domain.aggregate.entity.Reply;
import com.fixplz.reply.domain.aggregate.vo.PostNoVO;
import com.fixplz.reply.domain.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    // 답변 생성
    @Transactional
    public CreateReplyResponse createReply(CreateReplyRequest request) {

        // 게시글이 존재하는지 확인하는 로직

        PostNoVO postNoVO = new PostNoVO(request.postNo());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

        Reply reply = new Reply.Builder()
                .title(request.title())
                .content(request.content())
                .date(new Date())
                .postNoVo(postNoVO)
                .build();

        Reply createdReply = replyRepository.save(reply);

        return new CreateReplyResponse(createdReply.getReplyNo());

    }

    // 답변 수정
    @Transactional
    public UpdateReplyResponse updateReply(UpdateReplyRequest request) {

        Reply reply = replyRepository.findById(request.replyNo())
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 답변입니다."));

        String updateTitle = reply.getTitle();
        String updateContent = reply.getContent();

        if (!request.title().isEmpty()) {
            updateTitle = request.title();
        }

        if (!request.content().isEmpty()) {
            updateContent = request.content();
        }

        reply.update(updateTitle, updateContent, new Date());

        return new UpdateReplyResponse(reply.getReplyNo());

    }

    // 답변 삭제
    @Transactional
    public DeleteReplyResponse deleteReply(DeleteReplyRequest request) {

        Reply reply = replyRepository.findById(request.replyNo())
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 답변입니다."));

        replyRepository.delete(reply);

        return new DeleteReplyResponse(reply.getReplyNo());
    }

}
