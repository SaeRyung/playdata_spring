package com.ohgiraffers.springsecurity.security.dto;
//id,pwd 전달받는 타입

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String userId;
    private String pwd;
}
