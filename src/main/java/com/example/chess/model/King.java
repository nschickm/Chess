package com.example.chess.model;

import java.util.Objects;

/**
 *
 */
public class King extends Piece {
    public King(int x, int y, String color) {
        super(x, y, color);
        name = "king";
    }

    /**
     * Checks all the possible moves for King piece
     *
     * @param board
     * @return
     */
    @Override
    public Boolean[][] getPossibleMoves(Piece[][] board) {
        Boolean[][] possibleMoves = new Boolean[MAX_X][MAX_Y];
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                possibleMoves[i][j] = false;
            }
        }

        if (x + 1 < MAX_X) {
            if (board[x + 1][y] == null) {
                possibleMoves[x + 1][y] = true;
            } else if (!Objects.equals(board[x + 1][y].color, color)) {
                possibleMoves[x + 1][y] = true;
            }
        }

        if (x - 1 >= 0) {
            if (board[x - 1][y] == null) {
                possibleMoves[x - 1][y] = true;
            } else if (!Objects.equals(board[x - 1][y].color, color)) {
                possibleMoves[x - 1][y] = true;
            }
        }

        if (x + 1 < MAX_X && y + 1 < MAX_Y) {
            if (board[x + 1][y + 1] == null) {
                possibleMoves[x + 1][y + 1] = true;
            } else if (!Objects.equals(board[x + 1][y + 1].color, color)) {
                possibleMoves[x + 1][y + 1] = true;
            }
        }

        if (x - 1 >= 0 && y + 1 < MAX_Y) {
            if (board[x - 1][y + 1] == null) {
                possibleMoves[x - 1][y + 1] = true;
            } else if (!Objects.equals(board[x - 1][y + 1].color, color)) {
                possibleMoves[x - 1][y + 1] = true;
            }
        }

        if (y + 1 < MAX_Y) {
            if (board[x][y + 1] == null) {
                possibleMoves[x][y + 1] = true;
            } else if (!Objects.equals(board[x][y + 1].color, color)) {
                possibleMoves[x][y + 1] = true;
            }
        }

        if (x + 1 < MAX_X && y - 1 >= 0) {
            if (board[x + 1][y - 1] == null) {
                possibleMoves[x + 1][y - 1] = true;
            } else if (!Objects.equals(board[x + 1][y - 1].color, color)) {
                possibleMoves[x + 1][y - 1] = true;
            }
        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            if (board[x - 1][y - 1] == null) {
                possibleMoves[x - 1][y - 1] = true;
            } else if (!Objects.equals(board[x - 1][y - 1].color, color)) {
                possibleMoves[x - 1][y - 1] = true;
            }
        }

        if (y - 1 >= 0) {
            if (board[x][y - 1] == null) {
                possibleMoves[x][y - 1] = true;
            } else if (!Objects.equals(board[x][y - 1].color, color)) {
                possibleMoves[x][y - 1] = true;
            }
        }

        return possibleMoves;
    }
}
