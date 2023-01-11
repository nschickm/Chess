package com.example.chess.controller;

import com.example.chess.model.Database;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class LoginController {
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

    public void initialize() {
        sameNameErrorMsg.setVisible(false);

        passwordEncrypted.textProperty().bindBidirectional(password.textProperty());
        StackPane sp = new StackPane(passwordEncrypted, password);
        passwordEncrypted.toFront();
        checkBoxPassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                password.toFront();
            }else {
                passwordEncrypted.toFront();
            }
        });
        sp.setLayoutX(181.0);
        sp.setLayoutY(144.0);
        anchorPaneTop.getChildren().add(sp);

    }

    public void loginbttnclicked(ActionEvent actionEvent) throws SQLException, IOException {

        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM t_user WHERE username = '" + this.username.getText() + "' AND password = '" + this.password.getText() + "';");

        if (resultSet.next()) {
            // login -> weiterleiten
            System.out.println("Account verhanden!");

            if(checkname.equals(this.username.getText())){
                System.out.println("Fehler gleicher Name");
                countplayers--;
               // sameNameErrorMsg.setVisible(true);
            }

            if(countplayers == 1){
                changeSceneSetting();

            }else {
                countplayers++;
                checkname = this.username.getText();
                changeSceneLogin();
            }
        } else {
            // kein Account
            System.out.println("Kein Account, sie muessen sich vorher Registrieren um zu Spielen!");
        }
    }

    public void singUpbttnclicked(ActionEvent actionEvent) throws SQLException {
        Connection connection = Database.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO t_user (username, password) VALUES ('" + this.username.getText() + "','" + this.password.getText() + "');");


            username.clear();
            password.clear();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    /**
     * Schließt das aktuelle fxml und leitet zum Spielfeld ("settings.fxml") weiter.
     * @throws IOException
     */
    public void changeSceneSetting() throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) loginbttn.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = HelloApplication.class.getResource("settings.fxml");

        assert u != null;
        Scene scene = new Scene(fxmlLoader.load(u.openStream()));
        //PlayfieldController pc = fxmlLoader.getController();
        SettingsController settingsController = fxmlLoader.getController();
        //pc.afterSwitch(scene, "normal","0", nameTextField.getText());

        stage.setTitle("Chess");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Schließt das aktuelle fxml und leitet zum Spielfeld ("login.fxml") weiter.
     * @throws IOException
     */
    public void changeSceneLogin() throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) loginbttn.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = HelloApplication.class.getResource("login.fxml");

        assert u != null;
        Scene scene = new Scene(fxmlLoader.load(u.openStream()));
        //PlayfieldController pc = fxmlLoader.getController();
        LoginController loginController = fxmlLoader.getController();
        //pc.afterSwitch(scene, "normal","0", nameTextField.getText());

        stage.setTitle("Chess");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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
