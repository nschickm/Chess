package com.example.chess.model;

import java.util.Objects;

public class Queen extends Piece {
    public Queen(int x, int y, String color) {
        super(x, y, color);
        name = "queen";
    }

    /**
     * Checks all the possible moves for Queen piece
     *
     * @return board [][] with TRUE in every field possible and FALSE if not
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
