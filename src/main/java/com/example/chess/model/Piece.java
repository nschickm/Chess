package com.example.chess.model;

/**
 * Abstract class representing a chess piece.
 *
 * It has methods to return an array of booleans indicating the possible moves a piece can make on a chess board,
 * to move the piece to a new position on the board,
 *
 * @author decker
 */
public abstract class Piece {
    public static String WHITE = "white";
    public static String BLACK = "black";
    public static int MAX_X = 8;
    public static int MAX_Y = 8;

    protected String name;
    protected int x;
    protected int y;
    protected String color;


    /**
     * Constructor that takes the x, y, and color of the piece and initializes the corresponding fields.
     *
     * @param x     The x coordinate of the piece.
     * @param y     The y coordinate of the piece.
     * @param color The color of the piece.
     */
    public Piece(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }


    /**
     * Abstract method that returns an array of booleans indicating the possible moves a piece can make on a chess board.
     *
     * @param board The 2D array representing the chess board.
     * @return An array of booleans indicating the possible moves a piece can make on a chess board.
     */
    public Boolean[][] getPossibleMoves(Piece[][] board) {
        return null;
    }

    /**
     * Method to move the piece to a new position on the chess board.
     *
     * @param x The new x coordinate for the piece.
     * @param y The new y coordinate for the piece.
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to get the name of the piece.
     *
     * @return The name of the piece.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the color of the piece.
     *
     * @return The color of the piece.
     */
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
