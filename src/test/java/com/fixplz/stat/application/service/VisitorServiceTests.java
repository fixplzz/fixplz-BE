package com.fixplz.stat.application.service;

import com.fixplz.stat.application.controller.VisitorController;
import com.fixplz.stat.application.dto.response.VisitorResponse;
import com.fixplz.stat.domain.aggregate.KeyFactory;
import com.fixplz.stat.domain.aggregate.entity.Visitor;
import com.fixplz.stat.domain.repository.VisitorDomainRepository;
import com.fixplz.stat.domain.service.VisitorDomainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@DisplayName("방문자 관련 테스트 케이스")
class VisitorServiceTests {
    private final LocalDate TODAY = LocalDate.now();
    @Autowired
    private VisitorController visitorController;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private VisitorDomainService visitorDomainService;
    @Autowired
    private VisitorDomainRepository visitorDomainRepository;

    @BeforeEach
    void clear() {
        KeyFactory.clear();
        visitorDomainRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("방문자 중복 검증 테스트: 캐싱되어 있을 경우")
    void verifyVisitorTest() {
        //given
        KeyFactory.from("qwer");
        //when
        boolean result = KeyFactory.from("qwer").status();
        //then
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("방문자 중복 검증 테스트: 캐싱되어 있지 않을 경우")
    void verifyVisitorTest2() {
        //given
        //when
        boolean result = KeyFactory.from("qwer").status();
        //then
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("일일 방문자 수 증가 테스트: 오늘 날짜의 통계 있을 경우")
    void countDailyVisitorTest1() {
        //given
        visitorDomainRepository.save(Visitor.builder()
                .visitDate(TODAY)
                .visitorCount(2)
                .build());
        int todayCount1 = visitorDomainRepository
                .findByVisitDate(TODAY)
                .getVisitorCount();
        //when
        visitorService.countDailyVisitor();
        int todayCount2 = visitorDomainRepository
                .findByVisitDate(TODAY)
                .getVisitorCount();
        //then
        Assertions.assertEquals(todayCount2, todayCount1 + 1);
    }

    @Test
    @DisplayName("일일 방문자 수 증가 테스트: 오늘 날짜의 통계 없을 경우")
    void countDailyVisitorTest2() {
        //given
        //when
        visitorService.countDailyVisitor();
        int todayCount = visitorDomainRepository
                .findByVisitDate(TODAY)
                .getVisitorCount();
        //then
        Assertions.assertEquals(1, todayCount);
    }

    @Test
    @DisplayName("메인 페이지 첫 방문 시 UUID 값 생성 테스트")
    void giveRandomValueTest() {
        //given
        String visitorKey = "";
        //when
        VisitorResponse result = visitorService.getVisitorCount(visitorKey);
        //then
        Assertions.assertNotEquals(visitorKey, result.uuid());
    }
}
