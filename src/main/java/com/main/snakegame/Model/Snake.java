package com.main.snakegame.Model;

import javafx.scene.layout.Pane;

public class Snake {
    private final SnakeBody snakeHead;

    public Snake(Pane root){
        snakeHead = new SnakeBody(250, 250);
        root.getChildren().add(snakeHead.getSnake());
    }
    public SnakeBody getSnakeHead(){return snakeHead;}
}
