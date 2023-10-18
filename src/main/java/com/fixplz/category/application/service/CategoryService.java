package com.fixplz.category.application.service;

import com.fixplz.category.application.dto.request.CategoryCreateRequest;
import com.fixplz.category.application.dto.response.CategoryResponse;
import com.fixplz.category.domain.aggregate.entity.Category;
import com.fixplz.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        Category category =
                new Category.Builder()
                        .categoryName(request.categoryName())
                        .categoryExample(request.CategoryExample())
                        .builder();

        Category result =  categoryRepository.save(category);
        return CategoryResponse.of(result.getCategoryNo(), request.categoryName(), request.CategoryExample());
    }
}
