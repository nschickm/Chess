package com.example.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(int x, int y, String color) {
        super(x, y, color);
        name = "pawn";
    }

    @Override
    public boolean checkMove(Piece[][] board, int targetX, int targetY) {
        List<String> moves = new ArrayList<String>();

        boolean r = false;
/*
        // Check one step forward
        if (row > 0) {
            moves.add(row - 1 + " " + col);
        }

        // Check two steps forward (only possible on the pawn's first move)
        if (row == 6) {
            moves.add(row - 2 + " " + col);
        }

        // Check capture on the left
        if (row > 0 && col > 0) {
            moves.add(row - 1 + " " + col - 1);
        }

        // Check capture on the right
        if (row > 0 && col < 7) {
            moves.add(row - 1 + " " + col + 1);
        }
*/
        return r;
    }
}
