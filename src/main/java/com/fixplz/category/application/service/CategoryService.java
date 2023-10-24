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

    /**
     *  신고/제안 메뉴에 모두 사용되는 메소드로,
     *  시설물들의 카테고리를 나누고 관리하는 메소드입니다.
     * @param CategoryCreateRequest - 카테고리 등록을 위한 카테고리 정보를 담고 있는 요청 DTO
     *                              - categoryName : 카테고리 이름
     *                              - categoryExample : 카테고리 예
     * @return CategoryResponse - DB에 등록된 식별 값과 함께 카테고리 정보를 담고 있는 응답 DTO
     *                          - categoryNo : 카테고리 식별 값
     *                          - categoryName : 카테고리 이름
     *                          - categoryExample : 카테고리 예
     */
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        Category category =
                new Category.Builder()
                        .categoryName(request.getCategoryName())
                        .categoryExample(request.getCategoryExample())
                        .builder();

        Category result =  categoryRepository.save(category);
        return CategoryResponse.of(result.getCategoryNo(), request.getCategoryName(), request.getCategoryExample());
    }



}
