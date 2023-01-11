module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.chess.controller to javafx.fxml;
    exports com.example.chess.controller;
}