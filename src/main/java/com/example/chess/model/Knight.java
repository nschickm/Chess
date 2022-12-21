package com.example.chess.model;

import java.util.Objects;

public class Knight extends Piece {
    public Knight(int x, int y, String color) {
        super(x, y, color);
        name = "knight";
    }

    @Override
    public Boolean[][] getPossibleMoves(Piece[][] board) {
        Boolean[][] possibleMoves = new Boolean[MAX_X][MAX_Y];
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                possibleMoves[i][j] = false;
            }
        }

        if (y + 2 < MAX_Y) {
            if (x-- >= 0) {
                if (board[x--][y + 2] == null) {
                    possibleMoves[x--][y + 2] = true;
                } else if (!Objects.equals(board[x--][y + 2].color, color)) {
                    possibleMoves[x--][y + 2] = true;
                }
            }

            if (x++ < MAX_X) {
                if (board[x++][y + 2] == null) {
                    possibleMoves[x++][y + 2] = true;
                } else if (!Objects.equals(board[x++][y + 2].color, color)) {
                    possibleMoves[x++][y + 2] = true;
                }
            }
        }

        if (x + 2 < MAX_X) {
            if (y-- >= 0) {
                if (board[x + 2][y--] == null) {
                    possibleMoves[x + 2][y--] = true;
                } else if (!Objects.equals(board[x + 2][y--].color, color)) {
                    possibleMoves[x + 2][y--] = true;
                }
            }

            if (y++ < MAX_Y) {
                if (board[x + 2][y++] == null) {
                    possibleMoves[x + 2][y++] = true;
                } else if (!Objects.equals(board[x + 2][y++].color, color)) {
                    possibleMoves[x + 2][y++] = true;
                }
            }
        }

        if (y - 2 >= 0) {
            if (x-- >= 0) {
                if (board[x--][y - 2] == null) {
                    possibleMoves[x--][y - 2] = true;
                } else if (!Objects.equals(board[x--][y - 2].color, color)) {
                    possibleMoves[x--][y - 2] = true;
                }
            }

            if (x++ < MAX_X) {
                if (board[x++][y - 2] == null) {
                    possibleMoves[x++][y - 2] = true;
                } else if (!Objects.equals(board[x++][y - 2].color, color)) {
                    possibleMoves[x++][y - 2] = true;
                }
            }
        }

        if (x - 2 >= 0) {
            if (y-- >= 0) {
                if (board[x - 2][y--] == null) {
                    possibleMoves[x - 2][y--] = true;
                } else if (!Objects.equals(board[x - 2][y--].color, color)) {
                    possibleMoves[x - 2][y--] = true;
                }
            }

            if (y++ < MAX_Y) {
                if (board[x - 2][y++] == null) {
                    possibleMoves[x - 2][y++] = true;
                } else if (!Objects.equals(board[x - 2][y++].color, color)) {
                    possibleMoves[x - 2][y++] = true;
                }
            }
        }
        return possibleMoves;
    }
}
