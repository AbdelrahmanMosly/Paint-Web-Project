package ShapeWithDimensionsFactory;

import java.awt.*;

public class Circle implements ShapeWithDimensions{

    private int radius;
    private Point position;
    private String color;
    private boolean fill;
    public Circle(Point position,int[] dimensions,String color,boolean fill){
        this.position=new Point(position.x,position.y);
        this.radius=dimensions[0];
        this.color=color;
        this.fill=fill;
    }
    @Override
    public void setPosition(Point position){
        this.position.x=position.x;
        this.position.y=position.y;
    }
    @Override
    public Point getPosition(){
        return position;
    }
    @Override
    public int[] getDimensions(){
        return new int[] {radius};
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
        return new Circle(this.position,dimensions,this.color,this.fill);
    }
    @Override
    public String toString(){
        return  "Circle {"
                +"\n radius = "+ this.getDimensions()[0]
                +"\n position (x,y) :"+ this.getPosition().x+","+ this.getPosition().y
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n }";
    }
}
