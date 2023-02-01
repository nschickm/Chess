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
            if (y + 1 < MAX_Y) {
                if (board[x][y + 1] == null) {
                    possibleMoves[x][y + 1] = true;
                }

                if (y + 2 < MAX_Y) {
                    if (y == 1 && board[x][y + 1] == null && board[x][y + 2] == null) {
                        possibleMoves[x][y + 2] = true;
                    }
                }

                if (x + 1 < MAX_X) {
                    if (board[x + 1][y + 1] != null) {
                        if (Objects.equals(board[x + 1][y + 1].color, BLACK)) {
                            possibleMoves[x + 1][y + 1] = true;
                        }
                    }
                }

                if (x - 1 >= 0) {
                    if (board[x - 1][y + 1] != null) {
                        if (Objects.equals(board[x - 1][y + 1].color, BLACK)) {
                            possibleMoves[x - 1][y + 1] = true;
                        }
                    }
                }
            }
        }

        if (Objects.equals(color, BLACK)) {
            if (y - 1 >= 0) {
                if (board[x][y - 1] == null) {
                    possibleMoves[x][y - 1] = true;
                }

                if (y - 2 >= 0) {
                    if (y == 6 && board[x][y - 1] == null && board[x][y - 2] == null) {
                        possibleMoves[x][y - 2] = true;
                    }
                }

                if (x + 1 < MAX_X) {
                    if (board[x + 1][y - 1] != null) {
                        if (Objects.equals(board[x + 1][y - 1].color, WHITE)) {
                            possibleMoves[x + 1][y - 1] = true;
                        }
                    }
                }

                if (x - 1 >= 0) {
                    if (board[x - 1][y - 1] != null) {
                        if (Objects.equals(board[x - 1][y - 1].color, WHITE)) {
                            possibleMoves[x - 1][y - 1] = true;
                        }
                    }
                }
            }
        }

        return possibleMoves;
    }
}
