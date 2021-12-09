package ShapeWithCartesianFactory;

import java.awt.*;

public abstract class TwoPointsCartesianShape implements ShapeWithCartesian{

    protected Point[] cartesianCoordinate=new Point[2];
    protected String color;
    protected boolean fill;
    protected int edgeWidth;


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
        this.cartesianCoordinate[0]=cartesianCoordinate[0];
        this.cartesianCoordinate[1]=cartesianCoordinate[1];
    }

    @Override
    public int getEdgeWidth() {
        return edgeWidth;
    }

    @Override
    public void setEdgeWidth(int edgeWidth) {
        this.edgeWidth = edgeWidth;
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
    public abstract Cloneable clone();
    @Override
    public abstract String toString();


}
