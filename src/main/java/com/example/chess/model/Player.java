package com.example.chess.model;

import com.example.chess.controller.AbstractController;
import javafx.scene.paint.Color;

import java.sql.ResultSet;

public class Player extends AbstractController {
    private String name;
    private String color;
    private double timeInMillis;


    public Player(String name, String color, double timeInMillis) {
        this.name = name;
        this.color = color;
        this.timeInMillis = timeInMillis;
    }


    public Player(String name) {
        this.name = name;
    }

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
