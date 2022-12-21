package com.example.chess.model;

public class Bishop extends Piece{
    public Bishop(int x, int y, String color) {
        super(x, y, color);
        name = "bishop";
    }

    @Override
    public boolean checkMove(Piece[][] board, int targetX, int targetY) {
        boolean r = false;

        return r;
    }
}
