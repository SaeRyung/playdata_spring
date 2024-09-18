package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(
//            value = "SELECT c FROM Category c ORDER BY c.categoryCode",
            value = "SELECT category_code, category_name, ref_category_code " +
                    "FROM tbl_category " +
                    "ORDER BY category_code",
            nativeQuery = true  // JPQL일 경우 생략(default가 false)
    )
    List<Category> findAllCategory();
}