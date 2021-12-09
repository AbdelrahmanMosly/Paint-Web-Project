package ShapeWithCartesianFactory;

import java.awt.*;

public class Rectangle extends TwoPointsCartesianShape{
    Rectangle(Point[] cartesianCoordinate, String color, boolean fill,int edgeWidth){
        this.cartesianCoordinate[0]=new Point(cartesianCoordinate[0].x,cartesianCoordinate[0].y);
        this.cartesianCoordinate[1]=new Point(cartesianCoordinate[1].x,cartesianCoordinate[1].y);
        this.color=color;
        this.fill=fill;
        this.edgeWidth=edgeWidth;
    }
    @Override
    public Cloneable clone(){
        return new Rectangle(cartesianCoordinate,color,fill,this.edgeWidth);
    }
    @Override
    public String toString(){
        return  "Rectangle {"
                +"\n start(x,y) = "+ this.getCartesianCoordinate()[0].x+","+this.getCartesianCoordinate()[0].y
                +"\n end(x,y) = "+ this.getCartesianCoordinate()[1].y+","+this.getCartesianCoordinate()[1].y
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n EdgeWidth="+getEdgeWidth()
                +"\n }";
    }
}
