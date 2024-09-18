package com.ohgiraffers.nativequery.section02.namedquery;

import jakarta.persistence.*;

// 순수한 SQL 구문 이름을 정하여 매핑, 사용하기

@SqlResultSetMapping(name = "categoryCountAutoMapping2",
        entities = {@EntityResult(entityClass = Category.class)},
        columns = {@ColumnResult(name = "menu_count")}
)
@NamedNativeQueries(
        value = {
                @NamedNativeQuery(
                        // 쿼리문 이름 지정
                        name = "Category.menuCountOfCategory",
                        // 수행하고 싶은 이름
                        query = "SELECT a.category_code, a.category_name, a.ref_category_code," +
                                " COALESCE(v.menu_count, 0) menu_count" +
                                " FROM tbl_category a" +
                                " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                                " FROM tbl_menu b" +
                                " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                                " ORDER BY 1",
                        resultSetMapping = "categoryCountAutoMapping2" //결과매핑
                )
        }
)

@Entity(name = "Section02Category")
@Table(name = "tbl_category")
public class Category {

    @Id
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}