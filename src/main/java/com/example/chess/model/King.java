package com.example.chess.model;

public class King extends Piece{
    public King(int x, int y, String color) {
        super(x, y, color);
        name = "king";
    }

    @Override
    public boolean checkMove(Piece[][] board, int targetX, int targetY) {
        boolean r = false;

        return r;
    }
}
