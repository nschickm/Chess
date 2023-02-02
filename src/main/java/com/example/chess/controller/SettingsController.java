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

import java.io.*;
import java.net.URL;

/**
 * The SettingsController class is responsible for controlling the settings of the chess game.
 * It provides options to select game time and theme color.
 *
 * @author nschickm, meder1, decker
 */
public class SettingsController extends AbstractController {
    public JFXComboBox comboBox;
    public JFXButton startBttn;
    public JFXComboBox comboBoxTheme;

    public static String color1;
    public static String color2;
    public static double time;
    public Label labelClose;

    /**
     * Initialize method is used to add options for game time and theme color in the comboBox and comboBoxTheme.
     */
    public void initialize() {
        comboBox.getItems().addAll(
                "5 minutes",
                "10 minutes",
                "15 minutes",
                "20 minutes"
        );

        comboBoxTheme.getItems().addAll(FXCollections.observableArrayList("No Theme", "Wooden", "Royalty", "Blossom"));


    }

    /**
     * startBttnClicked method is called when the start button is clicked. It sets the game time and theme color.
     * It also closes the settings window and opens the board window.
     *
     * @param actionEvent the action event that triggers this method
     * @throws IOException if an I/O error occurs
     */
    public void startBttnClicked(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) labelClose.getScene().getWindow();
        stage.close();

        if (!comboBoxTheme.getSelectionModel().isEmpty()) {

            if (comboBoxTheme.getValue().equals("Wooden")) {
                color1 = "#5e3323";
                color2 = "#be9155";

            } else if (comboBoxTheme.getValue().equals("Royalty")) {
                color1 = "#6b879d";
                color2 = "#3c617e";

            } else if (comboBoxTheme.getValue().equals("Blossom")) {
                color1 = "YELLOW";
                color2 = "VIOLET";

            } else if (comboBoxTheme.getValue().equals("No Theme")) {
                color1 = "#769656";
                color2 = "#eeeed2";

            }

            File boardCssPath = new File("target/classes/com/example/chess/controller/board.css");
            FileWriter boardCssWriter = null;

            File colorCssPath = new File("target/classes/com/example/chess/controller/color.css");
            FileWriter colorCssWriter = null;
            try {
                boardCssWriter = new FileWriter(boardCssPath);
                boardCssWriter.write(".field {\n" +
                        "    -fx-background-color:" + color1 + ";\n" +
                        "}" +
                        ".field1 {\n" +
                        "    -fx-background-color:" + color2 + ";\n" +
                        "}" +
                        ".anchorPane {" +
                        "-fx-background-color:" + color2 +
                        "}");


                StringBuilder colorCssContent = new StringBuilder();
                File file2 = new File("src/main/resources/com/example/chess/controller/color.css");
                try {
                    FileReader fr = new FileReader(file2);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        colorCssContent.append(line);
                    }
                    br.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found: " + boardCssPath.toString());
                } catch (IOException e) {
                    System.out.println("Unable to read boardCssPath: " + boardCssPath.toString());
                }

                colorCssWriter = new FileWriter(colorCssPath);
                colorCssWriter.write(colorCssContent +
                        ".buttonStyle {\n" +
                        "   -fx-background-color: linear-gradient(to right top, " + color1 + ", " + color2 + ");\n" +
                        "}");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (boardCssWriter != null) {
                        boardCssWriter.flush();
                        boardCssWriter.close();
                    }

                    if (colorCssWriter != null) {
                        colorCssWriter.flush();
                        colorCssWriter.close();
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
