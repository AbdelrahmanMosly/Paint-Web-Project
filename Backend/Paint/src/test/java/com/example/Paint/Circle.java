package com.example.Paint;

public class Circle implements Shape{

    private double radius;
    private float x,y;//x and y position
    public Circle(float x,float y,double[] dimensions){
        this.x = x;
        this.y=y;
        this.radius=dimensions[0];
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
    @Override
    public double[] getDimensions(){
        return new double[] {this.radius};
    }
    @Override
    public void setDimensions(double[] dimensions){
        this.radius= dimensions[0];
    }/*
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getRadius(){
        return this.radius;
    }*/

    @Override
    public Cloneable clone(){
        double[] dimensions={this.radius};
        return new Circle(x,y,dimensions);
    }
    @Override
    public String toString(){
        String s= "Circle {\n"
                +" radius = "+ this.getDimensions()[0]
                + "\n position (x,y) :"+ this.getPosition()[0]+","+ this.getPosition()[1]+
                "\n }";
        return  s;
    }
}
