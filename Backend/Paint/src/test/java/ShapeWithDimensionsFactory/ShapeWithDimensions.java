package ShapeWithDimensionsFactory;

public interface ShapeWithDimensions extends Cloneable {
     void setPosition(int x,int y);
     int[] getPosition();
     int[] getDimensions();
     void setDimensions(int[] dimensions);
     boolean isFill();
     void setFill(boolean fill);
     String getColor();
     void setColor(String color);
     String toString();
}
