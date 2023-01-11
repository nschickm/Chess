package com.example.chess.model;

import java.sql.ResultSet;

public class Player {
    private String name;
    private String password;
    private int time;
    private String color;

    // hashes Password
    public Player(String name, String password) {
        this.name = name;
        this.password = PasswordHasher.hashPassword(password);
    }

    public Player(ResultSet results) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTime() { return time; }

    public void setTime(int time) { this.time = time; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }
}
