package com.totalcarefix.exception;

public class NotEditableException extends  RuntimeException{
    private  String Message;

    public NotEditableException(String message) {
        super(message);
        Message = message;
    }
}
