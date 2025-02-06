package com.main.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeGameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //snake game
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeGameApplication.class.getResource("View/Menu-Screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500 , 500);
        stage.setResizable(false);
        stage.setTitle("Snake Game!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}