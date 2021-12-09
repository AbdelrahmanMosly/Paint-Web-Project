package ShapeWithCartesianFactory;

import java.awt.*;

public class Triangle implements ShapeWithCartesian{
    private Point[] cartesianCoordinate=new Point[3];
    private String color;
    private boolean fill;
    Triangle(Point[] cartesianCoordinate,String color,boolean fill){
        this.cartesianCoordinate[0]=new Point(cartesianCoordinate[0].x,cartesianCoordinate[0].y);
        this.cartesianCoordinate[1]=new Point(cartesianCoordinate[1].x,cartesianCoordinate[1].y);
        this.cartesianCoordinate[2]=new Point(cartesianCoordinate[2].x,cartesianCoordinate[2].y);
        this.color=color;
        this.fill=fill;
    }
    @Override
    public Point[] getCartesianCoordinate(){
        return cartesianCoordinate;
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public void setCartesianCoordinate(Point[] cartesianCoordinate){
        this.cartesianCoordinate[0].x=cartesianCoordinate[0].x;
        this.cartesianCoordinate[0].y=cartesianCoordinate[0].y;

        this.cartesianCoordinate[1].x=cartesianCoordinate[1].x;
        this.cartesianCoordinate[1].y=cartesianCoordinate[1].y;

        this.cartesianCoordinate[2].x=cartesianCoordinate[2].x;
        this.cartesianCoordinate[2].y=cartesianCoordinate[2].y;
    }
    @Override
    public void setColor(String color) {
        this.color=color;
    }
    @Override
    public boolean isFill() {
        return fill;
    }
    @Override
    public void setFill(boolean fill) {
        this.fill = fill;
    }
    @Override
    public Cloneable clone(){
        return new Triangle(this.cartesianCoordinate,this.color,this.fill);
    }
    @Override
    public String toString(){
        return "Triangle {"
                +"\n point1 (x,y) = "+ this.getCartesianCoordinate()[0].x+","+this.getCartesianCoordinate()[0].y
                +"\n point2 (x,y) = "+ this.getCartesianCoordinate()[1].x+","+this.getCartesianCoordinate()[1].y
                +"\n point3 (x,y) = "+ this.getCartesianCoordinate()[2].x+","+this.getCartesianCoordinate()[2].y
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n }";
    }

}
