package com.fixplz.like.domain.repository;

import com.fixplz.like.domain.aggregate.entity.Like;
import com.fixplz.like.domain.aggregate.vo.LikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeVO> {
}
