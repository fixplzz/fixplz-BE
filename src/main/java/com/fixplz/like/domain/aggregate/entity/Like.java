package com.fixplz.like.domain.aggregate.entity;

import com.fixplz.like.domain.aggregate.vo.LikeVO;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_LIKE")
public class Like {

    @EmbeddedId
    private LikeVO likeVO;
}
