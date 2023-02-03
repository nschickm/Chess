package com.example.chess.controller;

import com.example.chess.model.Player;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * The controller class for the Scoreboard.
 *
 * @author nschickm
 */
public class ScoreboardController extends AbstractController {
    public ListView scoreListView;
    public JFXButton buttonclose;

    public void initialize(){
        scoreListView.getItems().addAll(Player.getScore());
    }

    /**
     * Closes the score window when the close button is clicked.
     *
     * @param actionEvent The action event that triggered this method.
     */
    public void closeScore(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonclose.getScene().getWindow();
        stage.close();
    }
}
