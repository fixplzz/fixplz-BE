package com.fixplz.complaint.application.controller;

import com.fixplz.complaint.application.dto.request.SendSMSRequest;
import com.fixplz.complaint.application.dto.request.ValidateSMSRequest;
import com.fixplz.complaint.infra.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/complaint")
public class ComplaintController {

    private final MessageService messageService;

    //요청된 전화번호로 인증번호 전송
    //임시로 String 을 리턴합니다. 문자 전송을 이용하면 비용이 발생하기 때문에 임의로 코드를 리턴하여 PostMan 을 통해 발급된 코드를 확인할 수 있도록 해놨습니다.
    @PostMapping("/sms/send")
    public String testSMSSend(@RequestBody SendSMSRequest request) {
       return messageService.sendMessage(request.getPhoneNo());
    }

    //인증번호 일치 여부 확인 -> 임시로 만들어놨습니다. verifiedCode 의 경우 boolean 을 리턴합니다.
    @PostMapping("/sms/validate")
    public String testSMSValidate(@RequestBody ValidateSMSRequest request) {
        if(messageService.verifyCode(request.getPhoneNo(), request.getCode())) {
            return "번호 인증 성공";
        }
        else return "번호 인증 실패";
    }

}
