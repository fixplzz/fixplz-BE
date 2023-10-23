package com.fixplz.reply.domain.aggregate.entity;

import com.fixplz.reply.domain.aggregate.vo.PostNoVO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Table(name = "TBL_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Date date;

    @Embedded
    private PostNoVO postNoVO;

    @Builder
    public Reply(String title, String content, Date date, PostNoVO postNoVO) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.postNoVO = postNoVO;
    }
}
