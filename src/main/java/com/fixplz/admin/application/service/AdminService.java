package com.fixplz.admin.application.service;

import com.fixplz.admin.application.dto.request.AdminRequest;
import com.fixplz.admin.application.dto.response.AdminResponse;
import com.fixplz.admin.application.dto.response.LoginResponse;
import com.fixplz.admin.domain.aggregate.entity.Admin;
import com.fixplz.admin.domain.aggregate.entity.Role;
import com.fixplz.admin.domain.repository.AdminRepository;
import com.fixplz.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AdminResponse register(AdminRequest request) {

        System.out.println("SERVICE request = " + request);

        if (adminRepository.existsByAdminId(request.adminId())) {
            throw new RuntimeException("이미 등록된 관리자입니다");
        }

        Admin admin = new Admin.Builder()
                .adminId(request.adminId())
                .adminPassword(passwordEncoder.encode(request.adminPassword()))
                .role(Role.ROLE_ADMIN)
                .builder();

        adminRepository.save(admin);

//        return AdminResponse.from(admin);
        return new AdminResponse(admin.getAdminNo(), admin.getAdminId());
    }

    @Transactional(readOnly = true)
    public LoginResponse login(AdminRequest request) {

        Admin admin = adminRepository.findByAdminId(request.adminId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(request.adminPassword(), admin.getAdminPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                admin, null, admin.getAuthorities());

        LoginResponse response = new LoginResponse(jwtTokenProvider.generateAccessToken(admin));

        return response;
    }

    @Transactional(readOnly = true)
    public Long getMemberInfo(String token) {

        Long result = jwtTokenProvider.getAdminNoFromToken(token);

        return result;
    }
}
