package com.example.chess.model;

public class Queen extends Piece{
    public Queen(int x, int y, String color) {
        super(x, y, color);
        name = "queen";
    }

    @Override
    public boolean checkMove(Piece[][] board, int targetX, int targetY) {
        boolean r = false;

        return r;
    }
}
