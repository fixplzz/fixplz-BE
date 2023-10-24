package com.fixplz.stat.domain.repository;

import com.fixplz.stat.domain.aggregate.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
@Transactional
public interface VisitorDomainRepository extends JpaRepository<Visitor, Long> {

    // 오늘 날짜로 방문자 기록 조회 메소드 - 조만제
    Visitor findByVisitDate(LocalDate visitDate);

    // 오늘 날짜의 방문자 수 +1 증가 메소드 - 조만제
    @Modifying
    @Query(value = "update tbl_visitor" +
            " set visitor_count = visitor_count + 1" +
            " where visit_date = :visitDate", nativeQuery = true)
    int updateByToday(@Param("visitDate") LocalDate visitDate);

}
