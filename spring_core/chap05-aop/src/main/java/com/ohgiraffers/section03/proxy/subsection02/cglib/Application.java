package com.ohgiraffers.section03.proxy.subsection02.cglib;

import com.ohgiraffers.section03.proxy.common.OhgiraffersStudent;
import org.springframework.cglib.proxy.Enhancer;

import java.util.Enumeration;

public class Application {
    public static void main(String[] args) {

        OhgiraffersStudent ohgiraffersStudent = new OhgiraffersStudent();
        Handler handler = new Handler(ohgiraffersStudent);

        OhgiraffersStudent proxy
                = (OhgiraffersStudent) Enhancer.create(OhgiraffersStudent.class, handler);

        proxy.study(20);
    }

}
