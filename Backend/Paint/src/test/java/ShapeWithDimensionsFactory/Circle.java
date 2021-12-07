package ShapeWithDimensionsFactory;

public class Circle implements ShapeWithDimensions{

    private int radius;
    private int x,y;//x and y position
    private String color;
    private boolean fill;
    public Circle(int x,int y,int[] dimensions,String color,boolean fill){
        this.x = x;
        this.y=y;
        this.radius=dimensions[0];
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
        return new int[] {this.radius};
    }
    @Override
    public void setDimensions(int[] dimensions){
        this.radius= dimensions[0];
    }

    @Override
    public String getColor() {
        return color;
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
        int[] dimensions={this.radius};
        return new Circle(this.x,this.y,dimensions,this.color,this.fill);
    }
    @Override
    public String toString(){
        return  "Circle {\n"
                +" radius = "+ this.getDimensions()[0]
                + "\n position (x,y) :"+ this.getPosition()[0]+","+ this.getPosition()[1]
                +"\n isFill="+isFill()
                +"\n }";
    }
}
