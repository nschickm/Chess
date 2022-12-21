package com.example.chess.model;

import java.util.Objects;

public class Pawn extends Piece {
    public Pawn(int x, int y, String color) {
        super(x, y, color);
        name = "pawn";
    }

    @Override
    public Boolean[][] getPossibleMoves(Piece[][] board) {
        Boolean[][] possibleMoves = new Boolean[MAX_X][MAX_Y];
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                possibleMoves[i][j] = false;
            }
        }

        if (Objects.equals(color, WHITE)) {
            if (board[x][y++] == null) {
                possibleMoves[x][y++] = true;
            }

            if (board[x][y++] == null && board[x][y + 2] == null) {
                possibleMoves[x][y + 2] = true;
            }

            if (board[x++][y++] != null) {
                if (Objects.equals(board[x++][y++].color, BLACK)) {
                    possibleMoves[x++][y++] = true;
                }
            }

            if (board[x--][y++] != null) {
                if (Objects.equals(board[x--][y++].color, BLACK)) {
                    possibleMoves[x++][y++] = true;
                }
            }
        }

        if (Objects.equals(color, BLACK)) {
            if (board[x][y--] == null) {
                possibleMoves[x][y--] = true;
            }

            if (board[x][y--] == null && board[x][y - 2] == null) {
                possibleMoves[x][y - 2] = true;
            }

            if (board[x++][y--] != null) {
                if (Objects.equals(board[x++][y--].color, WHITE)) {
                    possibleMoves[x++][y--] = true;
                }
            }

            if (board[x--][y--] != null) {
                if (Objects.equals(board[x--][y--].color, WHITE)) {
                    possibleMoves[x++][y++] = true;
                }
            }
        }

        return possibleMoves;
    }
}
