package com.ahba1.geographylearningapp.module.http.model;

import java.io.Serializable;

public class LoginInfo extends BaseModel implements Serializable {

    private String token;

    public String getInfo() {
        return token;
    }

    public void setInfo(String info) {
        this.token = info;
    }

}
