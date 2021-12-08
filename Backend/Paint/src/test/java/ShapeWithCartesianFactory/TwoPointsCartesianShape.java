package ShapeWithCartesianFactory;

public abstract class TwoPointsCartesianShape implements ShapeWithCartesian{

    protected int[][] cartesianCoordinate=new int[2][2];
    protected String color;
    protected boolean fill;


    @Override
    public int[][] getCartesianCoordinate(){
        return cartesianCoordinate;
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public void setCartesianCoordinate(int[][] cartesianCoordinate){
        this.cartesianCoordinate[0][0]=cartesianCoordinate[0][0];
        this.cartesianCoordinate[0][1]=cartesianCoordinate[0][1];
        this.cartesianCoordinate[1][0]=cartesianCoordinate[1][0];
        this.cartesianCoordinate[1][1]=cartesianCoordinate[1][1];
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
