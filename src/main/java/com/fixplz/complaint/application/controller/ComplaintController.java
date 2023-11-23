package com.fixplz.complaint.application.controller;

import com.fixplz.common.response.ApiResponse;
import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
import com.fixplz.complaint.application.dto.request.UpdateProcessingStatusRequest;
import com.fixplz.complaint.application.dto.response.*;
import com.fixplz.complaint.application.service.ComplaintService;
import com.fixplz.complaint.application.dto.request.SendSMSRequest;
import com.fixplz.complaint.application.dto.request.ValidateSMSRequest;
import com.fixplz.complaint.infra.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final MessageService messageService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createComplaint(@RequestBody CreateComplaintRequest request) {

        CreateComplaintResponse response = complaintService.createComplaint(request);

        return ResponseEntity.ok().body(ApiResponse.success("민원 등록 성공", response));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getComplaintList(Pageable pageable) {

        Page<GetComplaintListResponse> response = complaintService.getComplaintList(pageable);

        return ResponseEntity.ok().body(ApiResponse.success("민원 전체 조회 성공", response));
    }

    @PutMapping("/{complaintNo}")
    public ResponseEntity<ApiResponse> updateProcessingStatus(@PathVariable("complaintNo") Long complaintNO, @RequestBody UpdateProcessingStatusRequest request) {

        UpdateProcessingStatusResponse response = complaintService.updateProcessingStatus(request, complaintNO);

        return ResponseEntity.ok().body(ApiResponse.success("민원 진행 상황 업데이트 성공", response));
    }

    @GetMapping("/{complaintNo}")
    public ResponseEntity<ApiResponse> getComplaint(@PathVariable("complaintNo") Long complaintNo) {

        GetComplaintResponse response = complaintService.getComplaint(complaintNo);

        return ResponseEntity.ok().body(ApiResponse.success("민원 상세조회 성공", response));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse> getFacilityInComplaint(@RequestParam("facilityNo") Long facilityNo) {

        GetComplaintPageInfo response = complaintService.getComplaintPageInfoWithFacility(facilityNo);

        return ResponseEntity.ok().body(ApiResponse.success("민원 페이지 조회 성공", response));
    }

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
