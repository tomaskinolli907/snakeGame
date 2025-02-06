package com.main.snakegame.Controller;

import com.main.snakegame.Model.Snake;
import com.main.snakegame.Model.SnakeBody;
import com.main.snakegame.Model.Apple;
import com.main.snakegame.SnakeGameApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private Pane rootPane;
    private Snake snakeModel;
    private final Apple food = Apple.getInstance();
    private String lastKeyPressed = "";
    public static boolean gameOver = false;
    private int lagTime;
    private Integer score;
    @FXML
    private Label gameLabel;
    @FXML
    private HBox gameOverPane;
    @FXML
    private VBox notifPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        score = 1;

        gameLabel.setText(score.toString());
        this.snakeModel = new Snake(rootPane);
        rootPane.getChildren().add(food.getApple());
        rootPane.setStyle("-fx-background-color: black;");
    }
    public void setDifficulty(String difficulty){
        switch(difficulty){
            case "EASY":
                lagTime = 60;
                break;
            case "MEDIUM":
                lagTime = 40;
                break;
            case "HARD":
                lagTime = 30;
                break;
        }
    }
    private void gameOver(){
        notifPane.setDisable(false);
        Platform.runLater(()->gameLabel.setText("Booo! GAME OVER :("));
        //gameLabel.setStyle("-fx-background-color:  #0ac332;");
        gameOverPane.setDisable(false);
        gameOverPane.setVisible(true);
    }
    public void keyPressed(KeyEvent key) {
        SnakeBody head = snakeModel.getSnakeHead();
        String keyPressed  = key.getCode().toString();

        switch (keyPressed) {
            case "LEFT":
                if(!lastKeyPressed.equals("LEFT") && !lastKeyPressed.equals("RIGHT")) {
                    new Thread(()->{
                        while (true) {
                            try {
                                Thread.sleep(lagTime);
                                if (head.getSnake().getLayoutX() == 5)
                                    gameOver = true;
                                if((!lastKeyPressed.equals("RIGHT") && !lastKeyPressed.equals("LEFT")))
                                    break;
                                if(gameOver){
                                    gameOver();
                                    break;
                                }
                                head.moveSnake(head.getSnake().getLayoutX() - 5, head.getSnake().getLayoutY());
                                if((head.getSnake().getLayoutX() == food.getLocX()) && (head.getSnake().getLayoutY() == food.getLocY())){
                                    score += 5;
                                    Platform.runLater(()->gameLabel.setText(score.toString()));
                                    food.calculateNewLoc();
                                    head.getTail().addSeg(rootPane);
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
                    lastKeyPressed = "LEFT";
                }

                break;
            case "RIGHT":
                if(!lastKeyPressed.equals("RIGHT") && !lastKeyPressed.equals("LEFT")) {
                    new Thread(()->{
                        while (true) {
                            try {
                                Thread.sleep(lagTime);
                                if (head.getSnake().getLayoutX() == 490)
                                    gameOver = true;
                                if((!lastKeyPressed.equals("RIGHT") && !lastKeyPressed.equals("LEFT")))
                                    break;
                                if(gameOver){
                                    gameOver();
                                    break;
                                }
                                head.moveSnake(head.getSnake().getLayoutX() + 5, head.getSnake().getLayoutY());
                                if((head.getSnake().getLayoutX() == food.getLocX()) && (head.getSnake().getLayoutY() == food.getLocY())){
                                    score += 5;
                                    Platform.runLater(()->gameLabel.setText(score.toString()));
                                    food.calculateNewLoc();
                                    head.getTail().addSeg(rootPane);
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
                    lastKeyPressed = "RIGHT";
                }
                break;
            case "UP":
                if(!lastKeyPressed.equals("UP") && !lastKeyPressed.equals("DOWN")){
                    new Thread(()->{
                        while (true) {
                            try {
                                Thread.sleep(lagTime);
                                if (head.getSnake().getLayoutY() == 5)
                                    gameOver = true;
                                if (!lastKeyPressed.equals("UP") && !lastKeyPressed.equals("DOWN"))
                                    break;
                                if(gameOver){
                                    gameOver();
                                    break;
                                }
                                head.moveSnake(head.getSnake().getLayoutX(), head.getSnake().getLayoutY() - 5);
                                if((head.getSnake().getLayoutX() == food.getLocX()) && (head.getSnake().getLayoutY() == food.getLocY())){
                                    score += 5;
                                    Platform.runLater(()->gameLabel.setText(score.toString()));
                                    food.calculateNewLoc();
                                    head.getTail().addSeg(rootPane);
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
                    lastKeyPressed = "UP";
                }
                break;
            case "DOWN":
                if(!lastKeyPressed.equals("UP") && !lastKeyPressed.equals("DOWN")){
                    new Thread(()->{
                        while (true) {
                            try {
                                Thread.sleep(lagTime);
                                if (head.getSnake().getLayoutY() == 490)
                                    gameOver = true;
                                if (!lastKeyPressed.equals("UP") && !lastKeyPressed.equals("DOWN"))
                                    break;
                                if(gameOver){
                                    gameOver();
                                    break;
                                }
                                head.moveSnake(head.getSnake().getLayoutX(), head.getSnake().getLayoutY() + 5);
                                if((head.getSnake().getLayoutX() == food.getLocX()) && (head.getSnake().getLayoutY() == food.getLocY())){
                                    score += 5;
                                    Platform.runLater(()->gameLabel.setText(score.toString()));
                                    food.calculateNewLoc();
                                    head.getTail().addSeg(rootPane);
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
                    lastKeyPressed = "DOWN";
                }
                break;
            default:
                break;

        }

    }


    public void replayGame(ActionEvent actionEvent) {
        gameOver = false;
        score = 1;
        lastKeyPressed = "";
        Platform.runLater(()->gameLabel.setText(score.toString()));
        notifPane.setDisable(true);
        gameOverPane.setDisable(true);
        gameOverPane.setVisible(false);
        snakeModel.getSnakeHead().removeSeg(rootPane);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        gameOver = false;
        SnakeBody.resetSnake();
        FXMLLoader mainMenu = new FXMLLoader(SnakeGameApplication.class.getResource("View/Menu-Screen.fxml"));
        Scene menu = new Scene(mainMenu.load(), 500, 500);
        ((Stage)rootPane.getScene().getWindow()).setScene(menu);
    }
}