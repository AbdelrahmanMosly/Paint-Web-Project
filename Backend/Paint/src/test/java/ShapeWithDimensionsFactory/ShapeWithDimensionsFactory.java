package ShapeWithDimensionsFactory;

import java.awt.*;

public class ShapeWithDimensionsFactory {
    public static ShapeWithDimensions 
                    createShape(String shapeType, Point position, int[] dimensions, String color, boolean fill,int edgeWidth){
        if(shapeType==null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE"))
            return new Circle(position,dimensions,color,fill,edgeWidth);
        if(shapeType.equalsIgnoreCase("Ellipse"))
            return new Ellipse(position,dimensions,color,fill,edgeWidth);

        return null;
    }
}
