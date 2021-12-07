package ShapeWithCartesianFactory;

public interface ShapeWithCartesian extends Cloneable{
    int[][] getCartesianCoordinate();
    String getColor();
    boolean isFill();
    void setFill(boolean fill);
    void setCartesianCoordinate(int[][] cartesianCoordinate);
    void setColor(String color);
    String toString();
}
