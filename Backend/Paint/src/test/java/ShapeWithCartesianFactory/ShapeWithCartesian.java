package ShapeWithCartesianFactory;

import java.awt.*;

public interface ShapeWithCartesian extends Cloneable{
    public Point[] getCartesianCoordinate();
    String getColor();
    boolean isFill();
    void setFill(boolean fill);
    public void setCartesianCoordinate(Point[] cartesianCoordinate);
    void setColor(String color);
    String toString();
}
