package com.example.Paint;

public class ShapeFactory {
    public static Shape createShape(String shapeType,float x,float y,double[] dimensions){
        if(shapeType==null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE"))
            return new Circle(x,y,dimensions);
        if(shapeType.equalsIgnoreCase("RECTANGLE"))
            return new Rectangle(x,y,dimensions);
        if(shapeType.equalsIgnoreCase("SQUARE"))
            return new Square(x,y,dimensions);

        return null;
    }
}
