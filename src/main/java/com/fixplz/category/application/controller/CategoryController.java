package com.fixplz.category.application.controller;

import com.fixplz.category.application.dto.request.CategoryRequest;
import com.fixplz.category.application.dto.response.CategoryResponse;
import com.fixplz.category.application.service.CategoryService;
import com.fixplz.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody @Valid CategoryRequest request,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(ApiResponse.error(bindingResult.getFieldError().getDefaultMessage()));
        }
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("성공적으로 등록되었습니다.", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<CategoryResponse> response = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("성공적으로 조회되었습니다.", response));
    }

    @GetMapping("{categoryNo}")
    public ResponseEntity<ApiResponse> getCategoryByCategoryNo (@PathVariable(name = "categoryNo") Long categoryNo) {
        CategoryResponse response = categoryService.getCategoryByCategoryNo(categoryNo);
        return ResponseEntity.ok().body(ApiResponse.success("성공적으로 조회되었습니다.", response));
    }

    @PutMapping("{categoryNo}")
    public ResponseEntity<ApiResponse> updateCategory (@PathVariable(name = "categoryNo") Long categoryNo,
                                                       @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.updateCategory(categoryNo, request);
        return ResponseEntity.ok().body(ApiResponse.success("성공적으로 수정되었습니다.", response));
    }

    @DeleteMapping("{categoryNo}")
    public ResponseEntity<ApiResponse> deleteCategory (@PathVariable(name = "categoryNo") Long categoryNo) {
        int response = categoryService.deleteCategory(categoryNo);
        return ResponseEntity.ok().body(ApiResponse.success("성공적으로 삭제되었습니다.", response));
    }

}
