package org.ohgiraffers.chap09.section01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
// 꺼내고 기억하기 테스트
@WebServlet("/cookie")
public class CookieHandlerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 데이터 꺼내기, 파라미터에서 잘 나오는지 확인
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName: " + firstName + ", lastName: " + lastName);

        /* 1. 쿠키 생성 */
        Cookie firstNameCookie = new Cookie("firstName", firstName);
        Cookie lastNameCookie = new Cookie("lastName", lastName);
        // 쿠키는 클라이언트에 저장된다.
        /* 2. 쿠키 만료 시간 설정 */
        firstNameCookie.setMaxAge(60 * 60 * 24); // 초 단위이며 하루를 만료시간으로 하는 예시
        lastNameCookie.setMaxAge(60 * 60 * 24);
        // 브라우저에 저장하게끔 만들기
        /* 3. 응답 헤더에 쿠키를 담는다.*/
        resp.addCookie(firstNameCookie);
        resp.addCookie(lastNameCookie);
        // 담겨긴 쿠키는 응답과 함께 넘어간다.
        /* 4. 응답 처리 */
        // 재요청
        resp.sendRedirect("redirect");
    }
}
