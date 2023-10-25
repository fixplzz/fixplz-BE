package com.fixplz.category.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryNo;

    @Column(unique = true)
    private String categoryName;

    @Column
    private String categoryExample;

    public Category(Builder builder) {
        this.categoryNo = builder.categoryNo;
        this.categoryName = builder.categoryName;
        this.categoryExample = builder.categoryExample;
    }

    public void update(String categoryName, String categoryExample) {
        this.categoryName = categoryName;
        this.categoryExample = categoryExample;
    }

    public static class Builder {

        private Long categoryNo;
        private String categoryName;
        private String categoryExample;

        public Builder categoryNo(Long categoryNo) {
            this.categoryNo = categoryNo;
            return this;
        }

        public Builder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder categoryExample(String categoryExample) {
            this.categoryExample = categoryExample;
            return this;
        }

        public Category builder() {
            return new Category(this);
        }

    }
}
