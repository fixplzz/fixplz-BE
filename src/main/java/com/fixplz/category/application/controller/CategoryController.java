package com.fixplz.category.application.controller;

import com.fixplz.category.application.dto.request.CategoryCreateRequest;
import com.fixplz.category.application.dto.response.CategoryResponse;
import com.fixplz.category.application.service.CategoryService;
import com.fixplz.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(CategoryCreateRequest request) {
        System.out.println("request = " + request);
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("성공적으로 등록되었습니다.", response));
    }

}
