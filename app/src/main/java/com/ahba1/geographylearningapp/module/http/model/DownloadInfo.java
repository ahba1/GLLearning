package com.ahba1.geographylearningapp.module.http.model;

import com.ahba1.geographylearningapp.module.http.internet.utils.GsonUtil;

import java.util.List;

public class DownloadInfo {
    private List<DictionaryBean> dictionary;
    private List<FileBean> file;

    public List<DictionaryBean> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<DictionaryBean> dictionary) {
        this.dictionary = dictionary;
    }

    public List<FileBean> getFile() {
        return file;
    }

    public void setFile(List<FileBean> file) {
        this.file = file;
    }

    @Override
    public String toString(){
        return GsonUtil.bean2Json(this);
    }

    public static class DictionaryBean{
        /**
         * id : 1
         * name : 天
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

        @Override
        public String toString(){
            return GsonUtil.bean2Json(this);
        }
    }

    public static class FileBean {
        /**
         * id : 1
         * name : 热-大气温度
         * path : /天/热/大气温度/热—大气温度.ppt
         * type : 4
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

        @Override
        public String toString(){
            return GsonUtil.bean2Json(this);
        }
    }
}




