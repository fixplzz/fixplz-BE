package com.fixplz.Admin.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TBL_ADMIN")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminNo;

    @Column
    private String adminId;

    @Column
    private String adminPassword;

    public Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.adminPassword = builder.adminPassword;

    }

    public static class Builder {
        private String adminId;
        private String adminPassword;

        public Builder adminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder adminPassword(String adminPassword) {
            this.adminPassword = adminPassword;
            return this;
        }

        public Admin builder() {
            return new Admin(this);
        }
    }
}
