package org.ohgiraffers.chap10.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 서버 쪽에서 안전하게 관리할 수 있는 세션이라는 인스턴스를 통한 상태 유지 메커니즘을 제공한다.*/
        HttpSession session = req.getSession();

        /* 세션은 브라우저 당 한 개의 고유 아이디를 가지고 하나의 인스턴스를 이용한다.
        * 반복 요청 시 동일 session id를 반환한다.
        * 단, 브라우저가 종료 되면 해당 세션이 종료되므로 그 이후 요청은 다른 session id를 반환한다.
        * session id는 cookie로 보관되고 있다. */
        System.out.println(session.getId());

        System.out.println(session.getMaxInactiveInterval()); // 세션의 기본 유지시간 30 분
        session.setMaxInactiveInterval(60 * 20); // 필요에 따라 늘이거나 줄일 수 있다.(10분)
        System.out.println(session.getMaxInactiveInterval());

        /* 세션은 redirect 해도 값을 유지할 수 있으므로 request 보다 더 넓은 범위의 공유 영역이라고 할 수 있다. */
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        // 유지하고 싶을 때 세션 사용
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);


        resp.sendRedirect("redirect");
    }
}
