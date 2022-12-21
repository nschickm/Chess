module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chess.controller to javafx.fxml;
    exports com.example.chess.controller;
}