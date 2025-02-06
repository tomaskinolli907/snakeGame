package com.main.snakegame.Controller;

import com.main.snakegame.SnakeGameApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuScreenController {

    @FXML
    ToggleGroup difficulty;

    @FXML
    AnchorPane rootPane;

    public void startGame() throws IOException {
        String difficultySelected = ((RadioButton)difficulty.getSelectedToggle()).getText();
        FXMLLoader loadGame = new FXMLLoader(SnakeGameApplication.class.getResource("View/main-view.fxml"));
        Scene gameScene = new Scene(loadGame.load(), 500, 500);
        ((Stage)rootPane.getScene().getWindow()).setScene(gameScene);
        MainViewController gameController = loadGame.getController();
        gameController.setDifficulty(difficultySelected);
        gameScene.setOnKeyPressed(gameController::keyPressed);
    }
}
