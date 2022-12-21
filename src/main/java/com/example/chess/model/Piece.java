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

    public boolean checkMove(Piece[][] board, int targetX, int targetY) {
        return false;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
