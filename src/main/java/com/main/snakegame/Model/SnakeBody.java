package com.main.snakegame.Model;

import com.main.snakegame.Controller.MainViewController;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class SnakeBody {
    private final Rectangle snake;
    private double locY;
    private double locX;
    private double oldX;
    private double oldY;
    private static SnakeBody instance;
    private static SnakeBody head;
    private final SnakeBody parent;
    private SnakeBody child = null;

    public SnakeBody(double locX, double locY){
        if(instance != null)
            instance.child = this;
        else
            head = this;

        this.parent = instance;
        snake = new Rectangle(7,7,Color.GREEN);
        snake.setStrokeType(StrokeType.INSIDE);
        snake.setStroke(Color.WHITE);
        this.locX = locX;
        this.locY = locY;
        snake.setLayoutX(this.locX);
        snake.setLayoutY(this.locY);
        instance = this;
    }
    public Rectangle getSnake() {return snake;}
    public void moveSnake(double x, double y){
        oldX = locX;
        oldY = locY;
        locX = x;
        locY = y;
        snake.setLayoutX(x);
        snake.setLayoutY(y);

        if(head.locX == this.locX && head.locY == this.locY && head != this)
            MainViewController.gameOver = true;
        if(child != null)
            child.moveSnake(oldX, oldY);
    }
    public SnakeBody getTail(){return instance;}
    public void addSeg(Pane root){
        if(instance.oldY>instance.locY && instance.oldX == instance.locX){ //moving up
            for(int i = 0; i < 5; i++){
                SnakeBody seg = new SnakeBody(instance.locX, instance.locY + 5);
                Platform.runLater(()-> root.getChildren().add(seg.getSnake()));

            }
        }
        if(instance.oldY<instance.locY && instance.oldX == instance.locX){//moving down
            for(int i = 0; i < 5; i++){
                SnakeBody seg = new SnakeBody(instance.locX, instance.locY - 5);
                Platform.runLater(()->root.getChildren().add(seg.getSnake()));
            }
        }
        if(instance.oldY == instance.locY && instance.oldX > instance.locX){//left
            for(int i = 0; i < 5; i++){
                SnakeBody seg = new SnakeBody(instance.locX + 5, instance.locY );
                Platform.runLater(()->root.getChildren().add(seg.getSnake()));
            }
        }
        if(instance.oldY == instance.locY && instance.oldX < instance.locX){//right
            for(int i = 0; i < 5; i++){
                SnakeBody seg = new SnakeBody(instance.locX - 5, instance.locY );
                Platform.runLater(()->root.getChildren().add(seg.getSnake()));
            }
        }
    }
    public void removeSeg(Pane root){
        if(this == head){
            this.locX = 250;
            this.locY = 250;
            snake.setLayoutX(250);
            snake.setLayoutY(250);
            this.oldX = 250;
            this.oldY = 250;
            instance = this;
        }
        else{
            root.getChildren().remove(snake);
            parent.child = null;
        }
        if(child != null)
            this.child.removeSeg(root);
    }
    public static void resetSnake(){ head = null; instance = null;};
}
