package com.fixplz.stat.application.service;

import com.fixplz.stat.domain.aggregate.entity.Visitor;
import com.fixplz.stat.domain.repository.VisitorDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional
public class VisitorService {
    /*
        countDailyVisitor() 메소드를 실행하면
        1. 오늘 날짜로 방문자 테이블에 오늘 날짜의 기록이 있는 지 체크한다.
        2. 만약 기록이 없으면 오늘 날짜의 기록을 새로 만든다.
        3. 기록이 있으면 방문자 카운트를 +1 시켜준다.
    */
    private final VisitorDomainRepository visitorDomainRepository;
    private final LocalDate TODAY = LocalDate.now();

    // 일일 방문자 등록하는 메소드 - 조만제
    public int countDailyVisitor() {
        Visitor todayVisitor = visitorDomainRepository.findByVisitDate(TODAY);

        if (todayVisitor == null) {
            visitorDomainRepository.save(Visitor.builder().visitDate(TODAY).visitorCount(1).build());
            return 1;
        }
        return visitorDomainRepository.updateByToday(TODAY);
    }

    // 방문자 중복 체크하는 메소드 - 조만제
    private void validateVisitor() {

    }
}