package ShapeWithDimensionsFactory;

public class ShapeWithDimensionsFactory {
    public static ShapeWithDimensions 
                    createShape(String shapeType,int x,int y,int[] dimensions,String color,boolean fill){
        if(shapeType==null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE"))
            return new Circle(x,y,dimensions,color,fill);
        if(shapeType.equalsIgnoreCase("SQUARE"))
            return new Square(x,y,dimensions,color,fill);
        if(shapeType.equalsIgnoreCase("Ellipse"))
            return new Ellipse(x,y,dimensions,color,fill);

        return null;
    }
}
