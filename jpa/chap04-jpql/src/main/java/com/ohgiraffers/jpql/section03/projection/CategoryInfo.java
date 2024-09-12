package com.ohgiraffers.jpql.section03.projection;

// 스칼라로 뽑아온 내용 취급할 수 있는 클래스

public class CategoryInfo {

    private int categoryCode;
    private String categoryName;

    public CategoryInfo(int categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }


    @Override
    public String toString() {
        return "CategoryInfo{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
