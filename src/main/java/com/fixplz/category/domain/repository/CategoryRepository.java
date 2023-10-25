package com.fixplz.category.domain.repository;

import com.fixplz.category.domain.aggregate.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE " +
                     "FROM TBL_CATEGORY " +
                    "WHERE CATEGORY_NO = :categoryNo", nativeQuery = true)
    int deleteByCategoryNo(@Param("categoryNo") Long categoryNo);
}
