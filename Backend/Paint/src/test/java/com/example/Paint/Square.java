package com.example.Paint;

public class Square implements Shape{
    private double side;
    private float x,y;//x and y position
    public Square(float x,float y,double[] dimensions){
        this.x = x;
        this.y=y;
        this.side=dimensions[0];
    }
    @Override
    public void setPosition(float x,float y){
        this.x=x;
        this.y=y;
    }
    @Override
    public float[] getPosition(){
        return new float[]{x,y};
    }/*
    public void setSide(double side){
        this.side=side;
    }
    public double getSide(){
        return this.side;
    }*/
    @Override
    public double[] getDimensions(){
        return new double[] {this.side};
    }
    @Override
    public void setDimensions(double[] dimensions){
        this.side= dimensions[0];
    }
    @Override
    public Cloneable clone(){
        double[] dimensions={this.side};
        return new Square(x,y,dimensions);
    }
    @Override
    public String toString(){
        String s = "Square {\n"
                + " Side = " + this.getDimensions()[0]
                + "\n position (x,y) :" + this.getPosition()[0] + "," + this.getPosition()[1] +
                "\n }";
        return s;
    }
}
