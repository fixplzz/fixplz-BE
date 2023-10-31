package com.fixplz.stat.application.service;

import com.fixplz.stat.application.dto.response.VisitorResponse;
import com.fixplz.stat.domain.aggregate.KeyFactory;
import com.fixplz.stat.domain.aggregate.entity.Visitor;
import com.fixplz.stat.domain.repository.VisitorDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

/**
 * countDailyVisitor() 메소드를 실행하면
 * 1. 오늘 날짜로 방문자 테이블에 오늘 날짜의 기록이 있는 지 체크한다.
 * 2. 만약 기록이 없으면 오늘 날짜의 기록을 새로 만든다.
 * 3. 기록이 있으면 방문자 카운트를 +1 시켜준다.
 */
@Service
@RequiredArgsConstructor
public class VisitorService {
    private final VisitorDomainRepository visitorDomainRepository;
    private final LocalDate TODAY = LocalDate.now();

    // 일일 방문자 등록하는 메소드 - 조만제
    public VisitorResponse getVisitorCount(String visitorKey) {
        // 첫 방문일 경우
        if (visitorKey == null || visitorKey.equals("")) {
            String uuid = UUID.randomUUID().toString();
            countDailyVisitor();
            return new VisitorResponse(uuid, true);
        }

        // N번째 방문일 경우 중복 검증
        // visitorKey가 캐싱되어 있으면 중복이므로 true 반환받고
        // 캐싱 안되어 있으면 중복이 아니므로 저장소에 새로 저장하고 false 반환
        boolean verifyResult = KeyFactory.from(visitorKey).status();

        // 중복 방문일 경우
        if (verifyResult) {
            return new VisitorResponse(visitorKey, false);
        }

        // 중복 방문이 아닐 경우
        countDailyVisitor();
        return new VisitorResponse(visitorKey, true);
    }

    // 일일 방문자 수 증가 메소드 - 조만제
    void countDailyVisitor() {
        Visitor todayVisitor = visitorDomainRepository.findByVisitDate(TODAY);

        // 일일 방문자 통계 존재하지 않을 경우
        if (todayVisitor == null) {
            visitorDomainRepository
                    .save(Visitor.builder()
                            .visitDate(TODAY)
                            .visitorCount(1).build());
        }

        // 일일 방문자 통계 존재할 경우
        if (todayVisitor != null) {
            int result = visitorDomainRepository.updateByToday(TODAY);
            if (result == 1) {
                visitorDomainRepository.findByVisitDate(TODAY);
            }
        }
    }
}