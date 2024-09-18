package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MenuDTO {

    private int menuCode;
    private String menuName;
    private int menuPrice;
    private CategoryDTO category;
//    private Category category;
    // ㄴ 연관관계 매핑을 했기에 DTO 클래스가 Category 엔티티를 본인 필드 참조 X
    // CategoryDTO 필드 따로 만들어서 처리한다.
    private String orderableStatus;

    public MenuDTO(int menuCode, String menuName, int menuPrice, CategoryDTO category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }


    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
