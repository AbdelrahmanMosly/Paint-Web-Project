package com.example.Paint.Controller;

import com.example.Paint.DAO.DAO;
import com.example.Paint.Models.ApiShape;
import com.example.Paint.Models.Shape;
import com.example.Paint.Models.ShapeFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.xml.crypto.dsig.XMLObject;
import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/paint")
public class PaintController {

    private final ShapeFactory shapeFactory = new ShapeFactory();
    private final DAO dao = new DAO();
    private ArrayList<Shape> selected = new ArrayList<>();
    private ArrayList<Shape> moved = new ArrayList<>();
    private int previousX;
    private int previousY;

    @PostMapping("/create")
    public void createShape(@RequestBody ApiShape apiShape){
        try {
            Shape shape = (Shape) shapeFactory.createShape(apiShape.getShapeType(), apiShape.getStrokeSize(), apiShape.getColor(), apiShape.isFill(),
                    apiShape.getP1(), apiShape.getP2(), apiShape.getP3(), apiShape.getR1(), apiShape.getR2());
            if(shape != null) {
                dao.insertShape(shape);
                dao.maintainState();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        deselect();
    }


    @PostMapping("/select")
    public ArrayList<Shape> select(@RequestBody Point cursor){

        ArrayList<Shape> shapes = (ArrayList<Shape>) dao.findAll();

        for (Shape currentShape : shapes) {
            if (currentShape.cursorOnShape(cursor))
                selected.add(currentShape);
        }

        return selected;
    }

    @GetMapping("/draw")
    public ArrayList<Shape> draw(){
        return (ArrayList<Shape>) dao.findAll();
    }

    @PostMapping("/resize")
    public void resize(@RequestBody Point point){
        int diffX = point.x - previousX;
        int diffY = point.y - previousY;

        ArrayList<Shape> nextSelected = new ArrayList<>();
        for (Shape currentShape : selected) {
            Shape newShape = (Shape) currentShape.resize(diffX,diffY);
            dao.deleteShape(currentShape);
            nextSelected.add(newShape);
        }
        selected = nextSelected;

        previousX = point.x;
        previousY = point.y;
    }
    @PostMapping("/endResizing")
    public void endResize(){

        for (Shape currentShape : selected) {
            currentShape.maintainResizeSelection();
            dao.insertShape(currentShape);
        }
        dao.maintainState();
        deselect();
    }
    @PostMapping("/setInitialPosition")
    public void setInitial(@RequestBody Point point){
        previousX = point.x;
        previousY = point.y;
    }
    @PostMapping("/doAction")
    public void move(@RequestBody Point point){
        int diffX = point.x - previousX;
        int diffY = point.y - previousY;

        ArrayList<Shape> nextSelected = new ArrayList<>();
        for (Shape currentShape : selected) {
            Shape newShape = (Shape) currentShape.move(diffX, diffY);
            System.out.println(newShape.getShapeType());
            dao.deleteShape(currentShape);
            nextSelected.add(newShape);
        }
        selected = nextSelected;

        previousX = point.x;
        previousY = point.y;
    }
    @PostMapping("/endAction")
    public void endMove(){

        for (Shape currentShape : selected) {
            dao.insertShape(currentShape);
        }
        dao.maintainState();
        deselect();
    }

    @GetMapping("/actionsDraw")
    public ArrayList<Shape> actionDraw(){
        ArrayList<Shape> ret=new ArrayList<Shape>(dao.findAll());
        for (Shape currentShape : selected) {
            ret.add(currentShape);
        }

        return ret;
    }

    @PostMapping("/copy")
    public void copy(){
        ArrayList<Shape> nextSelected = new ArrayList<>();

        for (Shape currentShape : selected) {
            Shape newShape = (Shape) currentShape.move(20, 20);
            dao.insertShape(newShape);
        }
        dao.maintainState();
    }

    @PostMapping("/delete")
    public void delete(){
        for (Shape currentShape : selected) {
            dao.deleteShape(currentShape);
        }
        dao.maintainState();
        selected = new ArrayList<>();
    }

    @PostMapping("/undo")
    public void undo(){
        dao.previousState();
    }

    @PostMapping("/redo")
    public void redo(){
        dao.nextState();
    }

    @PostMapping("/save/XML")
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

    @PostMapping("/load/XML")
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


    @PostMapping("/clear")
    public void clear(){
        selected.clear();
        dao.undoReset();
        dao.redoReset();
        dao.setDb(new ArrayList<>());
    }

    @PostMapping("/deselect")
    public void deselect(){
        selected.clear();
    }
}
