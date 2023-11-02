package com.fixplz.admin.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Table(name = "TBL_ADMIN")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Admin{

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
