package com.fixplz.reply.domain.aggregate.entity;

import com.fixplz.reply.domain.aggregate.vo.PostNoVO;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    public Reply(Builder builder) {
        this.replyNo = builder.replyNo;
        this.title = builder.title;
        this.content = builder.content;
        this.date = builder.date;
        this.postNoVO = builder.postNoVO;
    }

    public static class Builder {

        private Long replyNo;
        private String title;
        private String content;
        private Date date;
        private PostNoVO postNoVO;

        public Builder replyNo(Long replyNo) {
            this.replyNo = replyNo;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder postNoVo(PostNoVO postNoVO) {
            this.postNoVO = postNoVO;
            return this;
        }

        public Reply build() {
            return new Reply(this);
        }
    }

    public void update(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
