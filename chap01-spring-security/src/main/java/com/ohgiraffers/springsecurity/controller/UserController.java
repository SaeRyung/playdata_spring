package com.ohgiraffers.springsecurity.controller;

import com.ohgiraffers.springsecurity.dto.CreateUserRequest;
import com.ohgiraffers.springsecurity.dto.UserInfoResponse;
import com.ohgiraffers.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// ㄴ final 생성자 전달받기 때문에 final
public class UserController {

    private final UserService userService;

    /* 회원 가입 기능*/
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest newUser) {

        userService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 특정 유저 조회 기능 */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfoById(@PathVariable Long id){
        UserInfoResponse userInfoResponse = userService.getUserInfoById(id);

        return ResponseEntity.ok(userInfoResponse);
    }
}
