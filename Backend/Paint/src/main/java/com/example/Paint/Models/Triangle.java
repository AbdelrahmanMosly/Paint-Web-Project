package com.example.Paint.Models;

import java.awt.*;

public class Triangle extends Shape{
    private Point p1;
    private Point p2;
    private Point p3;

    public Triangle(int strokeSize, String color, boolean filled, Point p1, Point p2, Point p3) {
        super("Triangle", strokeSize, color, filled);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
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

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    int orientation(Point a, Point b, Point c){
        int val = (b.y - a.y)*(c.x - b.x) - (b.x - a.x)*(c.y - b.y);
        if(val == 0)
            return 0;
        return val>0?1:-1;
    }

    @Override
    public boolean cursorOnShape(Point point) {
        int orientation1 = orientation(point, p1, p2);
        int orientation2 = orientation(point, p2, p3);
        int orientation3 = orientation(point, p3, p1);

        if(orientation1 == 0 || orientation2 == 0 || orientation3 == 0)
            return true;
        return orientation1 == orientation2 && orientation2 == orientation3;
    }

    @Override
    protected Triangle clone() throws CloneNotSupportedException {
        Triangle clone;
        try{
            clone = (Triangle) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.p1 = (Point) this.p1.clone();
        clone.p2 = (Point) this.p2.clone();
        clone.p3 = (Point) this.p3.clone();

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Triangle ret = null;
        try {
            ret = this.clone();
            ret.p1.x += x;
            ret.p1.y += y;
            ret.p2.x += x;
            ret.p2.y += y;
            ret.p3.x += x;
            ret.p3.y += y;
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
