package com.example.Paint.Controller;

import com.example.Paint.DAO.DAO;
import com.example.Paint.Models.ApiShape;
import com.example.Paint.Models.Shape;
import com.example.Paint.Models.ShapeFactory;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.dsig.XMLObject;
import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.Scanner;

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
    private Gson gson=new Gson();
    String load;

    @PostMapping("/create")
    public void createShape(@RequestBody ApiShape apiShape){
        try {
            Shape shape = (Shape) shapeFactory.createShape(apiShape.getShapeType(), apiShape.getStrokeSize(), apiShape.getColor(), apiShape.getFilled(),
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

    String stringfyJason(){
        if(dao.findAll().size()==0)
            return "{}" ;
        ArrayList<Shape> objects=new ArrayList<Shape>(dao.findAll());
        System.out.println(gson.toJson(objects));
        return gson.toJson(objects);
    }

    @PostMapping("save")
    public void saveXML() throws JSONException {

        boolean isJasonExtension;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Path");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
        fileChooser.setFileFilter(filter);
        filter = new FileNameExtensionFilter("json files (*.json)", "json");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(new JFrame());
        if(userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String file = ((File) fileToSave).getAbsolutePath();
            if(fileChooser.getFileFilter().equals(filter)) {
                if (!file.endsWith(".json"))
                    file = new String(file + ".json");
                isJasonExtension = true;
            }
            else {
                if(!file.endsWith(".xml"))
                    file = new String(file + ".xml");
                isJasonExtension = false;
            }

            try {
                File myObj = new File(file);
                if(myObj.createNewFile())
                    System.out.println("File Saved at : " + myObj.getName());
                else
                    System.out.println("File Exists");
            } catch (IOException e) {
                System.out.println("ERROR");
                e.printStackTrace();
                return;
            }

            if(isJasonExtension) {
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(this.stringfyJason());
                    writer.close();
                } catch (IOException e) {
                    System.out.println("ERROR");
                    e.printStackTrace();
                }

            }

            else {
                JSONObject json = new JSONObject(this.stringfyJason());
                String xml = XML.toString(json);
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(xml);
                    writer.close();
                } catch (IOException e) {
                    System.out.println("ERROR");
                    e.printStackTrace();
                }
            }
        }
    }



    @PostMapping("/load")

    public String load() throws FileNotFoundException, JSONException{
        JSONObject json;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Path");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML and JSON files", "xml", "json");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(new JFrame());
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String file = selectedFile.getAbsolutePath();
            if(file.endsWith(".json")) {
                Scanner myReader = new Scanner(selectedFile);
                while(myReader.hasNextLine()) {
                    this.load = myReader.nextLine();
                }
                myReader.close();
            }
            else {
                Scanner myReader = new Scanner(selectedFile);
                while(myReader.hasNextLine()) {
                    json = XML.toJSONObject(myReader.nextLine());
                    load = json.toString();
                }
                myReader.close();
            }
        }
        System.out.println(this.load);
        return this.load;
    }

}
