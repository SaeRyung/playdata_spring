package com.ohgiraffers.mapping.section01.entity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// MemberRepository를 의존하는 클래스
// 서비스계층에서 DTO 타입 값이 넘어온다.

@Service
public class MemberRegistService {

    private MemberRepository memberRepository;

    // 생성자 통한 의존성 주입
    public MemberRegistService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    @Transactional
    //ㄴ 메소드 호출 시점 가로채 시작 전 트랜잭션 begin, 종료 전에 commit 수행하는 어노테이션
    // 중간에 exception 발생 시 rollback 기능 포함된다.
    public void registerMember(MemberRegistDTO newMember){
        // 엔티티는 DB와 직접적 매핑->서비스계층에서 생성하여 사용, 서비스계층에서 DTO로 변경 후 사용이 안전하다.
        // DB와 실제 매핑된 데이터를 변경하여 DB에 변경 생길 수 있으므로
        // 서비스계층에서 DTO 타입 값이 넘어온다.


        // 넘어온 데이터 엔티티로 변경
        Member member = new Member(
                newMember.getMemberId(),
                newMember.getMemberPwd(),
                newMember.getMemberName(),
                newMember.getPhone(),
                newMember.getAddress(),
                newMember.getEnrollDate(),
                newMember.getMemberRole(),
                newMember.getStatus()

        );
        memberRepository.save(member);
    }


    /* Spring의 트랜잭션 전파 방식(default : PROPAGATION_REQUIRED)에 따라
    * @Transactional의 붙은 메소드가 다른 @Transactional이 붙은 메소드를 호출하면
    * 호출 된 메소드는 호출한 메소드와 동일한 트랜잭션 컨텍스트를 사용한다. */
    @Transactional
    public String registMemberAndFindName(MemberRegistDTO newMember){
        registerMember(newMember);
        return memberRepository.findNameById(newMember.getMemberId()); // 이름을 찾는다.
    }
}
