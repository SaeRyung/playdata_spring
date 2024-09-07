package com.ohgiraffers.springmybatis.config;

import com.ohgiraffers.springmybatis.section01.factorybean.MenuDTO;
import com.ohgiraffers.springmybatis.section01.factorybean.MenuMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// spring 환경 -> spring ioc 환경으로 구동된다.
// spring boot 내 mybatis 환경 설정
@Configuration
public class MybatisConfiguration {

    // yml에 작성된 키값 입력 => 해당값 주입된다,, yml에 작성된 접속정보
    @Value("${spring.datasource.driver-class-name}")
    private String driverCalssName;

    @Value("${spring.datasource.jdbc-url}")
    private String jdbcurl;


    @Value("${spring.datasource.username}")
    private String username;


    @Value("${spring.datasource.password}")
    private String password;


    // 컨트롤하고 싶은 기능 있을 때 기재 후 빈 등록
    @Bean
    // 히카리라는 라이브 기능 이름 존재한다.
    // DB와의 연동할 때 필요한 설정 정보를 넣는 기능
    public HikariDataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        // 히카리데이터소스 객체의 새터메소드에 존재, 각각의 데이터를 새터메소드에 설정
        // 각각 객체를 다룰 수 있도록 세터메소드가 존재한다.
        dataSource.setDriverClassName(driverCalssName);
        dataSource.setJdbcUrl(jdbcurl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        /* 데이터베이스 연결을 시도할 때 타임 아웃 (밀리세컨 단위 - 30초) */
        dataSource.setConnectionTimeout(30000);

        /* 데이터베이스 연결 유휴 상태 (쿼리를 보내지 않고 데이터를 업데이트 하지 않는 시간) 유지 */
        dataSource.setIdleTimeout(60000);

        /* 데이터베이스 연결 최대 유지 시간 (30분) */
        dataSource.setMaxLifetime(1800000);

        /* 미리 만들 커넥션 객체 갯수 */
        dataSource.setMaximumPoolSize(50);

        return dataSource;
    }

    @Bean
    // mybatis 기능을 spring 에서 사용하기 위한 라이브러리 -> SqlSessionFactoryBean 존재
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 별칭설정
        configuration.getTypeAliasRegistry().registerAlias("MenuDTO", MenuDTO.class);
        configuration.addMapper(MenuMapper.class);
        configuration.setMapUnderscoreToCamelCase(true);



        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 위에 선언한 dataSource 를 새터메소드로 의존성주입
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
        // ㄴ sqlSessionFactory type 객체 반환
    }


    @Bean
    // 요청시마다 SqlSession 만들어서 반환하는 기능
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        // 위의 소스가 throws Exception 설정이기에 아래도 설정 해야 한다.
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
