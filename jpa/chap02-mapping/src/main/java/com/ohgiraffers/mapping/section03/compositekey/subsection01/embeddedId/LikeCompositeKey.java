package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

// Like class에서 사용할 복합키 클래스
// 엔티티에 내장하기 위해 내장 가능 어노테이션(@Embeddable)을 작성


@Embeddable
public class LikeCompositeKey implements Serializable {
    /* 복합키는 Serializable 사용
    id로 사용한다, LikeCompositeKey(type)
    => PK 값이 되므로, id값 관리 위해 구현해야한다.
    영속성 컨텍스트 내부에 pk값이 type일 경우 Serializable 구현해야한다.
    ㄴ 객체를 식별 가능한 상태로 유지하기 위해
    */

    // 복합키요소 @Column
    @Column(name = "liked_member_no")
    private int likedMemberNo;

    @Column(name = "liked_book_no")
    private int likedBookNo;

    public LikeCompositeKey() {
    }

    public LikeCompositeKey(int likedMemberNo, int likedBookNo) {
        this.likedMemberNo = likedMemberNo;
        this.likedBookNo = likedBookNo;
    }
}
