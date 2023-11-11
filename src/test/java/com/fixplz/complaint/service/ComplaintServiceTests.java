//package com.fixplz.complaint.service;
//
//import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
//import com.fixplz.complaint.application.dto.response.CreateComplaintResponse;
//import com.fixplz.complaint.application.service.ComplaintService;
//import com.fixplz.complaint.domain.repository.ComplaintRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//class ComplaintServiceTests {
//
//    @Autowired
//    private ComplaintService complaintService;
//
//    @Autowired
//    private ComplaintRepository complaintRepository;
//
//    @Test
//    @DisplayName("민원 생성 : success")
//    void createComplaint() {
//
//        // given
//        String complaintContent = "민원 내용";
//        String phoneNumber = "01012345678";
//        int filterCategory = 1;
//        int facilityNo = 1;
//        // 이미지 추가
//
//        CreateComplaintRequest request = new CreateComplaintRequest(complaintContent, filterCategory, phoneNumber, facilityNo);
//
//        // when
//        long beforeCnt = complaintRepository.count();
//        CreateComplaintResponse response = complaintService.createComplaint(request);
//        long afterCnt = complaintRepository.count();
//
//        // then
//        Assertions.assertEquals(beforeCnt + 1, afterCnt);
//        Assertions.assertEquals(complaintContent,response.getComplaintContent());
//        Assertions.assertEquals(afterCnt, response.getComplaintNo());
//    }
//
//    @Test
//    @DisplayName("민원 처리 업데이트 : success")
//    void updateComplaintProcess() {
//
//        // given
//
//
//    }
//
//
//
//}
