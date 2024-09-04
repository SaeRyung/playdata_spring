package com.ohgiraffers.section02.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// mybatis xml 방식

public class Application {
    public static void main(String[] args) {


        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // ㄴ 1. xml 파일 읽어올 스트림 설정
            // getResourceAsStream : 입출력 수행할 동작, IO Exceptioin 추가
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // ㄴ 2. build 전달 > xml 파일로 전달하기 위해 inputStream. xml 파일을 읽어올 스트림을 설정해라

            // sqlSession 객체 생성
            SqlSession sqlSession = sqlSessionFactory.openSession(false);

            /* selectOne : 조회 결과가 1행인 경우 사용하는 메소드
            * mapper.xml 파일의 namespace와 select 태그의 id를 통해 수행 구문을 찾아 온다. (namespace.id) */
            java.util.Date date = sqlSession.selectOne("mapper.selectDate");
            System.out.println("date = " + date);
            sqlSession.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
