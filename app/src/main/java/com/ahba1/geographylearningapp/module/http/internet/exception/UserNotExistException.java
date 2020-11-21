package com.ahba1.geographylearningapp.module.http.internet.exception;

public class UserNotExistException extends GLException {
    public UserNotExistException(){}

    public UserNotExistException(String detailMessage){
        super(detailMessage);
    }
}
