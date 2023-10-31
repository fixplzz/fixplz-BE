package com.fixplz.stat.domain.aggregate;

import com.fixplz.stat.domain.aggregate.vo.VisitorKey;

import java.util.HashMap;
import java.util.Map;

// Visitor 객체를 생성하고 관리하는 Flyweight 팩토리 클래스
public class KeyFactory {
    // Visitor 객체를 저장하는 캐싱 저장소 역할
    private static final Map<String, VisitorKey> cache = new HashMap<>();

    // 방문자 키 객체 싱글톤으로 생성
    public static VisitorKey from(String visitorKey) {
        if (cache.containsKey(visitorKey)) {
            // 캐싱 되어있으면 그대로 가져와 반환
            cache.put(visitorKey, new VisitorKey(visitorKey, true));
            return cache.get(visitorKey);
        }
        // 캐싱 되어있지 않으면 새로 생성하고 캐싱하고 반환
        VisitorKey newKey = new VisitorKey(visitorKey, false);
        cache.put(visitorKey, newKey);
        return newKey;
    }

    // 특정 키값 삭제
    public static void remove(String visitorKey) {
        cache.remove(visitorKey);
    }

    // 키값 전체 삭제
    public static void clear() {
        cache.clear();
    }
}
