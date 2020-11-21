package com.ahba1.geographylearningapp.module.http.model;

import java.io.Serializable;
import java.util.List;

public class CatalogInfo extends BaseModel implements Serializable{

    private List<CatalogItem> dictionary;

    private List<FileInfo> file;

    public List<CatalogItem> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<CatalogItem> dictionary) {
        this.dictionary = dictionary;
    }

    public List<FileInfo> getFile() {
        return file;
    }

    public void setFile(List<FileInfo> file) {
        this.file = file;
    }

}
