package com.totalcarefix.exception;

public class UserCancelException extends RuntimeException{
    private  String message;

    public UserCancelException(String message) {
        super(message);
        this.message = message;
    }
}
