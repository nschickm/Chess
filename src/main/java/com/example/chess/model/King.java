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
     *  Checks all the possible moves for King piece
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


        if (board[x++][y] == null) {
            possibleMoves[x++][y] = true;
        } else if (!Objects.equals(board[x++][y].color, color)) {
            possibleMoves[x++][y] = true;
        }

        if (board[x--][y] == null) {
            possibleMoves[x--][y] = true;
        } else if (!Objects.equals(board[x--][y].color, color)) {
            possibleMoves[x--][y] = true;
        }


        if (board[x++][y++] == null) {
            possibleMoves[x++][y++] = true;
        } else if (!Objects.equals(board[x++][y++].color, color)) {
            possibleMoves[x++][y++] = true;
        }

        if (board[x--][y++] == null) {
            possibleMoves[x--][y++] = true;
        } else if (!Objects.equals(board[x--][y++].color, color)) {
            possibleMoves[x--][y++] = true;
        }

        if (board[x][y++] == null) {
            possibleMoves[x][y++] = true;
        } else if (!Objects.equals(board[x][y++].color, color)) {
            possibleMoves[x][y++] = true;
        }


        if (board[x++][y--] == null) {
            possibleMoves[x++][y--] = true;
        } else if (!Objects.equals(board[x++][y--].color, color)) {
            possibleMoves[x++][y--] = true;
        }

        if (board[x--][y--] == null) {
            possibleMoves[x--][y--] = true;
        } else if (!Objects.equals(board[x--][y--].color, color)) {
            possibleMoves[x--][y--] = true;
        }

        if (board[x][y--] == null) {
            possibleMoves[x][y--] = true;
        } else if (!Objects.equals(board[x][y--].color, color)) {
            possibleMoves[x][y--] = true;
        }


        return possibleMoves;
    }
}
