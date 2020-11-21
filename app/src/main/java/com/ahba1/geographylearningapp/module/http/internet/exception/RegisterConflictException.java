package com.ahba1.geographylearningapp.module.http.internet.exception;

public class RegisterConflictException extends GLException {
    public RegisterConflictException(){}

    public RegisterConflictException(String detailMessage){
        super(detailMessage);
    }
}
