package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {

    @GetMapping("/string")
    public String stringReturning(Model model) {
        // Model : View에서 표현 되어야 하는 동적인 데이터를 담는 용도로 사용하는 객체
        // 입력데이터 ex) 이름:쿠로미 입력 > 쿠로미 ,,, 이름:마이멜로디 입력 > 마이멜로디... 동적
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함...");
        // ㄴ 응답 뷰에서 꺼내서 출력

        // String 타입으로 리턴할 경우 논리적인 뷰 이름을 리턴한다.
        // ViewResolver가 prefix/suffix를 합쳐서 물리적인 뷰를 선택한다. -> Thymeleaf 이용한 viewResolver 사용
        // prefix: /resources/templates , suffix: .html
        return "result";
        // => /resources/templates/result.html
    }



    @GetMapping("/string-redirect")
    public String stringRedirect() {
        // redirect: 을 붙인 뒤 redirect 할 주소 값을 작성한다.
        // 응답 뷰 선택이 아닌  이 주소로 재요청, http://localhost:8080/ 재요청
        return "redirect:/";
    }



    /* Redirect시 request scope의 데이터는 공유 되지 않는다.
     * session scope에 너무 많은 데이터를 저장하는 것은 서버 성능에 영향을 준다.
     * RedirectAttributes 객체를 통해 잠시 세션에 저장했다가 redirect 후 세션에서 제거 되게 할 수 있다.
     * */
    // 잠깐 사이만 보관하고 지울 데이터
    @GetMapping("/string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {

        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect...");
        return "redirect:/";
    }




    // stringReturning 와 같은 의미, 표현만 다르다.
    @GetMapping("/modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {
        //ModelAndView: model과 view를 합쳐놓은 객체
        // Model 객체에 attribute 저장
        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");
        // View 객체에 논리적 뷰 이름 설정
        mv.setViewName("result");
        return mv;
    }





    /////////////////////////////////////////////////////////// 같은 의미
    @GetMapping("/modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {
        mv.setViewName("redirect:/");
        return mv;
    }


    @GetMapping("/modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectFlashAttribute(ModelAndView mv, RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage2", "리다이렉트 attr 사용하여 redirect...");
        mv.setViewName("redirect:/");
        return mv;
    }











}