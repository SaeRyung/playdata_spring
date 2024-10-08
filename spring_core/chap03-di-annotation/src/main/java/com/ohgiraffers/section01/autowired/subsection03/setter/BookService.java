package com.ohgiraffers.section01.autowired.subsection03.setter;

import com.ohgiraffers.section01.autowired.common.BookDAO;
import com.ohgiraffers.section01.autowired.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// @Component의 세분화 어노테이션으로 Service 계층에서 사용한다.
@Service("bookServiceSetter")
public class BookService {

    private /*final*/ BookDAO bookDAO; //생성과 동시에 초기화x

    // setter 주입
    //Q.setter 주입의 개념 다시----------------------
    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<BookDTO> selectAllBooks() {
        return bookDAO.selectBookList();
    }

    public BookDTO searchBookBySequence(int sequence) {
        return bookDAO.selectOneBook(sequence);
    }
}