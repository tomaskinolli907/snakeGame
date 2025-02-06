package com.main.snakegame.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.Random;

public class Apple {
    private static Apple instance;
    private double locX;
    private double locY;
    private final Rectangle food;
    private final Random rand = new Random();

    private Apple(){
        food = new Rectangle(7,7, Color.RED);
        food.setStrokeType(StrokeType.INSIDE);
        food.setStroke(Color.BLACK);
        calculateNewLoc();
        instance = this;
    }
    public static Apple getInstance(){
        if(instance == null)
            return new Apple();
        return instance;
    }
    public double getLocX() {
        return locX;
    }
    public double getLocY(){
        return locY;
    }
    public Rectangle getApple(){ return food;}
    public void calculateNewLoc(){
        locX = rand.nextInt(490 - 10 + 1) + 10;
        if(locX % 5 !=0)
            locX -= locX % 5;
        locY = rand.nextInt(490 - 10 + 1) + 10;
        if(locY % 5 != 0)
            locY -= locY % 5;
        food.setLayoutX(locX);
        food.setLayoutY(locY);

    }
}
