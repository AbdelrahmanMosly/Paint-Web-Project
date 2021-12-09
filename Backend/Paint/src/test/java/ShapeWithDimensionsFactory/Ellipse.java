package ShapeWithDimensionsFactory;

import java.awt.*;

public class Ellipse implements ShapeWithDimensions{
    private int radiusX;
    private int radiusY;
    private Point position;
    private String color;
    private boolean fill;
    private  int edgeWidth;
    public Ellipse(Point position,int[] dimensions,String color,boolean fill,int edgeWidth){
        this.position=new Point(position.x,position.y);
        this.radiusX=dimensions[0];
        this.radiusY=dimensions[1];
        this.color=color;
        this.fill=fill;
        this.edgeWidth=edgeWidth;
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
        return new int[] {this.radiusX,this.radiusY};
    }
    @Override
    public void setDimensions(int[] dimensions){
        this.radiusX= dimensions[0];
        this.radiusY=dimensions[1];
    }

    @Override
    public int getEdgeWidth() {
        return edgeWidth;
    }

    @Override
    public void setEdgeWidth(int edgeWidth) {
        this.edgeWidth = edgeWidth;
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
        return new Ellipse(this.position,dimensions,this.color,this.fill,this.edgeWidth);
    }
    @Override
    public String toString(){
        return  "Ellipse {"
                +"\n radiusX = "+ this.getDimensions()[0]
                +"\n radiusY= "+ this.getDimensions()[1]
                +"\n position (x,y) :"+ this.getPosition().x+","+ this.getPosition().y
                +"\n Color="+getColor()
                +"\n isFill="+isFill()
                +"\n EdgeWidth="+getEdgeWidth()
                +"\n }";
    }
}
