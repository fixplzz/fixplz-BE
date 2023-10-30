package com.fixplz.Admin.domain.repository;

import com.fixplz.Admin.domain.aggregate.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
