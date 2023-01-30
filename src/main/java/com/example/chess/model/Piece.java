package com.example.chess.model;

public abstract class Piece {
    public static String WHITE = "white";
    public static String BLACK = "black";
    public static int MAX_X = 8;
    public static int MAX_Y = 8;

    protected String name;
    protected int x;
    protected int y;
    protected String color;

    public Piece(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Boolean[][] getPossibleMoves(Piece[][] board) {
        return null;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
