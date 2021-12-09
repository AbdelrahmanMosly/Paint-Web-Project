package com.example.Paint.Models;

import java.awt.*;

public class Square extends Rectangle implements MaintainRatio{

    public Square(int strokeSize, String color, boolean filled, Point p1, Point p2) {
        super(strokeSize, color, filled, p1, p2);
    }
}
