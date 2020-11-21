package com.ahba1.geographylearningapp.module.http.internet.exception;

/**
 * Gson无法转换
 */

public class TranslateObjectException extends GLException {

    public TranslateObjectException(){}

    public TranslateObjectException(String detailMessage){
        super(detailMessage);
    }
}
