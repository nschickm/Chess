package com.example.chess.controller;

import com.example.chess.model.Database;
import com.example.chess.model.PasswordHasher;
import com.example.chess.model.Piece;
import com.example.chess.model.Player;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

/**
 * LoginController class is the controller class for handling the logic for the Login page.
 * It contains the logic for checking the entered credentials and handles the navigation to other pages based on the result.
 *
 * @author nschickm, meder1
 */
public class LoginController extends AbstractController {
    public TextField username;
    public TextField password;
    public Label nameLabel;
    public Label passwordLabel;
    public Button loginbttn;
    public Label welcometext;
    public CheckBox checkBoxPassword;
    public Button signUpSelect;
    public PasswordField passwordEncrypted;
    public AnchorPane anchorPaneTop;
    private static int countplayers = 0;
    private static String checkname = "";
    public ImageView imgViewBoard;
    public static String player1name;
    public static String player2name;


    /**
     * The initialize method is called automatically when the Login page is loaded.
     * It sets the prompt text for the passwordEncrypted field, sets the visibility of the password field,
     * and adds a listener for the checkBoxPassword CheckBox to switch between encrypted and plain text password display.
     */
    public void initialize() {

        Image image = new Image("chessboardPNG.png");
        imgViewBoard.setImage(image);

        passwordEncrypted.setPromptText("Enter Password");

        passwordEncrypted.textProperty().bindBidirectional(password.textProperty());
        StackPane sp = new StackPane(passwordEncrypted, password);
        passwordEncrypted.toFront();
        password.setVisible(false);
        checkBoxPassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                password.toFront();
                password.setVisible(true);
                passwordEncrypted.setVisible(false);
            } else {
                passwordEncrypted.toFront();
                password.setVisible(false);
                passwordEncrypted.setVisible(true);
            }
        });

        sp.setLayoutX(72.0);
        sp.setLayoutY(109.0);
        anchorPaneTop.getChildren().add(sp);

    }

    /**
     * The loginbttnclicked method is called when the login button is clicked.
     * It performs a check for an existing account in the database with the entered username and password.
     * If the account exists, it sets the player name and loads the next screen based on the number of players.
     * If the account does not exist, an error message is displayed.
     *
     * @param actionEvent the action event that triggers this method
     * @throws SQLException if a database error occurs
     * @throws IOException if an I/O error occurs when loading the next screen
     */
    public void loginbttnclicked(ActionEvent actionEvent) throws SQLException, IOException {

        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();

        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM t_user WHERE username = '" + this.username.getText() + "' AND password = '" + PasswordHasher.hashPassword(this.password.getText()) + "';");

        if (resultSet.next()) {


            if (checkname.equals(this.username.getText())) {
                System.out.println("Fehler gleicher Name");
                countplayers--;
            }

            if (countplayers == 1) {
                player2name = username.getText();

                this.loadFxmlFile(
                        "settings.fxml",
                        "Settings",
                        ((Button) actionEvent.getSource()).getScene().getWindow(),
                        SettingsController.class
                );


            } else {
                player1name = username.getText();
                countplayers++;
                checkname = this.username.getText();

                this.loadFxmlFile(
                        "testlogindesign.fxml",
                        "Player 2 login",
                        null,
                        LoginController.class
                );


            }
        } else {
            // no account, please create one
        }
    }

    /**
     * The singUpbttnclicked method is called when the sign up button is clicked.
     * It creates a new account in the database with the entered username and password.
     * If the account creation is successful, it calls the loginbttnclicked method to log in to the account.
     *
     * @param actionEvent the action event that triggers this method
     * @throws SQLException if a database error occurs
     */
    public void singUpbttnclicked(ActionEvent actionEvent) throws SQLException {
        Connection connection = Database.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO t_user (username, password) VALUES ('" + this.username.getText() + "','" + PasswordHasher.hashPassword(this.password.getText()) + "');");


            loginbttnclicked(actionEvent);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }


    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }


    public void closeLogin(ActionEvent actionEvent) {
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }
}
