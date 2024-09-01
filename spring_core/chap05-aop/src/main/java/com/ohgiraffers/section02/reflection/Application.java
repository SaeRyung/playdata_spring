package com.ohgiraffers.section02.reflection;

import java.lang.reflect.*;
// reflect : 클래스의 메타 정보 보는 기술

public class Application {
    public static void main(String[] args) {
        // .class 문법을 이용하여 Class 타입의 인스턴스를 생성
        Class class1 = Account.class;
        System.out.println("class1: " + class1); //Class 타입의 인스턴스는 해당 클래스의 메타 정보를 가짐


        // Object 클래스의 getClass() 메소드를 이용해서도 Class 타입의 인스턴스 생성 가능
        Class class2 = new Account().getClass();
        System.out.println("class2 : " + class2);

        try {
            Class class3 = Class.forName("com.ohgiraffers.section02.reflection.Account");
            Class class4 = Class.forName("[D"); //double 타입 배열
            Class class5 = double[].class;

            System.out.println("class3 : " + class3);
            System.out.println("class4 : " + class4);
            System.out.println("class5 : " + class5);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        // 원시 자료형의 경우, class가 아니므로 불가능
//        double d = 1.0;
//        Class class6 = d.getClass();

        // 원시형 클래스는 TYPE 필드로 반환하여 메타정보 접근 가능하도록
        Class class6 = Double.TYPE;
        System.out.println("class6 : " + class6);

        System.out.println();

        /* 클래스 메타 정보를 이용하여 여러 정보를 반환 받는 메소드가 제공 된다. */
        Class superClass = class1.getSuperclass();
        System.out.println("superClass : " + superClass);

        System.out.println();

        // 필드정보 접근
        Field[] fields = class1.getDeclaredFields();
        for(Field field : fields) {
            System.out.println("modifiers : " + Modifier.toString(field.getModifiers()));
            System.out.println("type : " + field.getType());
            System.out.println("name : " + field.getName());
        }

        System.out.println();

        // 생성자 접근
        Constructor[] constructors = class1.getConstructors();
        for(Constructor constructor : constructors) {
            System.out.println("name: " + constructor.getName());

            Class[] params = constructor.getParameterTypes();
            for(Class param : params){
                System.out.println("paramType : " + param.getTypeName());
            }
        }
        System.out.println();



        // 실제 인스턴스 생성
        try {
            Account acc = (Account) constructors[0].newInstance("20", "110-234-567890", "1234", 10000);
            System.out.println("acc.getBalence : " + acc.getBalance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        System.out.println();


        // 메소드 정보 접근
        Method[] methods = Account.class.getMethods();
        Method getBalenceMethod = null;
        for(Method method : methods) {
            System.out.print(Modifier.toString(method.getModifiers()) + " ");
            System.out.print(method.getReturnType().getSimpleName() + " ");
            System.out.println(method.getName());

            if("getBalence".equals(method.getName())) getBalenceMethod = method;
        }
        System.out.println();

        try {
            System.out.println(getBalenceMethod.invoke(constructors[2].newInstance()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }


    }
}
