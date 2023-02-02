package com.example.chess.model;

import java.util.Objects;

/**
 * The Bishop class extends the Piece class and represents a bishop piece in a chess game.
 *
 * @author  Example User
 */
public class Bishop extends Piece {

    /**
     * Constructs a bishop with the given x, y coordinates and color.
     *
     * @param x the x-coordinate of the bishop
     * @param y the y-coordinate of the bishop
     * @param color the color of the bishop (either "white" or "black")
     */
    public Bishop(int x, int y, String color) {
        super(x, y, color);
        name = "bishop";
    }

    /**
     * Returns a 2D Boolean array representing the possible moves of the bishop on the given chess board.
     *
     * @param board the current state of the chess board
     * @return a 2D Boolean array representing the possible moves of the bishop
     */
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
