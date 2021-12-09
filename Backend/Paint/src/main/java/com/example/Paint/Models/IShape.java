package com.example.Paint.Models;

import java.awt.*;

public interface IShape {
    void setStrokeSize(int strokeSize);
    int getStrokeSize();
    void setColor(String color);
    String getColor();
    void setFilled(boolean filled);
    boolean getFilled();

    boolean cursorOnShape(Point point);
    IShape move(int x, int y);
    IShape resize(double scale);
}
