package com.example.Paint.DAO;

import com.example.Paint.Models.Shape;
import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class DAO implements IDAO{
    private ArrayList<Shape> db = new ArrayList<>();
    private Stack<ArrayList<Shape>> stackUndo = new Stack<>();
    private Stack<ArrayList<Shape>> stackRedo = new Stack<>();
    public DAO (){
        stackUndo.push((ArrayList<Shape>) db.clone());
    }
    public ArrayList<Shape> getDb() {
        return db;
    }

    public void setDb(ArrayList<Shape> db) {
        this.db = db;
    }

    @Override
    public List<Shape> findAll() {
        List<Shape> ret = new ArrayList<Shape>(db);
        return ret;
    }

    @Override
    public Shape findById(int ID) {
        if(ID >= db.size())
            return null;
        return db.get(ID);
    }

    @Override
    public boolean insertShape(Shape shape) {
        try{
            db.add(shape);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteShape(Shape shape) {
        try{
            db.remove(shape);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Stack<ArrayList<Shape>> getStackUndo() {
        return stackUndo;
    }

    public void setStackUndo(Stack<ArrayList<Shape>> stackUndo) {
        this.stackUndo = stackUndo;
    }

    public Stack<ArrayList<Shape>> getStackRedo() {
        return stackRedo;
    }

    public void setStackRedo(Stack<ArrayList<Shape>> stackRedo) {
        this.stackRedo = stackRedo;
    }

    public void maintainState(){
        stackUndo.push((ArrayList<Shape>) db.clone());
        stackRedo.clear();
    }
    public void undoReset(){
        stackUndo.clear();
        //db.clear();
        stackUndo.push((ArrayList<Shape>) db.clone());
    }
    public void redoReset(){
        stackRedo.clear();
    }
    public void nextState(){
        try{

            db = (ArrayList<Shape>) stackRedo.peek().clone();
            stackUndo.push((ArrayList<Shape>) stackRedo.pop().clone());

        }catch(EmptyStackException e){
            //System.err.println("nextState Empty Stack");
        }
    }

    public void previousState(){
        try{
            db = (ArrayList<Shape>) stackUndo.peek().clone();
            stackRedo.push((ArrayList<Shape>) stackUndo.pop().clone());
        }catch(EmptyStackException e){
            //System.err.println("previousState Empty Stack");
        }
    }
}
