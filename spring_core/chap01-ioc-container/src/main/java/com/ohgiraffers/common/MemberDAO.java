package com.ohgiraffers.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/* 컴포넌트 스캔을 통해 빈으로 등록 될 수 있도록 함 */
//@Component -> ioc container가 스캔하여 빈으로 등록된다.
@Component
// 개발자가 직접 제어할 수 없는 외부 라이브러리는 @Bean 메소드를 사용해 수동 빈 추가 ,,, 다형성 활용하고 싶을 때도 @Bean 사용
public class MemberDAO {

    private final Map<Integer, MemberDTO> memberMap;

    public MemberDAO() {
        memberMap = new HashMap<>();
        memberMap.put(1, new MemberDTO(1, "user01", "pass01", "홍길동"));
        memberMap.put(2, new MemberDTO(2, "user02", "pass02", "유관순"));
    }

    public MemberDTO selectMember(int sequence) {
        return memberMap.get(sequence);
    }

    public boolean insertMember(MemberDTO member) {

        int before = memberMap.size();

        memberMap.put(member.getSequence(), member);

        int after = memberMap.size();

        return after > before;
    }

}