package com.ohgiraffers.springsecurity.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// bean 등록용 클래스 -> 설정용 클래스
// AppConfig 를 빈 등록하고 클래스 내에서 ModelMapper도 빈 등록
@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(
                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE
                        // setter 없어도 엔티티 쪽에 값을 설정하는 동작 할 수 있도록 설정
                )
                .setFieldMatchingEnabled(true)
                // 필드값 매칭 활성화
                .setMatchingStrategy(MatchingStrategies.STRICT);
        // id, userid 처럼 중복내용 있을때 , 유사한 필드명 오류 발생 방지 > 안전성
        // dto <-> entity 로 변환할때 필드 자동 매핑 라이브러리 사용 가능,,
        return modelMapper;
    }

    // spring security 라이브러리 추가 후 빈 추가
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
