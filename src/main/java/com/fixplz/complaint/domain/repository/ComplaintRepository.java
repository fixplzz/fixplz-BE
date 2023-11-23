package com.fixplz.complaint.domain.repository;

import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import com.fixplz.complaint.domain.aggregate.vo.ProcessingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tbl_complaint c SET c.processing_status = :#{#processingStatus.ordinal()} WHERE c.complaint_no = :complaintNo", nativeQuery = true)
    int updateComplaint(@Param("complaintNo") Long complaintNo, @Param("processingStatus") ProcessingStatus processingStatus);

}
