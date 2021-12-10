package com.example.Paint.Models;

import java.awt.*;

public class Ellipse extends Shape{
    private Point p1;
    private double r1;
    private double r2;

    public Ellipse(int strokeSize, String color, boolean filled, Point p1, double r1, double r2) {
        super("Ellipse", strokeSize, color, filled);
        this.p1 = p1;
        this.r1 = r1;
        this.r2 = r2;
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

    public double getR2() {
        return r2;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }

    @Override
    public boolean cursorOnShape(Point point) {
        return (Math.pow(point.x-p1.x,2)/Math.pow(r1,2))
                +(Math.pow(point.y-p1.y,2)/Math.pow(r2,2))<=1;
    }

    @Override
    protected Ellipse clone() throws CloneNotSupportedException {
        Ellipse clone;
        try{
            clone = (Ellipse) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.p1 = (Point) this.p1.clone();
        clone.r1 = this.r1;
        clone.r2 = this.r2;

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Ellipse ret = null;
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
        Ellipse ret = null;
        try {
            ret = this.clone();
            ret.r1 *= scale;
            ret.r2 *= scale;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
