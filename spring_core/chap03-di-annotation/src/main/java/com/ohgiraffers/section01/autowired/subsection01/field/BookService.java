package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.autowired.common.BookDAO;
import com.ohgiraffers.section01.autowired.common.BookDAOImpl;
import com.ohgiraffers.section01.autowired.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/* @Component의 세분화 어노테이션으로 Service 계층에서 사용한다. */
// ** 1. 빈등록 위한 annotation 붙이기(@Service)
@Service("bookServiceField")
// 의존성테스트 위한 클래스
public class BookService {
    // BookDAO를 의존 클래스,
//    public BookDAO bookDAO = new BookDAOImpl();
    //구체적 타입이 사용되므로 영향을 미쳐서 안된다.
    // Q. 구체적 타입 사용되어 영향을 미친다는 의미 설명-------------
//    private BookDAO bookDAO;

    /* BookDAO 타입의 빈 객체를 이 프로퍼티에 자동으로 주입해준다.*/
    // Q. 빈객체?-------------------------
    // @Autowired: ioc에게 알려줌. 자동으로 BookDAO 빈 객체 있으면 넣어라. 의존성 주입해 주세요!
    // 의존성 주입 가장 대표적 어노테이션, 위치에 붙느냐에 따라 어떤 주입이다 라고 한다.
    @Autowired //-> 필드주입, 해당타입이 의존성 주입이 되어 있을 것이다.
    public BookDAO bookDAO;

    public List<BookDTO> selectAllBooks(){
        return bookDAO.selectBookList();
    }
    public BookDTO searchBookBySequence(int sequence){
        return bookDAO.selectOneBook(sequence);
    }
}
