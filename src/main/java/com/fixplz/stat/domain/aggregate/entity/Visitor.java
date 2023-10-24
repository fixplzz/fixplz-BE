package com.fixplz.stat.domain.aggregate.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_VISITOR")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long visitorNo;

    @Column
    LocalDate visitDate;

    @Column(nullable = false)
    @ColumnDefault("1")
    int visitorCount;

    @Builder
    public Visitor(Long visitorNo, LocalDate visitDate, int visitorCount) {
        this.visitorNo = visitorNo;
        this.visitDate = visitDate;
        this.visitorCount = visitorCount;
    }
}
