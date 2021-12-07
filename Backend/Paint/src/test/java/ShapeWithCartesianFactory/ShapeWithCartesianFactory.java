package ShapeWithCartesianFactory;

public class ShapeWithCartesianFactory {
    public static ShapeWithCartesian
            createShape(String shapeType,int[][] cartesianCoordiante,String color,boolean fill){
        if(shapeType==null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("LINE"))
            return new Line(cartesianCoordiante,color,fill);
        if(shapeType.equalsIgnoreCase("RECTANGLE"))
            return new Rectangle(cartesianCoordiante,color,fill);
        if(shapeType.equalsIgnoreCase("SQUARE"))
            return new Square(cartesianCoordiante,color,fill);
        if(shapeType.equalsIgnoreCase("TRIANGLE"))
            return new Triangle(cartesianCoordiante,color,fill);


        return null;
    }
}
