package com.fixplz.category.domain.repository;

import com.fixplz.category.domain.aggregate.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Modifying(clearAutomatically = true)
    @Query(value = "delete " +
                     "from tbl_category " +
                    "where category_no = :categoryNo", nativeQuery = true)
    int deleteByCategoryNo(@Param("categoryNo") Long categoryNo);
}
