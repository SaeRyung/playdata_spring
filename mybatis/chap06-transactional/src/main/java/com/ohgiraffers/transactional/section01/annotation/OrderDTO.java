package com.ohgiraffers.transactional.section01.annotation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/* 테이블의 컬럼과 클래스의 필드가 꼭 일치할 필요 없이 주문을 위해서 사용자가 넘겨 준 값을 담아
* 계층을 넘어다니며 값을 옮겨 줄 수 있는 클래스를 작성한다.
* (DTO : Data Transfer Object)*/
// 테이블을 담고 계층을 넘나드는 클래스, 요청 때 마다 필요한 컨트롤러 > 서비스 계층으로 옮긴다.
// 기능에 맞춰서 정의하는 클래스이므로 테이블과 일치하지 않는다.

// 가공된 정보 받아올 예정
public class OrderDTO {
    private LocalDate orderDate;
    private LocalTime orderTime;
    private List<OrderMenuDTO> orderMenus; // 짜장면, 짬뽕 주문 들어온 리스트

    public OrderDTO() {
    }

    public OrderDTO(LocalDate orderDate, LocalTime orderTime, List<OrderMenuDTO> orderMenus) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderMenus = orderMenus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderMenuDTO> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(List<OrderMenuDTO> orderMenus) {
        this.orderMenus = orderMenus;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", orderMenus=" + orderMenus +
                '}';
    }
}
