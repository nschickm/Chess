package com.example.chess.model;

import java.util.Objects;

public class Bishop extends Piece {
    public Bishop(int x, int y, String color) {
        super(x, y, color);
        name = "bishop";
    }

    @Override
    public Boolean[][] getPossibleMoves(Piece[][] board) {
        Boolean[][] possibleMoves = new Boolean[MAX_X][MAX_Y];
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                possibleMoves[i][j] = false;
            }
        }

        for (int i = 1; i < MAX_Y - y && i < MAX_X - x; i++) {
            if (board[x + i][y + i] == null) {
                possibleMoves[x + i][y + i] = true;
            } else if (!Objects.equals(board[x + i][y + i].color, color)) {
                possibleMoves[x + i][y + i] = true;
                i = MAX_Y;
            } else {
                i = MAX_Y;
            }
        }

        for (int i = 1; i < MAX_Y - y && i <= x; i++) {
            if (board[x - i][y + i] == null) {
                possibleMoves[x - i][y + i] = true;
            } else if (!Objects.equals(board[x - i][y + i].color, color)) {
                possibleMoves[x - i][y + i] = true;
                i = MAX_Y;
            } else {
                i = MAX_Y;
            }
        }

        for (int i = 1; i < MAX_X - x && i <= y; i++) {
            if (board[x + i][y - i] == null) {
                possibleMoves[x + i][y - i] = true;
            } else if (!Objects.equals(board[x + i][y - i].color, color)) {
                possibleMoves[x + i][y - i] = true;
                i = MAX_X;
            } else {
                i = MAX_X;
            }
        }

        for (int i = 1; i <= x && i <= y; i++) {
            if (board[x - i][y - i] == null) {
                possibleMoves[x - i][y - i] = true;
            } else if (!Objects.equals(board[x - i][y - i].color, color)) {
                possibleMoves[x - i][y - i] = true;
                i = MAX_X;
            } else {
                i = MAX_X;
            }
        }

        return possibleMoves;
    }
}
