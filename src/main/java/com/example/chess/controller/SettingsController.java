package com.example.chess.controller;

import com.example.chess.model.Player;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class SettingsController extends AbstractController {
    public JFXComboBox comboBox;
    public JFXButton startBttn;
    public JFXComboBox comboBoxTheme;

    public static String color1;
    public static String color2;
    public static double time;
    public Label labelClose;

    public void initialize() {
        comboBox.getItems().addAll(
                "5 minutes",
                "10 minutes",
                "15 minutes",
                "20 minutes"
        );

        comboBoxTheme.getItems().addAll(FXCollections.observableArrayList("No Theme", "Sunset", "Royalty", "Blossom"));


    }

    public void startBttnClicked(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) labelClose.getScene().getWindow();
        stage.close();

        if (!comboBoxTheme.getSelectionModel().isEmpty()) {

            if (comboBoxTheme.getValue().equals("Sunset")) {
                color1 = "BLUE";
                color2 = "ORANGE";

            } else if (comboBoxTheme.getValue().equals("Royalty")) {
                color1 = "VIOLET";
                color2 = "YELLOW";

            } else if (comboBoxTheme.getValue().equals("Blossom")) {
                color1 = "GREEN";
                color2 = "PINK";

            } else if (comboBoxTheme.getValue().equals("No Theme")) {
                color1 = "WHITE";
                color2 = "BLACK";

            }
            System.out.println(color1 + " und " + color2);

            File file = new File("target/classes/com/example/chess/controller/board.css");
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.write(".field {\n" +
                        "    -fx-background-color:" + color1 + ";\n" +
                        "}" +
                        ".field1 {\n" +
                        "    -fx-background-color:" + color2 + ";\n" +
                        "}" +
                        ".anchorPane {" +
                        "-fx-background-color:" + color2 +
                        "}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.flush();
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (comboBox.getValue().equals("5 minutes")) {
            time = 300000;
        } else if (comboBox.getValue().equals("10 minutes")) {
            time = 600000;
        } else if (comboBox.getValue().equals("15 minutes")) {
            time = 900000;
        } else if (comboBox.getValue().equals("20 minutes")) {
            time = 1200000;
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
