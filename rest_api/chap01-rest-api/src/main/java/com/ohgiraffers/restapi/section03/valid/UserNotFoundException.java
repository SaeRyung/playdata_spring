package com.ohgiraffers.restapi.section03.valid;

// Exception class

// Exception 상속하면 된다.
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
        // exception 원할 경우 상위타입으로 message 전달하면 된다.
    }
}