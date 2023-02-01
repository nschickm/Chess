module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires com.jfoenix;
    requires mysql.connector.j;


    opens com.example.chess.controller to javafx.fxml;
    exports com.example.chess.controller;
}