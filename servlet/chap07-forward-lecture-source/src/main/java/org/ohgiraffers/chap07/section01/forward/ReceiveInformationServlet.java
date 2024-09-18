package org.ohgiraffers.chap07.section01.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ReceiveInformationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        /*id와 pwd에 해당하는 user의 정보를 select하고 오는 비즈니스 로직이 수행 되어야 한다.
        * 해당 로직이 정상 수행 되었다는 가정 하에 'XXX님 환영합니다.'와 같은 메세지 출력 화면을 응답한다. */

        req.setAttribute("userName", "홍길동"); // 조회된 이름으로 가정
        // 속성 설정. 이름을 기억할 수 있도록 setAttribute 후 forwarding

        /* 다른 서블릿으로 forward */
        // req가 가지고 있는 getRequestDispatcher 객체를 반환, print> 내가 원하는 서블릿 주소
        RequestDispatcher rd = req.getRequestDispatcher("print");
        rd.forward(req, resp); //사용하고 있던 req,resp 넘겨서 forward
    }

    //405 > 서버 측 제공하는 메소드가 아닌 방식으로 요청한 에러
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }
}
