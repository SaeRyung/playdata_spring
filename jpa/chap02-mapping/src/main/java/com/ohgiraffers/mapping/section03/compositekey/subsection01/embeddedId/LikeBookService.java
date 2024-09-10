package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeBookService {

    private LikeRepository likeRepository;

    public LikeBookService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }


//  @PersistenceContext 하기 위해 반드시 @Transactional 발생하여 실행해야 한다.
    @Transactional
    public void generateLikeBook(LikeDTO likeDTO){
        //1. LikeDTO를 기반으로


        Like like = new Like(// 2. LikeEntity를 만들어서
                new LikeCompositeKey( // 3. 복합키 내로 값을 가져온 후 Like에 넣기
                        likeDTO.getLikeMemberNo(),
                        likeDTO.getLikedBookNo()
                )
        );

        likeRepository.save(like); //4. LikeEntity를 save 요청
    }
}
