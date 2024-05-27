package com.totalcarefix.exception;

public class CanNotCompleteStatus extends  RuntimeException{

    private  String Message;

    public CanNotCompleteStatus(String message) {
        super(message);
        Message = message;
    }
}
