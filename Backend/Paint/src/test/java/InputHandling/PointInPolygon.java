package InputHandling;

import ShapeWithCartesianFactory.ShapeWithCartesian;
import ShapeWithDimensionsFactory.ShapeWithDimensions;

import java.awt.*;

public class PointInPolygon {


    public boolean pointInTriangle(Point point, ShapeWithCartesian triangle){
        PointInPolygonUtilities pointInPolygonUtilities=new PointInPolygonUtilities();
        int totalProduct=
                Math.abs(pointInPolygonUtilities.triangleVectorProduct(
                        triangle.getCartesianCoordinate()[0],
                        triangle.getCartesianCoordinate()[1],
                        triangle.getCartesianCoordinate()[2]
                        ));
        int product1=
                Math.abs(pointInPolygonUtilities.triangleVectorProduct(
                        point,
                        triangle.getCartesianCoordinate()[0],
                        triangle.getCartesianCoordinate()[1]
                ));
        int product2=
                Math.abs(pointInPolygonUtilities.triangleVectorProduct(
                        point,
                        triangle.getCartesianCoordinate()[0],
                        triangle.getCartesianCoordinate()[2]
                ));
        int product3=
                Math.abs(pointInPolygonUtilities.triangleVectorProduct(
                        point,
                        triangle.getCartesianCoordinate()[1],
                        triangle.getCartesianCoordinate()[2]
                ));

        if ((product1+product2+product3)==totalProduct)
            return true;

        return  false;

    }
    public boolean pointOnLine(Point point,ShapeWithCartesian line){

        PointInPolygonUtilities pointInPolygonUtilities=new PointInPolygonUtilities();
        return pointInPolygonUtilities.betweenTwoPoints(point,line.getCartesianCoordinate()[0],line.getCartesianCoordinate()[1])
                && pointInPolygonUtilities.onSameSlope(point,line.getCartesianCoordinate()[0],line.getCartesianCoordinate()[1]);
    }
    public boolean pointInRectangle(Point point,ShapeWithCartesian rectangle){

        PointInPolygonUtilities pointInPolygonUtilities=new PointInPolygonUtilities();
        return pointInPolygonUtilities.betweenTwoPoints(point,rectangle.getCartesianCoordinate()[0],rectangle.getCartesianCoordinate()[1]);
    }
    public boolean pointInSquare(Point point,ShapeWithCartesian square){

        PointInPolygonUtilities pointInPolygonUtilities=new PointInPolygonUtilities();
        return pointInPolygonUtilities.betweenTwoPoints(point,square.getCartesianCoordinate()[0],square.getCartesianCoordinate()[1]);
    }

    public boolean pointInCircle(Point point, ShapeWithDimensions circle){

        PointInPolygonUtilities pointInPolygonUtilities=new PointInPolygonUtilities();
        return pointInPolygonUtilities.distance(point,circle.getPosition())<=circle.getDimensions()[0];
    }

    public boolean pointInEllipse(Point point, ShapeWithDimensions ellipse){

        PointInPolygonUtilities pointInPolygonUtilities=new PointInPolygonUtilities();
        return pointInPolygonUtilities.ellipseChecker(point,ellipse.getPosition(),
                ellipse.getDimensions()[0],ellipse.getDimensions()[1]);
    }
}
