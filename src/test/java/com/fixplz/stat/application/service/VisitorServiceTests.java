package com.fixplz.stat.application.service;

import com.fixplz.stat.domain.aggregate.entity.Visitor;
import com.fixplz.stat.domain.repository.VisitorDomainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class VisitorServiceTests {
    private final LocalDate TODAY = LocalDate.now();
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private VisitorDomainRepository visitorDomainRepository;

    @Test
    @DisplayName("일일 방문자 통계 조회 테스트: 오늘 날짜의 통계 없을 경우")
    void countDailyVisitorTest1() {
        //given
        visitorDomainRepository.save(Visitor.builder()
                .visitDate(TODAY)
                .visitorCount(2)
                .build());
        //when
        int result = visitorService.countDailyVisitor();
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("일일 방문자 통계 조회 테스트: 오늘 날짜의 통계 있을 경우")
    void countDailyVisitorTest2() {
        //given
        visitorDomainRepository.deleteAllInBatch();
        //when
        int result = visitorService.countDailyVisitor();
        //then
        Assertions.assertEquals(1, result);
    }
}
