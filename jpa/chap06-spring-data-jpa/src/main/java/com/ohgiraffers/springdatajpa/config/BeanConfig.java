package com.ohgiraffers.springdatajpa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 빈등록하여 읽어왔을 때 빈 등록, mvn 추가 모델 매퍼

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        /* setter 메소드 미사용 시 ModelMapper 가 private 필드에 접근 가능하도록 하는 설정*/
        modelMapper.getConfiguration()
                .setFieldAccessLevel(
                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE
                )
                .setFieldMatchingEnabled(true);
                return modelMapper;
    }
}
