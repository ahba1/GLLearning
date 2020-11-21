package com.ahba1.geographylearningapp.node;

public class ProgressNode {

    private int id;

    private String name;

    private int percent;

    private boolean downing;

    public ProgressNode(int id, String name, int percent, boolean downing) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.downing = downing;
    }

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

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isDowning() {
        return downing;
    }

    public void setDowning(boolean downing) {
        this.downing = downing;
    }
}
