package ShapeWithCartesianFactory;

import java.awt.*;

public interface ShapeWithCartesian extends Cloneable{
    public Point[] getCartesianCoordinate();
    String getColor();
    boolean isFill();
    void setFill(boolean fill);
    void setEdgeWidth(int edgeWidth);
    int getEdgeWidth();
    public void setCartesianCoordinate(Point[] cartesianCoordinate);
    void setColor(String color);
    String toString();
}
