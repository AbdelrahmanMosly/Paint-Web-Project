package com.example.Paint.DAO;

import com.example.Paint.Models.Shape;

import java.util.List;

public interface IDAO {
    List<Shape> findAll();
    Shape findById(int ID);
    boolean insertShape(Shape shape);
    boolean deleteShape(Shape shape);
}
