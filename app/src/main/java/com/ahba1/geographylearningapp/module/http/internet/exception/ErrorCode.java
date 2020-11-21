package com.ahba1.geographylearningapp.module.http.internet.exception;

public class ErrorCode {
    /**
     * 200: 请求成功
     * 500：网络连接错误
     * 422：输入不合法
     * 401：用户不存在或者密码错误
     */
    public final static int SUCCESS=200;

    public final static int NET_ERROR=500;

    public final static int INPUT_ILLEGAL=422;

    public final static int USER_NOT_EXIST=401;
}
