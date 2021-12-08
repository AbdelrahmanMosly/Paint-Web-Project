package ShapeWithDimensionsFactory;

public class Ellipse implements ShapeWithDimensions{
    private int radiusX;
    private int radiusY;
    private int x,y;//x and y position
    private String color;
    private boolean fill;
    public Ellipse(int x,int y,int[] dimensions,String color,boolean fill){
        this.x = x;
        this.y=y;
        this.radiusX=dimensions[0];
        this.radiusY=dimensions[1];
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
        return new int[]{this.x,this.y};
    }
    @Override
    public int[] getDimensions(){
        return new int[] {this.radiusX,this.radiusY};
    }
    @Override
    public void setDimensions(int[] dimensions){
        this.radiusX= dimensions[0];
        this.radiusY=dimensions[1];
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
        int[] dimensions={this.radiusX,this.radiusY};
        return new Ellipse(this.x,this.y,dimensions,this.color,this.fill);
    }
    @Override
    public String toString(){
        return  "Ellipse {"
                +"\n radiusX = "+ this.getDimensions()[0]
                +"\n radiusY= "+ this.getDimensions()[1]
                +"\n position (x,y) :"+ this.getPosition()[0]+","+ this.getPosition()[1]
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n }";
    }
}
