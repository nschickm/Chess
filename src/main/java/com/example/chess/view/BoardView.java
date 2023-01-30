package com.example.chess.view;

import com.example.chess.model.Piece;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BoardView {
    private GridPane gridPane;
    private ArrayList<ImageView> images = new ArrayList<>();
    Piece[][] board;

    public BoardView(GridPane gridPane, Piece[][] board) {
        this.gridPane = gridPane;
        this.board = board;
    }

    public void drawBoard() {

        for (int i = 0; i < Piece.MAX_X; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (board[i][j] != null) {
                    if (Objects.equals(board[i][j].getName(), "pawn")) {
                        if (Objects.equals(board[i][j].getColor(), "black")) {
                            images.add(new ImageView("Pawn_Black.png"));
                        } else {
                            images.add(new ImageView("Pawn_White.png"));
                        }
                    } else if (Objects.equals(board[i][j].getName(), "bishop")) {
                        if (Objects.equals(board[i][j].getColor(), "black")) {
                            images.add(new ImageView("Bishop_Black.png"));
                        } else {
                            images.add(new ImageView("Bishop_White.png"));
                        }
                    } else if (Objects.equals(board[i][j].getName(), "rook")) {
                        if (Objects.equals(board[i][j].getColor(), "black")) {
                            images.add(new ImageView("Rook_Black.png"));
                        } else {
                            images.add(new ImageView("Rook_White.png"));
                        }
                    } else if (Objects.equals(board[i][j].getName(), "queen")) {
                        if (Objects.equals(board[i][j].getColor(), "black")) {
                            images.add(new ImageView("Queen_Black.png"));
                        } else {
                            images.add(new ImageView("Queen_White.png"));
                        }
                    } else if (Objects.equals(board[i][j].getName(), "king")) {
                        if (Objects.equals(board[i][j].getColor(), "black")) {
                            images.add(new ImageView("King_Black.png"));
                        } else {
                            images.add(new ImageView("King_White.png"));
                        }
                    } else if (Objects.equals(board[i][j].getName(), "knight")) {
                        if (Objects.equals(board[i][j].getColor(), "black")) {
                            images.add(new ImageView("Knight_Black.png"));
                        } else {
                            images.add(new ImageView("Knight_White.png"));
                        }
                    }

                    images.get(images.size() - 1).setFitHeight(87.5);
                    images.get(images.size() - 1).setFitWidth(87.5);

                    gridPane.add(images.get(images.size() - 1), i, Piece.MAX_Y - j - 1);
                }

            }
        }
    }

    @Override
    public String toString() {
        return "BoardView{" +
                "gridPane=" + gridPane +
                ", board=" + Arrays.toString(board) +
                '}';
    }

    public void drawPossibleMoves(Boolean[][] possibleMoves) {
        for (int i = 0; i < Piece.MAX_X; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (possibleMoves[i][j]) {
                    images.add(new ImageView("selected.png"));

                    images.get(images.size() - 1).setFitHeight(87.5);
                    images.get(images.size() - 1).setFitWidth(87.5);
                    images.get(images.size() - 1).setOpacity(0.2);

                    gridPane.add(images.get(images.size() - 1), i, Piece.MAX_Y - j - 1);
                }
            }
        }
    }
}
