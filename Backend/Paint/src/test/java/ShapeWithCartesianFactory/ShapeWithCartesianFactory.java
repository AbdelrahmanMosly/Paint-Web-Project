package ShapeWithCartesianFactory;

import java.awt.*;

public class ShapeWithCartesianFactory {
    public static ShapeWithCartesian
            createShape(String shapeType, Point[] cartesianCoordiante, String color, boolean fill,int edgeWidth){
        if(shapeType==null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("LINE"))
            return new Line(cartesianCoordiante,color,fill,edgeWidth);
        if(shapeType.equalsIgnoreCase("RECTANGLE"))
            return new Rectangle(cartesianCoordiante,color,fill,edgeWidth);
        if(shapeType.equalsIgnoreCase("SQUARE"))
            return new Square(cartesianCoordiante,color,fill,edgeWidth);
        if(shapeType.equalsIgnoreCase("TRIANGLE"))
            return new Triangle(cartesianCoordiante,color,fill,edgeWidth);


        return null;
    }
}
