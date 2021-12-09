package com.example.Paint.Models;

import java.awt.*;

public class Rectangle extends Shape {
    private Point p1;
    private Point p2;

    public Rectangle(int strokeSize, String color, boolean filled, Point p1, Point p2) {
        super(strokeSize, color, filled);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public boolean cursorOnShape(Point point) {
        return point.x >= p1.x && point.y >=p1.y && point.x <= p2.x && point.y <= p2.y;
    }

    @Override
    protected Rectangle clone() throws CloneNotSupportedException {
        Rectangle clone;
        try{
            clone = (Rectangle) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.p1 = (Point) this.p1.clone();
        clone.p2 = (Point) this.p2.clone();

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Rectangle ret = null;
        try {
            ret = this.clone();
            ret.p1.x += x;
            ret.p1.y += y;
            ret.p2.x += x;
            ret.p2.y += y;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public IShape resize(double scale) {
        return null;
    }
}
