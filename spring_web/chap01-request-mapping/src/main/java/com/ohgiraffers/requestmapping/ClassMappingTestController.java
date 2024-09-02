package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/* 클래스 레벨에도 @RequestMapping 사용이 가능하다.
 * URL을 공통 부분을 이용해서 설정하고 나면 매번 핸들러 메소드의 URL에 중복 내용을 작성하지 않아도 된다. */
@Controller
@RequestMapping("/order/*") // "/order/*"로 시작하는 요청은 전부 이곳에 매핑, 뒤에 다른 문자열 전부
public class ClassMappingTestController {

    @GetMapping("/regist") // "/order/regist"에 매핑된다.
    public String registOrder(Model model) {
        model.addAttribute("message", "GET 방식의 주문 등록용 핸들러 메소드 호출");
        return "mappingResult";
    }

    /* 여러 개의 패턴 매핑 */
    // value 요청 주소에 여러개, 하나의 핸들러 메소드로 가능하다.
    @RequestMapping(value = {"/modify", "/delete"}, method = RequestMethod.POST)
    public String modifyAndDeleteOrder(Model model) {
        model.addAttribute("message", "POST 방식의 주문 수정, 삭제 공통 핸들러 메소드 호출");
        return "mappingResult";
    }

    /* path variable : 요청 주소에 포함 된 변수 */
    // "/detail/{orderNo}" > orderNo 위치의 값 빼내고 싶을때
    // @PathVariable("orderNo") > String orderNo 에 넣겠다.. 경로 안에 있는 변수를 꺼내준다.
    @GetMapping("/detail/{orderNo}")
    public String selectOneOrderDetail(Model model, @PathVariable("orderNo") String orderNo) {
        model.addAttribute("message", orderNo + "번 주문 상세 내용 조회용 핸들러 메소드 호출");
        return "mappingResult";
    }

    /* 아무런 URL을 설정하지 않으면 요청 처리에 대한 핸들러 메소드가 준비 되지 않았을 때 해당 메소드를 매핑한다. */
    // "/order/hello" 로 준비되지 않은 주소 입력시
    @RequestMapping
    public String otherRequest(Model model) {
        model.addAttribute("message", "order 요청이긴 하지만 다른 기능은 아직 준비되지 않음");
        return "mappingResult";
    }


}