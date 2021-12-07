package ShapeWithDimensionsFactory;

public class Square implements ShapeWithDimensions{
    private int side;
    private int x,y;//x and y position
    private String color;
    private boolean fill;
    public Square(int x,int y,int[] dimensions,String color,boolean fill){
        this.x = x;
        this.y=y;
        this.side=dimensions[0];
        this.color=color;
        this.fill=fill;
    }
    @Override
    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public int[] getPosition(){
        return new int[]{x,y};
    }
    @Override
    public int[] getDimensions(){
        return new int[] {this.side};
    }
    @Override
    public void setDimensions(int[] dimensions){
        this.side= dimensions[0];
    }
    @Override
    public void setColor(String color) {
        this.color=color;
    }
    @Override
    public String getColor() {
        return color;
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
        int[] dimensions={this.side};
        return new Square(this.x,this.y,dimensions,this.color,this.fill);
    }
    @Override
    public String toString(){
        return  "Square {\n"
                + " Side = " + this.getDimensions()[0]
                + "\n position (x,y) :" + this.getPosition()[0] + "," + this.getPosition()[1]
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n }";
    }
}
