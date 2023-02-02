package com.example.chess.model;

import java.util.Objects;

/**
 * The King class extends the Piece class and represents a king piece in a chess game.
 *
 * @author  Example User
 */
public class King extends Piece {


    /**
     * Constructs a king with the given x, y coordinates and color.
     *
     * @param x the x-coordinate of the king
     * @param y the y-coordinate of the king
     * @param color the color of the king (either "white" or "black")
     */
    public King(int x, int y, String color) {
        super(x, y, color);
        name = "king";
    }

    /**
     * Returns a 2D Boolean array representing the possible moves of the king on the given chess board.
     *
     * @param board the current state of the chess board
     * @return a 2D Boolean array representing the possible moves of the king
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


        /*
        int tempCount = 0;
        for (int i = 0; i < Piece.MAX_X; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (!Objects.equals(board[i][j].getColor(), color) && !Objects.equals(board[i][j].getName(), "king")) {
                    Boolean[][] PiecePossibleMoves = board[i][j].getPossibleMoves(board);
                    for (int k = 0; k < Piece.MAX_X; k++) {
                        for (int l = 0; l < Piece.MAX_Y; l++) {
                            if (PiecePossibleMoves[k][l]) {
                                tempCount++;
                                possibleMoves[i][j] = false;
                            }
                        }
                    }
                }
            }
        }

         */








        return possibleMoves;
    }
}
