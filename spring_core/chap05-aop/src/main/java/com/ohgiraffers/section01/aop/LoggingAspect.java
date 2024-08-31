package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

// 메인기능 MemberService 에 Logging 기능 추가

@Component
@Aspect //pointcut(부가기능 넣을곳 선별)과 advice(부가기능)를 하나의 클래스 단위로 정의하기 위한 어노테이션
public class LoggingAspect {
    // Join Point: 하나의 시점

    // Pointcut : 여러 Join Point(부가기능 넣으려는 하나의 시점)를 매치하기 위해 지정한 표현식,  Join Point 집합
    // MemberService selectMembers를 JoinPoint 하기 위해

    // 이런상황에 대해 부가기능을 잡고 싶다.
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    // .*Service : Service로 끝나는 클래스명 / .* : 메소드명 / (..): 매개변수 )
    // @Pointcut: MemberService 에 있는 메소드를 join point 로 지정하기 위해 작성함
    public void logPointcut(){}

    //Advice : 부가 코드

    // Before : 핵심 기능 수행 전 동작
    // 위 메소드 logPointcut 하기 전 기능 적용
    @Before("LoggingAspect.logPointcut()") //LoggingAspect 클래스의 logPointcut() 메소드 불러옴
    // @Before: 이 시점에 로그를 남기겠다.
    public void logBefore(JoinPoint joinPoint){  // 각각의 JoinPoint가 넘어옴. JoinPoint: 포인트 컷으로 패치한 실행 지점
        // JoinPoint 객체를 통해 현재 조인포인트의 메소드명, 인수 값 등 자세한 정보를 엑세스 할 수 있다.
        System.out.println("before joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("before joinPoint.getSignature() : " + joinPoint.getSignature());
        if(joinPoint.getArgs().length > 0){
            System.out.println("before joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }

    // After : 핵심 기능 수행 후 동작(정상 수헹 또는 오류 발생 무관)
    // 어떤 포인트컷에 어드바이스 적용할것인지
    @After("logPointcut()") //포인트 컷을 클래스 내에서 사용할 경우 클래스명 생략 가능,
    // 패키지가 다르면 패키지 명까지 기술.
    public void logAfter(JoinPoint joinPoint){
        System.out.println("after joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("after joinPoint.getSignature() : " + joinPoint.getSignature());
        if(joinPoint.getArgs().length > 0){
            System.out.println("after joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////

    // AfterReturning -> 정상 수행 시
    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturn(JoinPoint joinPoint, Object result){
        System.out.println("** 정상 수행 시 after returning result : " + result + " **");
        if(result != null && result instanceof Map){
            ((Map<Long, MemberDTO>)result).put(100L, new MemberDTO(100L, "반환 값 가공"));
        }
    }

    // AfterThrowing -> exception 발생 시
    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception){

        System.out.println("** 예외 발생 after throwing exception: " + exception + " **");
    }


    // Arround: 핵심 기능 시작과 끝을 감싸고 동작
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint)throws Throwable{

        System.out.println("around before : " + joinPoint.getSignature());

        Object result = joinPoint.proceed(); //원본 조인 포인트를 실행

        System.out.println("around after : " + joinPoint.getSignature());

        return result;
        }
    }
