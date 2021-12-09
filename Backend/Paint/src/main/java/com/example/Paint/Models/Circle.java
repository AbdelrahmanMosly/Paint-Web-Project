package com.example.Paint.Models;

import java.awt.*;

public class Circle extends Shape{

    private Point center;
    private double radius;

    public Circle(int strokeSize, String color, boolean filled, Point center, double radius) {
        super(strokeSize, color, filled);
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double distance(Point point){
        return Math.sqrt(Math.pow(center.x-point.x,2)+Math.pow(center.y-point.y,2));
    }

    @Override
    public boolean cursorOnShape(Point point) {
        return distance(point) <= radius;
    }

    @Override
    protected Circle clone() throws CloneNotSupportedException {
        Circle clone;
        try{
            clone = (Circle) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.center = (Point) this.center.clone();
        clone.radius = this.radius;

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Circle ret = null;
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
        Circle ret = null;
        try {
            ret = this.clone();
            ret.radius *= scale;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
