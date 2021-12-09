package com.example.Paint.Models;

import java.awt.*;

public class Ellipse extends Shape{
    private Point center;
    private double radius1;
    private double radius2;

    public Ellipse(int strokeSize, String color, boolean filled, Point center, double radius1, double radius2) {
        super(strokeSize, color, filled);
        this.center = center;
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius1() {
        return radius1;
    }

    public void setRadius1(double radius1) {
        this.radius1 = radius1;
    }

    public double getRadius2() {
        return radius2;
    }

    public void setRadius2(double radius2) {
        this.radius2 = radius2;
    }

    @Override
    public boolean cursorOnShape(Point point) {
        return (Math.pow(point.x-center.x,2)/Math.pow(radius1,2))
                +(Math.pow(point.y-center.y,2)/Math.pow(radius2,2))<=1;
    }

    @Override
    protected Ellipse clone() throws CloneNotSupportedException {
        Ellipse clone;
        try{
            clone = (Ellipse) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.center = (Point) this.center.clone();
        clone.radius1 = this.radius1;
        clone.radius2 = this.radius2;

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Ellipse ret = null;
        try {
            ret = this.clone();
            ret.center.x += x;
            ret.center.y += y;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public IShape resize(double scale) {
        Ellipse ret = null;
        try {
            ret = this.clone();
            ret.radius1 *= scale;
            ret.radius2 *= scale;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
