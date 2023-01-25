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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController extends AbstractController{
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
    public Label sameNameErrorMsg;
    public ImageView imgViewBoard;

    public void initialize() {
        Image image = new Image("chessboardPNG.png");
        imgViewBoard.setImage(image);



        sameNameErrorMsg.setVisible(false);
        passwordEncrypted.setPromptText("Enter Password");

        passwordEncrypted.textProperty().bindBidirectional(password.textProperty());
        StackPane sp = new StackPane(passwordEncrypted, password);
        passwordEncrypted.toFront();
        password.setVisible(false);
        checkBoxPassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                password.toFront();
                password.setVisible(true);
                passwordEncrypted.setVisible(false);
            }else {
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
     * Überp
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void loginbttnclicked(ActionEvent actionEvent) throws SQLException, IOException {

        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM t_user WHERE username = '" + this.username.getText() + "' AND password = '" + PasswordHasher.hashPassword(this.password.getText()) + "';");

        if (resultSet.next()) {
            // login -> weiterleiten
            System.out.println("Account verhanden!");

            if(checkname.equals(this.username.getText())){
                System.out.println("Fehler gleicher Name");
                countplayers--;
               // sameNameErrorMsg.setVisible(true);
            }

            if(countplayers == 1){
                SettingsController c = this.loadFxmlFile(
                        "settings.fxml",
                        "Settings",
                        ((Button) actionEvent.getSource()).getScene().getWindow(),
                        SettingsController.class
                );



            }else {
                countplayers++;
                checkname = this.username.getText();
                LoginController c = this.loadFxmlFile(
                        "login.fxml",
                        "Player 2 login",
                        ((JFXButton) actionEvent.getSource()).getScene().getWindow(),
                        LoginController.class
                );


                c.closeWindow();
            }
        } else {
            // kein Account
            System.out.println("Kein Account, sie muessen sich vorher Registrieren um zu Spielen!");
        }
    }

    /**
     * The values entered will be added to
     * @param actionEvent
     * @throws SQLException
     */
    public void singUpbttnclicked(ActionEvent actionEvent) throws SQLException {
        Connection connection = Database.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO t_user (username, password) VALUES ('" + this.username.getText() + "','" + PasswordHasher.hashPassword(this.password.getText()) + "');");


            username.clear();
            password.clear();

        } catch (SQLException e) {
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




}
