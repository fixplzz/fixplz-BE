package com.fixplz.category.application.service;

import com.fixplz.category.application.dto.request.CategoryRequest;
import com.fixplz.category.application.dto.response.CategoryResponse;
import com.fixplz.category.domain.aggregate.entity.Category;
import com.fixplz.category.domain.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     *  신고/제안 메뉴에 모두 사용되는 메소드로,
     *  시설물들의 카테고리를 나누고 관리하는 메소드입니다.
     * @param CategoryRequest - 카테고리 등록을 위한 카테고리 정보를 담고 있는 요청 DTO
     *                              - categoryName : 카테고리 이름
     *                              - categoryExample : 카테고리 예
     * @return CategoryResponse - DB에 등록된 식별 값과 함께 카테고리 정보를 담고 있는 응답 DTO
     *                          - categoryNo : 카테고리 식별 값
     *                          - categoryName : 카테고리 이름
     *                          - categoryExample : 카테고리 예
     */
    public CategoryResponse createCategory(CategoryRequest request) {

        try {
            Category category =
                    new Category.Builder()
                            .categoryName(request.categoryName())
                            .categoryExample(request.categoryExample())
                            .builder();
            Category result = categoryRepository.save(category);
            return CategoryResponse.of(result.getCategoryNo(), request.categoryName(), request.categoryExample());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("중복된 카테고리 명 입니다.");
        }
    }


    /**
     * 모든 신고/제안 카테고리를 조회하는 메소드입니다.
     * @return List<CategoryResponse> - DB에 등록된 카테고리 정보들을 담고 있는 응답 DTO
     */
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new IllegalArgumentException("검색 결과가 없습니다.");
        }
        List<CategoryResponse> categoryList =  categories.stream().map( category -> CategoryResponse.of(
                category.getCategoryNo(),
                category.getCategoryName(),
                category.getCategoryExample()
        )).toList();

        return categoryList;
    }

    /**
     * 카테고리 식별 값으로 특정 신고/제안 카테고리를 조회하는 메소드입니다.
     * @param categoryNo - 특정 카테고리를 식별할 수 있는 식별 값
     * @return CategoryResponse - DB에 등록된 카테고리 정보들을 담고 있는 응답 DTO
     */
    public CategoryResponse getCategoryByCategoryNo(Long categoryNo) {
        Category category = verifyCategoryByCategoryNo(categoryNo);
        return CategoryResponse.of(category.getCategoryNo(), category.getCategoryName(), category.getCategoryExample());
    }


    /**
     * 특정 카테고리를 수정하는 메소드
     * @param categoryNo - 특정 카테고리를 식별할 수 있는 식별 값
     * @param request - 수정될 카테고리 정보를 담고 있는 요청 DTO
     * @return CategoryReesponse - DB에 등록된 카테고리 정보들을 담고 있는 응답 DTO
     */
    public CategoryResponse updateCategory(Long categoryNo, CategoryRequest request) {
        Category category = verifyCategoryByCategoryNo(categoryNo);
        category.update(request.categoryName(), request.categoryExample());
        return CategoryResponse.of(category.getCategoryNo(), category.getCategoryName(), category.getCategoryExample());
    }

    /**
     * 특정 카테고리를 삭제하는 메소드
     * @param categoryNo - 특정 카테고리를 식별할 수 있는 식별 값
     * @return CategoryReesponse - DB에 등록된 카테고리 정보들을 담고 있는 응답 DTO
     */
    public int deleteCategory(Long categoryNo) {
        Category category = verifyCategoryByCategoryNo(categoryNo);
        return categoryRepository.deleteByCategoryNo(category.getCategoryNo());
    }

    private Category verifyCategoryByCategoryNo (Long categoryNo) {
        Category category = categoryRepository.findById(categoryNo)
                .orElseThrow( () -> new IllegalArgumentException("일치하는 카테고리가 없습니다."));
        return category;
    }
}
