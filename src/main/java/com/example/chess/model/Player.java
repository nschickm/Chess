package com.example.chess.model;

import com.example.chess.controller.AbstractController;
import javafx.scene.paint.Color;

import java.sql.ResultSet;

/**
 * The class representing a chess player.
 *
 * @author
 */
public class Player extends AbstractController {
    private String name;
    private String color;
    private double timeInMillis;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
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
}
