package com.example.chess.model;

import com.example.chess.controller.AbstractController;
import javafx.scene.paint.Color;

import java.sql.*;
import java.util.ArrayList;

/**
 * The class representing a chess player.
 *
 * @author mani
 */
public class Player extends AbstractController {
    private String name;
    private String color;
    private double timeInMillis;
    private int wins;
    private int losses;
    private int draws;


    /**
     * Constructor to initialize a player with name, color and time in milliseconds.
     *
     * @param name the name of the player
     * @param color the color of the player
     * @param timeInMillis the time in milliseconds for the player
     */
    public Player(String name, String color, double timeInMillis) {
        this.name = name;
        this.color = color;
        this.timeInMillis = timeInMillis;
    }

    /**
     * Constructor to initialize a player with only name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Default constructor to initialize an empty player object.
     */
    public Player() {

    }

    /**
     * Constructs a Player object from a ResultSet.
     *
     * @param resultSet The ResultSet that contains the player data.
     * @throws SQLException If an error occurs while reading from the ResultSet.
     */
    public Player(ResultSet resultSet) throws SQLException {
      this.name = resultSet.getString("username");
      this.wins = resultSet.getInt("wins");
      this.losses = resultSet.getInt("losses");
      this.draws = resultSet.getInt("draws");
    }


    /**
     * Returns a list of all players and their scores.
     *
     * @return An ArrayList of all players and their scores.
     */
    public static ArrayList<Player> getScore(){
        ArrayList<Player> playerList = new ArrayList<>();
        Connection connection = Database.getConnection();
        try {

            PreparedStatement statement =
                    connection.prepareStatement("SELECT username, wins, losses, draws FROM t_user;");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                playerList.add(new Player(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playerList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getOppositeColor(){
        if (this.color == Piece.WHITE){
            return Piece.BLACK;
        } else {
            return Piece.WHITE;
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(double timeInMillis) {
        this.timeInMillis = timeInMillis;
    }


    @Override
    public String toString() {
        return name + ": Wins: " + wins + " Losses: " + losses + " Draws: " + draws;
    }
}
