package com.example.Paint;

public interface Shape extends Cloneable {
    String toString();
    void setPosition(float x,float y);
    float[] getPosition();
    public double[] getDimensions();
    public void setDimensions(double[] dimensions);
}
