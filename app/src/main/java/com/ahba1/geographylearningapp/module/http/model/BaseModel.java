package com.ahba1.geographylearningapp.module.http.model;

import com.alibaba.fastjson.JSON;

public class BaseModel {

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
