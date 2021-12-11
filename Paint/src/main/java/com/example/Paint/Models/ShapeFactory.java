package com.example.Paint.Models;

import java.awt.*;

public class ShapeFactory {
    public Shape createShape(String shapeType, int strokeSize, String color, boolean fill,
                             Point p1, Point p2, Point p3, double r1, double r2){
        if(shapeType==null)
            return null;
        if(shapeType.equalsIgnoreCase("LINE"))
            return new Line(strokeSize ,color, fill, p1, p2);
        if(shapeType.equalsIgnoreCase("RECTANGLE"))
            return new Rectangle(strokeSize ,color, fill, p1, p2);
        if(shapeType.equalsIgnoreCase("SQUARE"))
            return new Square(strokeSize ,color, fill, p1, p2);
        if(shapeType.equalsIgnoreCase("TRIANGLE"))
            return new Triangle(strokeSize ,color, fill, p1, p2, p3);
        if(shapeType.equalsIgnoreCase("ELLIPSE"))
            return new Ellipse(strokeSize ,color, fill, p1, r1, r2);
        if(shapeType.equalsIgnoreCase("CIRCLE"))
            return new Circle(strokeSize ,color, fill, p1, r1);



        return null;
    }
}
