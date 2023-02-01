package com.example.chess.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.chess.controller.LoginController.player1name;
import static com.example.chess.controller.LoginController.player2name;

/**
 * This class is responsible for handling the database operations for the chess game.
 * It provides methods for updating the wins, losses and draws of the players in the database.
 *
 * @author
 */
public class DatabaseHandler {

    /**
     * Retrieves the win, loss and draw record of a given player.
     *
     * @param player the player for which the data is to be retrieved
     * @return a string representing the win, loss and draw record of the player
     * @throws SQLException if there is any error while querying the database
     */
    public String getPlayerData(Player player) throws SQLException {
        String player1 = "";
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT wins AS wins, losses AS losses, draws AS draws FROM t_user WHERE username = '" + player.getName() + "';");

            if (resultSet.next()) {
                player1 = "Wins: " + resultSet.getString("wins") + " / Lose: " + resultSet.getString("losses") + " / Draws: " + resultSet.getString("draws");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player1;
    }

    /**
     * Updates the draw record of both players in the database.
     */
    public void setDraw(){
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            statement.executeUpdate("UPDATE t_user SET draws = IFNULL(draws, 0) + 1 WHERE username ='" + player1name  + "';");
            statement1.executeUpdate("UPDATE t_user SET draws = IFNULL(draws, 0) + 1 WHERE username ='" + player2name  + "';");

            statement1.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the win record of a given player in the database.
     *
     * @param player the player for which the win record is to be updated
     */
    public void setWin(String player){
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE t_user SET wins = IFNULL(wins, 0) + 1 WHERE username ='" + player  + "';");


            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the loss record of a given player in the database.
     *
     * @param player the player for which the loss record is to be updated
     */
    public void setLose(String player){
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE t_user SET losses = IFNULL(losses, 0) + 1 WHERE username ='" + player  + "';");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
