package com.example.chess.controller;

import com.example.chess.model.*;

public class HelloController {
    private Piece[][] board;

    public void initialize() {
        board[0][0] = new Rook(0, 0, Piece.BLACK);
        board[1][0] = new Knight(1, 0, Piece.BLACK);
        board[2][0] = new Bishop(2, 0, Piece.BLACK);
        board[3][0] = new Queen(3, 0, Piece.BLACK);
        board[4][0] = new King(4, 0, Piece.BLACK);
        board[5][0] = new Bishop(5, 0, Piece.BLACK);
        board[6][0] = new Knight(6, 0, Piece.BLACK);
        board[7][0] = new Rook(7, 0, Piece.BLACK);
        for (int i = 0; i < Piece.MAX_X; i++) {
            board[i][1] = new Pawn(i, 1, Piece.BLACK);
        }

        board[0][7] = new Rook(0, 7, Piece.WHITE);
        board[1][7] = new Knight(1, 7, Piece.WHITE);
        board[2][7] = new Bishop(2, 7, Piece.WHITE);
        board[3][7] = new Queen(3, 7, Piece.WHITE);
        board[4][7] = new King(4, 7, Piece.WHITE);
        board[5][7] = new Bishop(5, 7, Piece.WHITE);
        board[6][7] = new Knight(6, 7, Piece.WHITE);
        board[7][7] = new Rook(7, 7, Piece.WHITE);
        for (int i = 0; i < Piece.MAX_X; i++) {
            board[i][6] = new Pawn(i, 6, Piece.WHITE);
        }
    }
}