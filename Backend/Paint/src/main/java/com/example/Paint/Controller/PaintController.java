package com.example.Paint.Controller;

import com.example.Paint.DAO.DAO;
import com.example.Paint.Models.ApiShape;
import com.example.Paint.Models.Shape;
import com.example.Paint.Models.ShapeFactory;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.XMLObject;
import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping()
public class PaintController {

    private final ShapeFactory shapeFactory = new ShapeFactory();
    private final DAO dao = new DAO();
    private ArrayList<Shape> selected = new ArrayList<>();
    private int previousX;
    private int previousY;

    @PostMapping("/create")
    public void createShape(@RequestBody ApiShape apiShape){
        Shape shape = (Shape) shapeFactory.createShape(apiShape.getShapeType(), apiShape.getStrokeSize(), apiShape.getColor(), apiShape.isFill(),
                apiShape.getP1(), apiShape.getP2(), apiShape.getP3(), apiShape.getR1(), apiShape.getR2());
        dao.insertShape(shape);
    }


    @GetMapping("/select")
    public ArrayList<Shape> select(@RequestParam int mouseX, @RequestParam int mouseY){
        Point cursor = new Point(mouseX, mouseY);
        ArrayList<Shape> shapes = (ArrayList<Shape>) dao.findAll();

        for (Shape currentShape : shapes) {
            if (currentShape.cursorOnShape(cursor)) {
                if (!selected.contains(currentShape))
                    selected.add(currentShape);
                else
                    selected.remove(currentShape);
            }
        }

        return selected;
    }

    @GetMapping("/draw")
    public ArrayList<Shape> draw(){
        return (ArrayList<Shape>) dao.findAll();
    }

    @PostMapping("/resize")
    public void resize(@RequestBody boolean increase){
        double scale = 1.00;
        if(increase)
           scale += 0.05;
        else
            scale -= 0.05;

        ArrayList<Shape> nextSelected = new ArrayList<>();
        for (Shape currentShape : selected) {
            Shape newShape = (Shape) currentShape.resize(scale);
            dao.deleteShape(currentShape);
            dao.insertShape(newShape);
            nextSelected.add(newShape);
        }
        selected = nextSelected;
    }

    @PostMapping("/move/setInitialPosition")
    public void setInitial(@RequestBody int mouseX, @RequestBody int mouseY){
        previousX = mouseX;
        previousY = mouseY;
    }
    @PostMapping("/move/doAction")
    public void move(@RequestBody int mouseX, @RequestBody int mouseY){
        int diffX = mouseX - previousX;
        int diffY = mouseY - previousY;

        ArrayList<Shape> nextSelected = new ArrayList<>();
        for (Shape currentShape : selected) {
            Shape newShape = (Shape) currentShape.move(diffX, diffY);
            dao.deleteShape(currentShape);
            dao.insertShape(newShape);
            nextSelected.add(newShape);
        }
        selected = nextSelected;

        previousX = mouseX;
        previousY = mouseY;
    }

    @PostMapping("/copy")
    public void copy(){
        ArrayList<Shape> nextSelected = new ArrayList<>();
        for (Shape currentShape : selected) {
            Shape newShape = (Shape) currentShape.move(5, 5);
            dao.insertShape(newShape);
        }
    }

    @PostMapping("/delete")
    public void delete(){
        for (Shape currentShape : selected) {
            dao.deleteShape(currentShape);
        }
        selected = new ArrayList<>();
    }

    @GetMapping("/undo")
    public void undo(){
        dao.previousState();
    }

    @GetMapping("/redo")
    public void redo(){
        dao.nextState();
    }

    @GetMapping("/save/XML")
    public void saveXML(){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("./painting.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(dao.getDb());
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/load/XML")
    public void loadXML(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("./painting.xml"));
            XMLDecoder decoder = new XMLDecoder(fis);
            dao.setDb((ArrayList<Shape>) decoder.readObject());
            decoder.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
