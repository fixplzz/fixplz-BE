package com.fixplz.member.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column
    private String kakaoId;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String email;

    @Column
    private String gender;

    @Column
    private String ageRange;

    @Column
    private int reportCnt;

    @Column
    private Role role;

    @Builder
    public Member(String kakaoId, String nickname, String email, String gender, String ageRange, Role role) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.ageRange = ageRange;
        this.reportCnt = 0;
        this.role = role;
    }
}
