package com.example.chess.view;

import com.example.chess.model.Database;
import com.example.chess.model.Piece;
import javafx.scene.Node;
import com.example.chess.model.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.example.chess.controller.LoginController.player1name;
import static com.example.chess.controller.LoginController.player2name;

/**
 * BoardView class represents the view component of the chess game
 * It is responsible for visualizing the chessboard and pieces on the chessboard.
 *
 * @author
 */
public class BoardView {
    private GridPane gridPane;
    private ArrayList<ImageView> images = new ArrayList<>();
    Piece[][] board;

    /**
     * Constructor to initialize the gridPane and board instance variables.
     *
     * @param gridPane GridPane instance to represent the chessboard.
     * @param board A 2-Dimensional array of Piece objects to represent the chessboard.
     */
    public BoardView(GridPane gridPane, Piece[][] board) {
        this.gridPane = gridPane;
        this.board = board;
    }

    /**
     * The method to draw the chessboard and pieces on it.
     * It removes the existing pieces from the gridPane,
     * then iterates through the board array and adds the ImageView of the pieces to the gridPane.
     */
    public void drawBoard() {
        List<Node> toRemove = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                toRemove.add(node);
            }
        }
        gridPane.getChildren().removeAll(toRemove);

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

    /**
     * The method to draw the possible moves on the chessboard.
     *
     * @param possibleMoves A 2-Dimensional boolean array to represent the possible moves.
     */
    public void drawPossibleMoves(Boolean[][] possibleMoves) {
        for (int i = 0; i < Piece.MAX_X; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (possibleMoves[i][j]) {
                    images.add(new ImageView("selected.png"));

                    images.get(images.size() - 1).setFitHeight(87.5);
                    images.get(images.size() - 1).setFitWidth(87.5);
                    images.get(images.size() - 1).setOpacity(0.1);

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


}
