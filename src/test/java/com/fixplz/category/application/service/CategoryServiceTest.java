package com.fixplz.category.application.service;

import com.fixplz.category.application.dto.request.CategoryRequest;
import com.fixplz.category.application.dto.response.CategoryResponse;
import com.fixplz.category.domain.aggregate.entity.Category;
import com.fixplz.category.domain.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;


@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void clear() { categoryRepository.deleteAllInBatch();}

    @DisplayName("카테고리 생성 테스트 : success")
    @Test
    void createCategory() {

        // given
        Long beforeSize = categoryRepository.count();
        CategoryRequest request = CategoryRequest.of("test", "test");

        // when
        categoryService.createCategory(request);
        Long afterSize = categoryRepository.count();

        // then
        Assertions.assertThat(afterSize)
                .isEqualTo(beforeSize + 1);
    }

    @DisplayName("카테고리 생성 테스트 : 중복 처리 되는 지 확인")
    @Test
    void testDuplicationCreateCategory() {

        // given
        categoryRepository.save(new Category.Builder()
                .categoryName("test")
                .categoryExample("test")
                .builder());
        CategoryRequest request = CategoryRequest.of("test", "test");

        // when & then
        Assertions.assertThatCode(
                () -> categoryService.createCategory(request)
        ).isInstanceOf(DataIntegrityViolationException.class)
         .hasMessageContaining("중복된 카테고리 명 입니다.");
    }

    @DisplayName("카테고리 전체 조회 테스트 : success")
    @Test
    void getAllCategories() {

        // given
        categoryRepository.save(new Category.Builder()
                        .categoryName("test")
                        .categoryExample("test")
                        .builder());
        categoryRepository.save(new Category.Builder()
                        .categoryName("test2")
                        .categoryExample("test2")
                        .builder());

        // when
        List<CategoryResponse> categories = categoryService.getAllCategories();

        // then
        Assertions.assertThat(categories)
                .hasSizeGreaterThan(1);
    }

    @DisplayName("카테고리 전체 조회 테스트 : 빈 결과 조회 시 예외 확인")
    @Test
    void testGetAllCategoriesException() {

        // given & when & then
        Assertions.assertThatCode(
                () -> categoryService.getAllCategories()
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("검색 결과가 없습니다.");

    }

    @DisplayName("특정 카테고리 조회 테스트 : success")
    @Test
    void getCategoryByCategoryNo() {

        // given
        Category insertedCategory = categoryRepository.save(new Category.Builder()
                .categoryName("test")
                .categoryExample("test")
                .builder());

        // when
        CategoryResponse category = categoryService.getCategoryByCategoryNo(insertedCategory.getCategoryNo());

        // then
        Assertions.assertThat(category.categoryName())
                .isEqualTo("test");
    }

    @DisplayName("특정 카테고리 조회 테스트 : 카테고리 조회 실패 시 예외 처리 되는 지 확인")
    @Test
    void testGetCategoryException() {

        // given
        Long categoryNo = 100L;

        // when & then
        Assertions.assertThatCode(
                        () -> categoryService.getCategoryByCategoryNo(categoryNo)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("일치하는 카테고리가 없습니다.");
    }

    @DisplayName("카테고리 수정 테스트 : success")
    @Test
    void updateCategory() {

        // given
        Category insertedCategory = categoryRepository.save(new Category.Builder()
                    .categoryName("test")
                    .categoryExample("test")
                    .builder());

        // when
        Category category = categoryRepository.findById(insertedCategory.getCategoryNo()).get();

        // then
        Assertions.assertThat(category.getCategoryName())
                .isEqualTo("test");
    }

    @DisplayName("카테고리 수정 테스트 : 기존에 없던 카테고리 수정 요청이 들어왔을 시 예외 확인")
    @Test
    void testWrongRequestUpdateCategory () {

        // given
        categoryRepository.save(new Category.Builder()
                .categoryNo(1L)
                .categoryName("test")
                .categoryExample("test")
                .builder());
        CategoryRequest request = CategoryRequest.of("test2", "test2");

        // when & then
        Assertions.assertThatCode(
                () -> categoryService.updateCategory(2L, request)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("일치하는 카테고리가 없습니다.");

    }

    @DisplayName("카테고리 삭제 테스트 : success")
    @Test
    void deleteCategory() {

        // given
        Category insertedCategory = categoryRepository.save(new Category.Builder()
                .categoryName("test")
                .categoryExample("test")
                .builder());

        // when & then
        Assertions.assertThatCode(
                () -> categoryService.deleteCategory(insertedCategory.getCategoryNo())
        ).doesNotThrowAnyException();
    }

    @DisplayName("카테고리 삭제 테스트 : 없는 카테고리를 삭제 요청할 시 예외 확인")
    @Test
    void testWrongRequestDeleteCategory() {

        // given & when & then
        Assertions.assertThatCode(
                () -> categoryService.deleteCategory(100L)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("일치하는 카테고리가 없습니다.");
    }

}