package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 해당 클래스가 빈을 생성하는 설정 클래스임을 표기
@Configuration("configurationSection02")
public class ConfigurationContext {

    @Bean(name="member")
    public MemberDTO getMember() {
        return new MemberDTO(1, "user01", "pass01", "홍길동");
    }
}
