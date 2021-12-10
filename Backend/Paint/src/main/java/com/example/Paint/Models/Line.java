package com.example.Paint.Models;

import java.awt.*;

public class Line extends Shape{
    private Point p1;
    private Point p2;
    private final double eps = 1e-6;

    public Line(int strokeSize, String color, boolean filled, Point p1, Point p2) {
        super("Line", strokeSize, color, filled);
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

    private boolean betweenp2points(Point point){
        return point.x>=Math.min(p1.x,p2.x) && point.x<=Math.max(p1.x,p2.x)
               && point.y>=Math.min(p1.y,p2.y) && point.y<=Math.max(p1.y,p2.y);
    }
    private boolean onSameSlope(Point point){
        if(point == p1 || point == p2)
            return true;
        if(point.x == p1.x || point.x == p2.x)
            return p1.x == p2.x;
        return ( ((double)(p2.y-point.y)/(p2.x-point.x)) - ((double)(point.y-p1.y)/(point.x-p1.x)) ) <= eps;
    }

    @Override
    public boolean cursorOnShape(Point point) {
        return betweenp2points(point) && onSameSlope(point);
    }

    @Override
    protected Line clone() throws CloneNotSupportedException {
        Line clone;
        try{
            clone = (Line) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.p1 = (Point) this.p1.clone();
        clone.p2 = (Point) this.p2.clone();

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Line ret = null;
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
