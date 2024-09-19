package com.ohgiraffers.restapi.section03.valid;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

// 에러 확인 클래스

@RestController
@RequestMapping("/valid")
public class ValidTestController {

    @GetMapping("/users/{userNo}")
    // 임의로 오류 메소드 정의
    public ResponseEntity<Void> findUserByUserNo(@PathVariable int userNo) throws UserNotFoundException {
        boolean check = true;
        // ㄴ if문에서 무조건 true > exception 발생
        if(check) throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");

        return ResponseEntity.ok().build();
    }



    // UserDTO 받아올 때 검사하도록, @Valid 로 검사하여, 제공하는 어노테이션으로 가능하다. -> UserDTO.java
    /* @Valid : 클라이언트에서 전달 되는 파라미터에 대한 유효성 검사를 수행. UserDTO 내부의 어노테이션 설정에 따른다. */
    @PostMapping("/users")
    public ResponseEntity<Void> registUser(@Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.created(URI.create("/valid/users/1")).build();
    }







}