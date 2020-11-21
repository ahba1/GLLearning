package com.ahba1.geographylearningapp.module.http.internet.exception;

public class GLException extends RuntimeException {
    public GLException() {
    }

    public GLException(String detailMessage) {
        super(detailMessage);
    }

    public GLException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public GLException(Throwable throwable) {
        super(throwable);
    }
}
