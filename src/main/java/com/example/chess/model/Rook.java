package com.example.chess.model;

public class Rook extends Piece {
    public Rook(int x, int y, String color) {
        super(x, y, color);
        name = "rook";
    }

    @Override
    public boolean checkMove(Piece[][] board, int targetX, int targetY) {
        boolean r = false;

        return r;
    }
}
