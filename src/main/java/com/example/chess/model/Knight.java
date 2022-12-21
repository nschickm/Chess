package com.example.chess.model;

public class Knight extends Piece{
    public Knight(int x, int y, String color) {
        super(x, y, color);
        name = "knight";
    }

    @Override
    public boolean checkMove(Piece[][] board, int targetX, int targetY) {
        boolean r = false;

        return r;
    }
}
