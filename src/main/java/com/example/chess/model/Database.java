package com.example.chess.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String host = "localhost";
    private String user = "4ahitn";
    private String db = "4ahitn";
    private String password = "4ahitn";

    private String driverType = "mysql";
    private int port = 4306;

    private static Connection connection = null;

    // static-block wird beim Laden der Klasse aufgerufen
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Database() {
        try {
            Connection c = DriverManager.getConnection(
                    //Connetion String
                    "jdbc:" + driverType + "://" + host + ":" + port + "/" + db + "?useSSL=false",
                    user,
                    password
            );

            connection = c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {
        if (connection == null) {
            new Database();
        }
        return connection;
    }

}