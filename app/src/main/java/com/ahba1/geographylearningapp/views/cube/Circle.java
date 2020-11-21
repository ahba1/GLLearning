package com.ahba1.geographylearningapp.views.cube;

import java.util.ArrayList;

public class Circle {
    private float x;
    private float y;
    private float radius;
    private int id;
    private ArrayList<String> nameList;

    public Circle(float x, float y, float radius,int id) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.id=id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isInRegion(float x,float y){
        return radius>(Math.sqrt(Math.pow(x-this.x,2)+Math.pow(y-this.y,2)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getNameList() {
        return nameList;
    }

    public void setNameList(ArrayList<String> nameList) {
        this.nameList = nameList;
    }
}
