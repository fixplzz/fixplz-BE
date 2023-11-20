package com.fixplz.admin.domain.repository;

import com.fixplz.admin.domain.aggregate.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAdminId(String adminId);
    boolean existsByAdminId(String adminId);

}
