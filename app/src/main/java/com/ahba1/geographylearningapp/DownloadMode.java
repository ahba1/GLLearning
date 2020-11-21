package com.ahba1.geographylearningapp;

import java.io.Serializable;

public enum DownloadMode implements Serializable {
    ALL(-1), ALONE(0);
    private int id;

    private DownloadMode(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
