package com.fixplz.category.application.dto.request;

import lombok.*;

@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString
public class CategoryCreateRequest {

    private final String categoryName;

    private final String categoryExample;

    public CategoryCreateRequest(Builder builder) {
        this.categoryName = builder.categoryName;
        this.categoryExample = builder.categoryExample;
    }

    public static class Builder {

        private String categoryName;
        private String categoryExample;

        public Builder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder categoryExample(String categoryExample) {
            this.categoryExample = categoryExample;
            return this;
        }

        public CategoryCreateRequest builder() {
            return new CategoryCreateRequest(this);
        }
    }




}
