package org.ohgiraffers.chap09.section01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* redirect 처리 시 이전 req 정보는 존재하지 않는다. 새로운 req 객체이므로 값이 없어 null이 반환 된다. */
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName: " + firstName + ", lastName: " + lastName);

        // 저장된 쿠키 값을 꺼내서 확인
        Cookie[] cookies = req.getCookies();

        for(Cookie cookieee : cookies){
            if(cookieee.getName().equals("lastName")){
                firstName = cookieee.getValue();
            }else if(cookieee.getName().equals("firstName")){
                lastName = cookieee.getValue();
            }
        }

        resp.setContentType("text/html");
        // 응답화면 만들고 확인
        PrintWriter out = resp.getWriter();
        out.println("<h1>your firstName is " + firstName + "and lastName is" + lastName + "</h1>");
        out.flush();
        out.close();
    }
}
