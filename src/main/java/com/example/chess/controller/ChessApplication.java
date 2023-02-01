package com.example.chess.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * ChessApplication class is the main class to launch the Chess game application.
 * It extends the javafx.application.Application class.
 */
public class ChessApplication extends Application {

    /**
     * start method is overridden from the javafx.application.Application class.
     * It is used to initialize the stage, set the title and scene for the chess game application.
     *
     * @param stage The stage where the game application is going to be displayed
     * @throws IOException If there is an error while loading the fxml file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("testlogindesign.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chess");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * main method is the entry point for the Chess game application.
     * It launches the application by calling the launch method from the javafx.application.Application class.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
