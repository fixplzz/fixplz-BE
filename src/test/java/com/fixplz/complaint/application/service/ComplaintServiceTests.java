package com.fixplz.complaint.application.service;

import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
import com.fixplz.complaint.application.dto.request.UpdateProcessingStatusRequest;
import com.fixplz.complaint.application.dto.response.CreateComplaintResponse;
import com.fixplz.complaint.application.dto.response.UpdateProcessingStatusResponse;
import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.complaint.domain.aggregate.vo.FacilityNoVO;
import com.fixplz.complaint.domain.aggregate.vo.FilterCategory;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import com.fixplz.complaint.domain.repository.ComplaintRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ComplaintServiceTests {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private ComplaintRepository complaintRepository;

    private Complaint complaint;

    @BeforeEach
    void setUp() {
        complaintRepository.deleteAllInBatch();

        String complaintContent = "민원 내용";
        String phoneNumber = "01012345678";
        int filterCategory = 0;
        FacilityNoVO facilityNoVO = new FacilityNoVO(1L);
        ProcessingStatus processingStatus = ProcessingStatus.fromInt(0);

        complaint = complaintRepository.save(new Complaint.Builder()
                .complaintContent(complaintContent)
                .processingStatus(processingStatus)
                .facilityNoVO(facilityNoVO)
                .filterCategory(FilterCategory.fromInt(filterCategory))
                .phoneNumber(phoneNumber)
                .build());

        complaintRepository.saveAndFlush(complaint);
    }

    @AfterEach
    void clear() {
        complaintRepository.deleteAllInBatch();
    }

    @Test
    @Transactional
    @DisplayName("민원 생성 : success")
    void createComplaint() {


        // given
        String complaintContent = "민원 내용";
        String phoneNumber = "01012345678";
        int filterCategory = 1;
        Long facilityNo = 1L;
        // 이미지 추가

        CreateComplaintRequest request = new CreateComplaintRequest(complaintContent, filterCategory, phoneNumber, facilityNo);

        // when
        long beforeCnt = complaintRepository.count();
        CreateComplaintResponse response = complaintService.createComplaint(request);
        long afterCnt = complaintRepository.count();
        Complaint savedComplaint = complaintRepository.findById(response.getComplaintNo()).get();

        // then
        Assertions.assertEquals(beforeCnt + 1, afterCnt);
        Assertions.assertEquals(complaintContent,response.getComplaintContent());
        Assertions.assertEquals(FilterCategory.fromInt(1).getText(), savedComplaint.getFilterCategory().getText());

    }

    @Test
    @Transactional
    @DisplayName("민원 처리 업데이트 : success")
    void updateComplaintProcess() {

        // given
        int processingStatus = 1;
        Long complaintNo = 1L;
        UpdateProcessingStatusRequest request = new UpdateProcessingStatusRequest(processingStatus, complaintNo);

        // when
        UpdateProcessingStatusResponse response = complaintService.updateProcessingStatus(request);

        // then
        Assertions.assertEquals(ProcessingStatus.fromInt(processingStatus).getText(), response.getProcessingStatusText());
        Assertions.assertEquals(complaintNo, response.getComplaintNo());
        Assertions.assertEquals(processingStatus, response.getProcessingStatusNum());
    }

}
