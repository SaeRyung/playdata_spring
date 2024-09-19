package com.ohgiraffers.restapi.section03.valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 에러 확인 핸들링 클래스

// @RestControllerAdvice : 전역 Exception handler
@RestControllerAdvice
public class ExceptionController {

    // UserNotFoundException.class 에서 에러 처리 시 발생하는 메소드
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserNotFoundException e) {
        // <ErrorResponse> : 에러 발생 시  직접 만든 타입으로 반환하도록
        // ResponseEntity 쓸 때 상태코드 지정 가능하다. -> return HttpStatus.NOT_FOUND
        String code = "ERROR_CODE_00000";
        String description = "회원 정보 조회 실패";
        String detail = e.getMessage();

        return new ResponseEntity<>(new ErrorResponse(code, description, detail), HttpStatus.NOT_FOUND);
        // new ErrorTesponse : Body -> 넘겨줄 에러 메세지
        // HttpStatus : 404 -> 오류에 대한 적절한 상태 코드 설정 .. 에러 정의된 방식으로 출력된다.
    }



    // 에러 핸들링
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e) {
        String code = "";
        String description = "";
        String detail = "";

        /* 에러가 있다면 */
        if(e.getBindingResult().hasErrors()) {
            // 가지고 있는 메세지로 에러 채우기
            detail = e.getBindingResult().getFieldError().getDefaultMessage();
            // 코드는 케이스에 따라 분류
            String bindingResultCode = e.getBindingResult().getFieldError().getCode();
            switch (bindingResultCode) {
                case "NotNull" :
                    code = "ERROR_CODE_00001";
                    description = "필수 값이 누락 되었습니다.";
                    break;
                case "NotBlank" :
                    code = "ERROR_CODE_00002";
                    description = "필수 값이 공백으로 처리 되었습니다.";
                    break;
                case "Size" :
                    code = "ERROR_CODE_00003";
                    description = "알맞은 크기의 값이 입력 되지 않았습니다.";
                    break;
            }
        }

        return new ResponseEntity<>(new ErrorResponse(code, description, detail), HttpStatus.BAD_REQUEST);
    }






}