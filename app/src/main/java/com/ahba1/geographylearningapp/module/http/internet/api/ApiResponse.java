package com.ahba1.geographylearningapp.module.http.internet.api;

import com.ahba1.geographylearningapp.module.http.internet.utils.GsonUtil;

public class ApiResponse<T> {

    public int code;

    public String msg;

    public T data;

    @Override
    public String toString() {
        return GsonUtil.bean2Json(this);
    }
}
