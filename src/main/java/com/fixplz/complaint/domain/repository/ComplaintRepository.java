package com.fixplz.complaint.domain.repository;

import com.fixplz.complaint.domain.aggregate.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
