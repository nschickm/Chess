package com.example.chess.model;

import java.util.Objects;

/**
 * The Queen class extends the Piece class and represents a queen piece in a chess game.
 *
 * @author  Example User
 */
public class Queen extends Piece {

    /**
     * Constructs a queen with the given x, y coordinates and color.
     *
     * @param x the x-coordinate of the queen
     * @param y the y-coordinate of the queen
     * @param color the color of the queen (either "white" or "black")
     */
    public Queen(int x, int y, String color) {
        super(x, y, color);
        name = "queen";
    }

    /**
     * Returns a 2D Boolean array representing the possible moves of the queen on the given chess board.
     *
     * @param board the current state of the chess board
     * @return a 2D Boolean array representing the possible moves of the queen
     */
    @Override
    public Boolean[][] getPossibleMoves(Piece[][] board) {
        Boolean[][] possibleMoves = new Boolean[MAX_X][MAX_Y];
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                possibleMoves[i][j] = false;
            }
        }

        Boolean[][] possibleMovesBishop = new Bishop(x, y, color).getPossibleMoves(board);
        Boolean[][] possibleMovesRook = new Rook(x, y, color).getPossibleMoves(board);

        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                if (possibleMovesBishop[i][j] || possibleMovesRook[i][j]) {
                    possibleMoves[i][j] = true;
                }
            }
        }

        return possibleMoves;
    }
}
