package com.fixplz.complaint.infra.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final RedisService redisService;

    @Value("${coolsms.key}")
    private String apiKey;

    @Value("${coolsms.secret}")
    private String apiSecret;

    @Value("${coolsms.number}")
    private String fromNumber;

    public String sendMessage(String toNumber) {

        Message sms = new Message(apiKey, apiSecret);

        String code = String.valueOf((int)(Math.random() * 8999) + 1000);

        redisService.setDataExpire(toNumber, code, 60*5L);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[도로 위 성동구] 인증번호 "+ code +" 를 입력하세요.");

        try {
            JSONObject obj = (JSONObject) sms.send(params);
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        return code;
    }

    public Boolean verifyCode(String toNumber, String code) {

        String codeForSMS = redisService.getData(toNumber);

        if (codeForSMS == null) {
            throw new IllegalArgumentException("인증요청 기록이 없는 전화번호입니다.");
        }

        return codeForSMS.equals(code);

    }
}
