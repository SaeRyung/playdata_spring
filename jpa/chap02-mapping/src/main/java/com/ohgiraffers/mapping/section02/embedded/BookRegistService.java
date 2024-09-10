package com.ohgiraffers.mapping.section02.embedded;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookRegistService {

    private BookRepositoty bookRepositoty;

    public BookRegistService(BookRepositoty bookRepositoty) {
        this.bookRepositoty = bookRepositoty;
    }


    // @Transactional 범위 내에서 진행
    @Transactional
    public void registBook(BookRegistDTO newBook){
        // BookRegistDTO를 전달받고

        // 전달받은 BookRegistDTO를 엔티티에 담는다.
        Book book = new Book(
                newBook.getBookTitle(),
                newBook.getAuthor(),
                newBook.getPublisher(),
                newBook.getPublishedDate(),
                new Price(
                        newBook.getRegularPrice(),
                        newBook.getDiscountRate()
                )
        );

        bookRepositoty.save(book);
    }
}
