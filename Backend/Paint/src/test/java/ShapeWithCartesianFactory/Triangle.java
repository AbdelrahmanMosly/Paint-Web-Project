package ShapeWithCartesianFactory;

public class Triangle implements ShapeWithCartesian{
    private int[][] cartesianCoordinate=new int[3][2];
    private String color;
    private boolean fill;
    Triangle(int[][] cartesianCoordinate,String color,boolean fill){
        this.cartesianCoordinate[0][0]=cartesianCoordinate[0][0];
        this.cartesianCoordinate[0][1]=cartesianCoordinate[0][1];

        this.cartesianCoordinate[1][0]=cartesianCoordinate[1][0];
        this.cartesianCoordinate[1][1]=cartesianCoordinate[1][1];

        this.cartesianCoordinate[2][0]=cartesianCoordinate[2][0];
        this.cartesianCoordinate[2][1]=cartesianCoordinate[2][1];

        this.color=color;
        this.fill=fill;
    }
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

        this.cartesianCoordinate[2][0]=cartesianCoordinate[2][0];
        this.cartesianCoordinate[2][1]=cartesianCoordinate[2][1];
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
                +"\n point1 (x,y) = "+ this.getCartesianCoordinate()[0][0]+","+this.getCartesianCoordinate()[0][1]
                +"\n point2 (x,y) = "+ this.getCartesianCoordinate()[1][0]+","+this.getCartesianCoordinate()[1][1]
                +"\n point3 (x,y) = "+ this.getCartesianCoordinate()[2][0]+","+this.getCartesianCoordinate()[2][1]
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n }";
    }

}
