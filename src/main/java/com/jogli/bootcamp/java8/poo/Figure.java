package com.jogli.bootcamp.java8.poo;

public abstract class Figure {
    private String name;
    protected float h;
    protected float l;

    public abstract float calculateArea();

    public void setH(float h) {
        this.h = h;
    }

    public void setL(float l) {
        this.l = l;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
