package org.ohgiraffers.chap01.section01.xml;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

public class LifeCycleTest extends HttpServlet {

    /* 기본 생성자 */
    public LifeCycleTest() {
        System.out.println("xml 생성자 호출");
    }

    /* init : 최초 서블릿 요청 시 객체를 생성하며 초기화 작업을 할 수 있는 메소드 */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("xml 매핑 init 호출");
    }

    /* service: 서블릿 컨테이너에 의해 호출 되며 최초 요청 시에는 init 이후 동작, 두 번째 요청부터는 init 없이 바로 동작하는 메소드*/
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("xml 매핑 service 호출");
    }

    /* destory: 컨터이너가 종료될 때 호출 되는 메소드로 주로 자원을 반납하는 용도로 사용 되는 메소드*/
    @Override
    public void destroy() {
        System.out.println("xml 매핑 destroy 호출");
    }
}
