package com.example.Paint.Models;

import java.awt.*;

public class Circle extends Shape{

    private Point p1;
    private double r1;

    public Circle(int strokeSize, String color, boolean filled, Point p1, double r1) {
        super("Circle", strokeSize, color, filled);
        this.p1 = p1;
        this.r1 = r1;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public double getR1() {
        return r1;
    }

    public void setR1(double r1) {
        this.r1 = r1;
    }

    public double distance(Point point){
        return Math.sqrt(Math.pow(p1.x-point.x,2)+Math.pow(p1.y-point.y,2));
    }

    @Override
    public boolean cursorOnShape(Point point) {
        return distance(point) <= r1;
    }

    @Override
    protected Circle clone() throws CloneNotSupportedException {
        Circle clone;
        try{
            clone = (Circle) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.p1 = (Point) this.p1.clone();
        clone.r1 = this.r1;

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Circle ret = null;
        try {
            ret = this.clone();
            ret.p1.x += x;
            ret.p1.y += y;
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
            ret.r1 *= scale;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
