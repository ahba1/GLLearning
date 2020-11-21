package com.ahba1.geographylearningapp.module.http.model;

import java.io.Serializable;

public class FileInfo extends BaseModel implements Serializable {

    /**
     * {
     *      "id": 4,
     *      "name": "环境问题-Y",
     *      "path": "/file/成稿文件-人/环境—Y/环境问题-Y.ppt",
     *      "type": "3"
     * }
     */

    private int id;
    private String name;
    private String path;
    private int type;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
