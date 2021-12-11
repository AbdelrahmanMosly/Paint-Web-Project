package com.example.Paint.Models;

import java.awt.*;

public abstract class Shape implements IShape, Cloneable{
    private String shapeType;
    private int strokeSize;
    private String color;
    private boolean filled;

    public Shape(String shapeType, int strokeSize, String color, boolean filled) {
        this.shapeType = shapeType;
        this.strokeSize = strokeSize;
        this.color = color;
        this.filled = filled;
    }

    @Override
    public String getShapeType() {
        return shapeType;
    }

    @Override
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
    @Override
    public void maintainResizeSelection(){return;}
    @Override
    public int getStrokeSize() {
        return strokeSize;
    }

    @Override
    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public boolean getFilled() {
        return false;
    }

    @Override
    protected Shape clone() throws CloneNotSupportedException {
        Shape clone;
        try{
            clone = (Shape) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.strokeSize = this.strokeSize;
        clone.color = this.color;
        clone.filled = this.filled;

        return clone;
    }
}
