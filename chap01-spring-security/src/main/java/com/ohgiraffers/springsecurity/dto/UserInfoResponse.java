package com.ohgiraffers.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    private String userId;
    private String email;
    private String name;
}
