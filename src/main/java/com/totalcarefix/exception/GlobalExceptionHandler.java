package com.totalcarefix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GlobalExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(ex.getMessage()+"(bad request global)", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @ExceptionHandler(InvalidEmailException.class)
        public ResponseEntity<GlobalExceptionResponse> handleInvalidEmailException(InvalidEmailException ex) {
            GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
              return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserCancelException.class)
    public ResponseEntity<GlobalExceptionResponse> handleUserCancelException(UserCancelException ex) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( InvalidBookingId.class)
    public ResponseEntity<GlobalExceptionResponse> handleUserCancelException( InvalidBookingId ex) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler( NotEditableException.class)
    public ResponseEntity<GlobalExceptionResponse> handleUserCancelException( NotEditableException ex) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler( CanNotCompleteStatus.class)
    public ResponseEntity<GlobalExceptionResponse> handleUserCancelException( CanNotCompleteStatus ex) {
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }
}
