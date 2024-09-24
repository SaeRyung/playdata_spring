package com.ohgiraffers.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;

//userDTO 역할 클래스

@Getter
@Setter
public class CreateUserRequest {
    private String userId;
    private String pwd;
    private String email;
    private String name;
}
