package com.ohgiraffers.section01.aop;

import org.springframework.stereotype.Service;

import java.util.Map;

// 메인기능

@Service
public class MemberService {
    private final MemberDAO memberDAO;
    // 생성자 주입 - 의존성
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
    // 핵심관점(Primary Concern) -> selectMembers
    public Map<Long, MemberDTO> selectMembers(){
        System.out.println("selectMembers 메소드 실행");
        return memberDAO.selectMembers();
    }
    public MemberDTO selectMember(Long id) {
        System.out.println("selectMember 메소드 실행");
        return memberDAO.selectMember(id);
    }
}
