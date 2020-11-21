package com.ahba1.geographylearningapp.module.http.model;

import java.io.Serializable;

public class RegisterInfo extends BaseModel implements Serializable {

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
