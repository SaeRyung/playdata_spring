package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ContextConfiguration {

    // messageSource 정해진 이름이다. 이렇게 사용함
    //@Bean: 제공하고 있는 기능을 설정해서 등록할 때 선언
    // messageSource : 메세지를 바꾸고 싶을때 기능
    @Bean
    public MessageSource messageSource(){
        /* 접속하는 세션의 로케일에 따라 자동 재로딩 하는 용도의 MessageSource 구현체*/
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        /* 다국어 메세지를 읽어올 properties 파일의 이름을 설정 */
        messageSource.setBasename("section03/properties/subsection02/message");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
