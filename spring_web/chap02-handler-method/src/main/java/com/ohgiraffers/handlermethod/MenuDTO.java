package com.ohgiraffers.handlermethod;
// spring에서 작업해서 넘겨주는 객체 = 커멘드 객체
/* 커멘드 객체로 사용하기 위해서는 name 속성 값과 필드명이 일치하도록 작성해야 한다. */
// 전달받을 값 MenuDTO
public class MenuDTO {
    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }
}
