package ShapeWithDimensionsFactory;

import java.awt.*;

public interface ShapeWithDimensions extends Cloneable {
     void setPosition(Point position);
     Point getPosition();
     int[] getDimensions();
     void setDimensions(int[] dimensions);
     boolean isFill();
     void setFill(boolean fill);
     String getColor();
     void setColor(String color);
     String toString();
}
