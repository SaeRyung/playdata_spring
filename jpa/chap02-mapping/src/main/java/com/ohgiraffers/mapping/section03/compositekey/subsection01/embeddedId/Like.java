package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;
// 엔티티

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_like")
public class Like {


    // @EmbeddedId: 복합키에 대한 표시
    @EmbeddedId
    private LikeCompositeKey likeInfo;

    public Like(){}

    public Like(LikeCompositeKey likeInfo){
        this.likeInfo = likeInfo;
    }
}
