package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.autowired.common.BookDAO;
import com.ohgiraffers.section01.autowired.common.BookDAOImpl;
import com.ohgiraffers.section01.autowired.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/* @Component의 세분화 어노테이션으로 Service 계층에서 사용한다. */
@Service("bookServiceField")
public class BookService {
    // BookDAO를 의존 클래스
//    public BookDAO bookDAO = new BookDAOImpl();
    //구체적 타입이 사용되므로 영향을 미쳐서 안된다.

    /* BookDAO 타입의 빈 객체를 이 프로퍼티에 자동으로 주입해준다.*/
    @Autowired //-> 필드주입
    public BookDAO bookDAO;

    public List<BookDTO> selectAllBooks(){
        return bookDAO.selectBookList();
    }
    public BookDTO searchBookBySequence(int sequence){
        return bookDAO.selectOneBook(sequence);
    }
}
