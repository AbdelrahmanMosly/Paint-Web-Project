package ShapeWithCartesianFactory;

public class Square extends TwoPointsCartesianShape{
    Square(int[][] cartesianCoordinate,String color,boolean fill){
        this.cartesianCoordinate[0][0]=cartesianCoordinate[0][0];
        this.cartesianCoordinate[0][1]=cartesianCoordinate[0][1];
        this.cartesianCoordinate[1][0]=cartesianCoordinate[1][0];
        this.cartesianCoordinate[1][1]=cartesianCoordinate[1][1];
        this.color=color;
        this.fill=fill;
    }
    @Override
    public Cloneable clone(){
        return new Square(cartesianCoordinate,color,fill);
    }
    @Override
    public String toString(){
        return "Square {"
                +"\n start(x,y) = "+ this.getCartesianCoordinate()[0][0]+","+this.getCartesianCoordinate()[0][1]
                +"\n end(x,y) = "+ this.getCartesianCoordinate()[1][0]+","+this.getCartesianCoordinate()[1][1]
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n }";
    }
}
