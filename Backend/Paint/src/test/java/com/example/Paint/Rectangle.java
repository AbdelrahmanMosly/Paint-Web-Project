package com.example.Paint;

public class Rectangle implements Shape {
    private double length,width;
    private float x,y;//x and y position
    public Rectangle(float x,float y,double[] dimensions){
        this.x = x;
        this.y=y;
        this.length=dimensions[0];
        this.width=dimensions[1];
    }
    @Override
    public void setPosition(float x,float y){
        this.x=x;
        this.y=y;
    }
    @Override
    public float[] getPosition(){
        return new float[]{x,y};
    }
    /*
    public void setLength(double length){
        this.length=length;
    }
    public void setWidth(double width){
        this.width=width;
    }
    public double getLength(){
        return this.length;
    }
    public double getWidth(){
        return this.width;
    }*/
    @Override
    public double[] getDimensions(){
        return new double[] {this.length,this.width};
    }
    @Override
    public void setDimensions(double[] dimensions){
        this.length=dimensions[0];
        this.width=dimensions[1];
    }
    @Override
    public Cloneable clone(){
        double[] dimensions={this.length,this.width};
        return new Rectangle( x,y,dimensions);
    }
    @Override
    public String toString(){
        String s = "Rectangle {\n"
                + " length = " + this.getDimensions()[0]
                + " width = " + this.getDimensions()[1]
                + "\n position (x,y) :" + this.getPosition()[0] + "," + this.getPosition()[1] +
                "\n }";
        return s;
    }
}
