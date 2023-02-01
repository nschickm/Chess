package com.example.chess.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database is a singleton class that handles the database connection.
 *
 * @author
 */
public class Database {

    private String host = "localhost";
    private String user = "4ahitn";
    private String db = "4ahitn";
    private String password = "4ahitn";
    private String driverType = "mysql";
    private int port = 4306;

    private static Connection connection = null;

    /**
     * This static block loads the database driver when the class is loaded.
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Private constructor for the Database class.
     * Creates a new connection to the database.
     */
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

    /**
     * Returns the connection object for the database.
     * Creates a new connection to the database if there isn't one yet.
     *
     * @return connection to the database
     */
    public static Connection getConnection() {
        if (connection == null) {
            new Database();
        }
        return connection;
    }

}