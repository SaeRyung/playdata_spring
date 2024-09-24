package com.ohgiraffers.springsecurity.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// ㄴ 외부에서 엔티티 생성 막아주기
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String email;
    private String pwd;
    private String name;
    @Enumerated(EnumType.STRING) // 문자열 취급
    private UserRole userRole = UserRole.USER; // default로 user 권한 주기

    public void encryptPassword(String encodedPwd) {
        this.pwd = encodedPwd;
    }

}
