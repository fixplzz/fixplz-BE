package com.fixplz.reply.domain.repository;

import com.fixplz.reply.domain.aggregate.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
