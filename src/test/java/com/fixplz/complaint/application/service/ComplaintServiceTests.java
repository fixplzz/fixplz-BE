package com.fixplz.complaint.application.service;

import com.fixplz.complaint.application.dto.request.CreateComplaintRequest;
import com.fixplz.complaint.application.dto.request.UpdateProcessingStatusRequest;
import com.fixplz.complaint.application.dto.response.CreateComplaintResponse;
import com.fixplz.complaint.application.dto.response.GetComplaintListResponse;
import com.fixplz.complaint.application.dto.response.GetComplaintResponse;
import com.fixplz.complaint.application.dto.response.UpdateProcessingStatusResponse;
import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.complaint.domain.aggregate.vo.FacilityNoVO;
import com.fixplz.complaint.domain.aggregate.vo.FilterCategory;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import com.fixplz.complaint.domain.repository.ComplaintRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ComplaintServiceTests {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private ComplaintRepository complaintRepository;

    private Complaint complaint1;
    private Complaint complaint2;

    @BeforeEach
    void setUp() {
        complaintRepository.deleteAllInBatch();

        String complaintContent = "민원 내용";
        String phoneNumber = "01012345678";
        int filterCategory = 0;
        FacilityNoVO facilityNoVO = new FacilityNoVO(1L);
        ProcessingStatus processingStatus = ProcessingStatus.fromInt(0);

        complaint1 = complaintRepository.save(new Complaint.Builder()
                .complaintContent(complaintContent)
                .processingStatus(processingStatus)
                .facilityNoVO(facilityNoVO)
                .filterCategory(FilterCategory.fromInt(filterCategory))
                .phoneNumber(phoneNumber)
                .build());

        complaintRepository.saveAndFlush(complaint1);

        complaint2 = complaintRepository.save(new Complaint.Builder()
                .complaintContent(complaintContent)
                .processingStatus(processingStatus)
                .facilityNoVO(facilityNoVO)
                .filterCategory(FilterCategory.fromInt(filterCategory))
                .phoneNumber(phoneNumber)
                .build());

        complaintRepository.saveAndFlush(complaint2);
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
        Assertions.assertEquals(complaintContent, response.getComplaintContent());
        Assertions.assertEquals(FilterCategory.fromInt(1).getText(), savedComplaint.getFilterCategory().getText());

    }

    @Test
    @Transactional
    @DisplayName("민원 처리 업데이트 : success")
    void updateComplaintProcess() {

        // given
        int processingStatus = 1;
        Long complaintNo = complaint1.getComplaintNo();
        UpdateProcessingStatusRequest request = new UpdateProcessingStatusRequest(processingStatus, complaintNo);

        // when
        UpdateProcessingStatusResponse response = complaintService.updateProcessingStatus(request);

        // then
        Assertions.assertEquals(ProcessingStatus.fromInt(processingStatus).getText(), response.getProcessingStatusText());
        Assertions.assertEquals(complaintNo, response.getComplaintNo());
        Assertions.assertEquals(processingStatus, response.getProcessingStatusNum());
    }

    @Test
    @Transactional
    @DisplayName("민원 전체 조회 : success")
    void getComplaintList() {

        // given
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "complaintNo");
        long entityCnt = complaintRepository.count();

        // when
        Page<GetComplaintListResponse> response = complaintService.getComplaintList(pageable);

        // then
        Assertions.assertEquals(entityCnt, response.getTotalElements());
    }

    @Test
    @Transactional
    @DisplayName("민원 상세 조회 : success")
    void getComplaint() {

        // given
        Long complaintNo = 1L;

        // when
        GetComplaintResponse response = complaintService.getComplaint(complaintNo);

        //then
        Assertions.assertEquals(complaint1.getComplaintContent(), response.getComplaintContext());
        Assertions.assertEquals(complaint1.getProcessingStatus().getText(), response.getProcessingStatus());
        Assertions.assertEquals(complaint1.getFilterCategory().getText(), response.getFilterCategory());
    }
}
