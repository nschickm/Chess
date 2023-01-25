package com.example.chess.controller;

import com.example.chess.model.Player;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class SettingsController extends AbstractController {
    public JFXComboBox comboBox;
    public JFXButton startBttn;
    public JFXComboBox comboBoxTheme;

    public static Color color1;
    public static Color color2;
    public static double time;



    public void initialize() {
        comboBox.getItems().addAll(
                "5 minutes",
                "10 minutes",
                "15 minutes",
                "20 minutes"
        );

        comboBoxTheme.getItems().addAll(FXCollections.observableArrayList("No Theme", "Sunset", "Royalty", "Blossom"));


    }

    public void startBttnClicked(ActionEvent actionEvent) {

        if (comboBox.getValue().equals("5 minutes")) {
            time = 300000;
        } else if (comboBox.getValue().equals("10 minutes")) {
            time = 600000;
        } else if (comboBox.getValue().equals("15 minutes")) {
            time = 900000;
        } else if (comboBox.getValue().equals("20 minutes")) {
            time = 1200000;
        }

        if (!comboBoxTheme.getSelectionModel().isEmpty()) {

            if (comboBoxTheme.getValue().equals("Sunset")) {
                color1 = Color.BLUE;
                color2 = Color.ORANGE;

            } else if (comboBoxTheme.getValue().equals("Royalty")) {
                color1 = Color.VIOLET;
                color2 = Color.YELLOW;

            } else if (comboBoxTheme.getValue().equals("Blossom")) {
                color1 = Color.GREEN;
                color2 = Color.PINK;

            } else if (comboBoxTheme.getValue().equals("No Theme")) {
                color1 = Color.WHITE;
                color2 = Color.BLACK;

            }
        }



        try {
            BoardController c = this.loadFxmlFile(
                    "board.fxml",
                    "Chess",
                    ((Button) actionEvent.getSource()).getScene().getWindow(),
                    BoardController.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
