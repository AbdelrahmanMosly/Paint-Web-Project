package com.example.Paint.Models;

import java.awt.*;
import java.util.ArrayList;

public class Freehand extends Shape{

    private ArrayList<Point> path;

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }

    public Freehand(int strokeSize, String color, boolean filled) {
        super("Freehand", strokeSize, color, filled);
    }

    @Override
    public boolean cursorOnShape(Point point) {
        for(Point p: path){
            if(point == p)
                return true;
        }
        return false;
    }

    @Override
    public Freehand clone(){
        Freehand clone = null;
        try{
            clone = (Freehand) super.clone();
            ArrayList<Point> clonedPath = new ArrayList<>();
            for(Point p: path)
                clonedPath.add(p);
            clone.setPath(clonedPath);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
    @Override
    public IShape move(int x, int y) {
        Freehand ret = this.clone();
        ArrayList<Point> previousPath = ret.getPath();
        ArrayList<Point> newPath = new ArrayList<>();
        for(Point p: previousPath){
            p.x += x;
            p.y += y;
            newPath.add(p);
        }
        ret.setPath(newPath);
        return ret;
    }

    @Override
    public IShape resize(double scale) {
        return this;
    }
}
