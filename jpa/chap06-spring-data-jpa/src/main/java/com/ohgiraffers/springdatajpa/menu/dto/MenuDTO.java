package com.ohgiraffers.springdatajpa.menu.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MenuDTO {

    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

}