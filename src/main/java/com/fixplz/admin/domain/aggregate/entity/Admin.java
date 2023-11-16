package com.fixplz.admin.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Table(name = "TBL_ADMIN")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Admin implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminNo;

    @Column
    private String adminId;

    @Column
    private String adminPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.adminPassword = builder.adminPassword;
        this.role = builder.role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return adminId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static class Builder {
        private String adminId;
        private String adminPassword;
        private Role role;

        public Builder adminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder adminPassword(String adminPassword) {
            this.adminPassword = adminPassword;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Admin builder() {
            return new Admin(this);
        }
    }
}
