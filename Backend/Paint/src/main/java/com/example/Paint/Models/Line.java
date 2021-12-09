package com.example.Paint.Models;

import java.awt.*;

public class Line extends Shape{
    private Point start;
    private Point end;
    private final double eps = 1e-6;

    public Line(int strokeSize, String color, boolean filled, Point start, Point end) {
        super(strokeSize, color, filled);
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    private boolean betweenEndpoints(Point point){
        return point.x>=Math.min(start.x,end.x) && point.x<=Math.max(start.x,end.x)
               && point.y>=Math.min(start.y,end.y) && point.y<=Math.max(start.y,end.y);
    }
    private boolean onSameSlope(Point point){
        if(point == start || point == end)
            return true;
        if(point.x == start.x || point.x == end.x)
            return start.x == end.x;
        return ( ((double)(end.y-point.y)/(end.x-point.x)) - ((double)(point.y-start.y)/(point.x-start.x)) ) <= eps;
    }

    @Override
    public boolean cursorOnShape(Point point) {
        return betweenEndpoints(point) && onSameSlope(point);
    }

    @Override
    protected Line clone() throws CloneNotSupportedException {
        Line clone;
        try{
            clone = (Line) super.clone();
        }catch (CloneNotSupportedException e){
            throw e;
        }
        clone.start = (Point) this.start.clone();
        clone.end = (Point) this.end.clone();

        return clone;
    }

    @Override
    public IShape move(int x, int y) {
        Line ret = null;
        try {
            ret = this.clone();
            ret.start.x += x;
            ret.start.y += y;
            ret.end.x += x;
            ret.end.y += y;
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
