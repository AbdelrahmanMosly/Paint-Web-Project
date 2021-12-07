package ShapeWithCartesianFactory;

import ShapeWithDimensionsFactory.Circle;
import ShapeWithDimensionsFactory.Square;

public class ShapeWithCartesianFactory {
    public static ShapeWithCartesian
            createShape(String shapeType,int[][] cartesianCoordiante,String color,boolean fill){
        if(shapeType==null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("Line"))
            return new Line(cartesianCoordiante,color,fill);
        if(shapeType.equalsIgnoreCase("Rectangle"))
            return new Rectangle(cartesianCoordiante,color,fill);
        if(shapeType.equalsIgnoreCase("Triangle"))
            return new Triangle(cartesianCoordiante,color,fill);


        return null;
    }
}
