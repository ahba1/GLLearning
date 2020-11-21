package com.ahba1.geographylearningapp.module.http.model;

import java.io.Serializable;

public class CatalogItem extends BaseModel implements Serializable {

    /**
     * {
     *      "id": 2,
     *      "name": "产业-Y"
     * }
     */

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
