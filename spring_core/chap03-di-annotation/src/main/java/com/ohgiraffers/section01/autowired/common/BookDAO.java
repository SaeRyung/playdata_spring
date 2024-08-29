package com.ohgiraffers.section01.autowired.common;

import java.util.List;
//DAO: data access object - 데이터 접근 객체
public interface BookDAO {
    /* 도서 목록 전체 조회 */
    List<BookDTO> selectBookList();
    /* 도서 번호로 도서 조회 */
    BookDTO selectOneBook(int sequence);
    //Q 인터페이스 BookDTO 타입 선언?----------
}