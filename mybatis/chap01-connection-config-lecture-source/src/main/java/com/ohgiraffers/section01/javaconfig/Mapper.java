package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.annotations.Select;
// Application 에서 반환한 sqlSession 으로 동작시킬 간단한 코드 인터페이스
public interface Mapper {
    @Select("SELECT NOW()")
    java.util.Date selectDate();
}
