package com.ohgiraffers.common;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
public class MemberDTO {
    private int sequence; //회원번호
    private String name; //이름
    private String phone; //휴대폰번호
    private String email; //이메일
    // MemberDTO는 Account에 의존관계가 있다.
    private Account personalAccount; //개인계좌

    //추상적 타입 Account만 유지된다면 바껴도 상관없다.

    // 초기화
    public MemberDTO(int sequence, String name, String phone, String email, Account personalAccount) {
        this.sequence = sequence;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.personalAccount = personalAccount;
    }
}