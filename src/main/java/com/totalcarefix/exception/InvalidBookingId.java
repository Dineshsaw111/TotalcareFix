package com.totalcarefix.exception;

public class InvalidBookingId  extends  RuntimeException{
    private  String Message;

    public InvalidBookingId(String message) {
        super(message);
        Message = message;
    }
}
