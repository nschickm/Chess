package com.example.chess.model;

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

        for (int i = 0; i < MAX_X; i++) {
            if (board[x + i][y] == null){
                possibleMoves[x+i][y] = true;
            }
        }

        for (int i = 0; i < MAX_X; i++) {
            if (board[x - i][y] == null){
                possibleMoves[x-i][y] = true;
            }
        }

        for (int i = 0; i < MAX_Y; i++) {
            if (board[x][y + i] == null){
                possibleMoves[x][y + i] = true;
            }
        }

        for (int i = 0; i < MAX_X; i++) {
            if (board[x][y - i] == null){
                possibleMoves[x][y - i] = true;
            }
        }

        return possibleMoves;
    }
}
