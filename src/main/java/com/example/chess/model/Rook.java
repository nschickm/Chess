package com.example.chess.model;

import java.util.Objects;

public class Rook extends Piece {
    public Rook(int x, int y, String color) {
        super(x, y, color);
        name = "rook";
    }

    @Override
    public Boolean[][] getPossibleMoves(Piece[][] board) {
        Boolean[][] possibleMoves = new Boolean[MAX_X][MAX_Y];
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                possibleMoves[i][j] = false;
            }
        }

        for (int i = 1; i < MAX_X - x; i++) {
            if (board[x + i][y] == null) {
                possibleMoves[x + i][y] = true;
            } else if (!Objects.equals(board[x + i][y].color, color)) {
                possibleMoves[x + i][y] = true;
                i = MAX_X - x;
            } else {
                i = MAX_X - x;
            }
        }

        for (int i = 1; i <= x; i++) {
            if (board[x - i][y] == null) {
                possibleMoves[x - i][y] = true;
            } else if (!Objects.equals(board[x - i][y].color, color)) {
                possibleMoves[x - i][y] = true;
                i = x;
            } else {
                i = x;
            }
        }

        for (int i = 1; i < MAX_Y - y; i++) {
            if (board[x][y + i] == null) {
                possibleMoves[x][y + i] = true;
            } else if (!Objects.equals(board[x][y + i].color, color)) {
                possibleMoves[x][y + i] = true;
                i = MAX_Y - y;
            } else {
                i = MAX_Y - y;
            }
        }

        for (int i = 1; i <= y; i++) {
            if (board[x][y - i] == null) {
                possibleMoves[x][y - i] = true;
            } else if (!Objects.equals(board[x][y - i].color, color)) {
                possibleMoves[x][y - i] = true;
                i = y;
            } else {
                i = y;
            }
        }

        return possibleMoves;
    }
}
