package com.example.chess.controller;

import com.example.chess.model.*;
import com.example.chess.view.BoardView;
import javafx.css.Stylesheet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.chess.controller.LoginController.player1name;
import static com.example.chess.controller.LoginController.player2name;

public class BoardController extends AbstractController {
    @FXML
    private GridPane gridPane = new GridPane();
    @FXML
    private Label timePlayer2;
    @FXML
    private Label ratingPlayer2;
    @FXML
    private Label namePlayer2;
    @FXML
    private Label timePlayer1;
    @FXML
    private Label ratingPlayer1;
    @FXML
    private Label namePlayer1;

    private Piece[][] board = new Piece[Piece.MAX_X][Piece.MAX_Y];
    private BoardView boardView;
    private Boolean[][] possibleMoves = new Boolean[Piece.MAX_X][Piece.MAX_Y];
    private Piece selected;
    private Player currentPlayer;
    private Boolean isChecked;
    private Player winner = null;
    private Player loser = null;
    private String drawType;
    private Player player1;
    private Player player2;

    public void gameStart() throws SQLException {
        boardView.drawBoard();

        player1 = new Player("Mani 1", Piece.WHITE, 1);
        player2 = new Player("Mani 2", Piece.BLACK, 1);
        currentPlayer = player1;

        ratingPlayer1.setText(boardView.showPlayer1Data());
        ratingPlayer2.setText(boardView.showPlayer2Data());
        namePlayer1.setText(player1name);
        namePlayer2.setText(player2name);

    }

    public void gameEnd() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game End");
        if (winner != null) {
            alert.setContentText(winner.getName() + " won the game");
            boardView.setWin(winner.getName());
            boardView.setLose(loser.getName());
        } else {
            alert.setContentText("The game ended by " + drawType);
            boardView.setDraw();
        }

        for (int i = 0; i < Piece.MAX_X; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                board[i][j] = null;
            }
        }

        Boolean isPieceSelected = false;
        Player player1 = null;
        Player player2 = null;
        Player currentPlayer = null;
        Boolean isChecked = false;
        Player winner = null;
        String drawType = "";
    }

    @FXML
    void fieldClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();

        int col = GridPane.getColumnIndex(node);
        int row = GridPane.getRowIndex(node);
        if (selected == null) {
            selected = board[col][Piece.MAX_Y - row - 1];

            if (Objects.equals(selected.getColor(), currentPlayer.getColor())) {

                possibleMoves = board[col][Piece.MAX_Y - row - 1].getPossibleMoves(board);
                boardView.drawPossibleMoves(possibleMoves);
            }
        } else {
            try {
                int kingX = -1;
                int kingY = -1;

                if (possibleMoves[col][Piece.MAX_Y - row - 1]) {

                    for (int i = 0; i < Piece.MAX_X; i++) {
                        for (int j = 0; j < Piece.MAX_Y; j++) {
                            if (board[i][j] == selected) {
                                board[i][j] = null;
                                i = Piece.MAX_X;
                                j = Piece.MAX_Y;
                            }
                        }
                    }
                        /*
                            }

                            if (board[i][j] != null) {
                                if (Objects.equals(board[i][j].getName(), "king")
                                        && Objects.equals(board[i][j].getColor(), selected.getColor())) {
                                    kingX = i;
                                    kingY = j;
                                }
                            }
                        }
                    }

                    if (kingX != -1) {
                        for (int i = 0; i < Piece.MAX_X; i++) {
                            for (int j = 0; j < Piece.MAX_Y; j++) {
                                if (!Objects.equals(board[i][j].getColor(), selected.getColor())) {
                                    if (board[i][j].getPossibleMoves(board)[kingX][kingY]) {
                                        isChecked = true;
                                        i = Piece.MAX_X;
                                        j = Piece.MAX_Y;
                                    }
                                }
                            }
                        }
                    }

                    if (!isChecked) {
                    board[selected.getX()][selected.getY()] = selected;
                    selected.move(col, Piece.MAX_Y - row - 1);
                    }
*/
                    board[col][Piece.MAX_Y - row - 1] = selected;
                    selected.move(col, Piece.MAX_Y - row - 1);

                    if (currentPlayer == player1) {
                        currentPlayer = player2;
                    } else {
                        currentPlayer = player1;
                    }
                } else {
                    System.out.println("Move not possible");
                }


            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            System.out.println(Arrays.deepToString(board).replace("]", "]\n"));
            possibleMoves = null;
            selected = null;
            boardView.drawBoard();
        }

    }

    public void drawButtonClicked(ActionEvent actionEvent) {
        drawType = "offered draw";
        gameEnd();
    }

    public void resignButtonClicked(ActionEvent actionEvent) {
        if (currentPlayer == player1) {
            winner = player2;
            loser = player1;
        } else {
            winner = player1;
            loser = player2;
        }

        gameEnd();
    }

    public void initialize() throws SQLException {

        board[0][0] = new Rook(0, 0, Piece.WHITE);
        board[1][0] = new Knight(1, 0, Piece.WHITE);
        board[2][0] = new Bishop(2, 0, Piece.WHITE);
        board[3][0] = new Queen(3, 0, Piece.WHITE);
        board[4][0] = new King(4, 0, Piece.WHITE);
        board[5][0] = new Bishop(5, 0, Piece.WHITE);
        board[6][0] = new Knight(6, 0, Piece.WHITE);
        board[7][0] = new Rook(7, 0, Piece.WHITE);
        for (int i = 0; i < Piece.MAX_X; i++) {
            board[i][1] = new Pawn(i, 1, Piece.WHITE);
        }

        board[0][7] = new Rook(0, 7, Piece.BLACK);
        board[1][7] = new Knight(1, 7, Piece.BLACK);
        board[2][7] = new Bishop(2, 7, Piece.BLACK);
        board[3][7] = new Queen(3, 7, Piece.BLACK);
        board[4][7] = new King(4, 7, Piece.BLACK);
        board[5][7] = new Bishop(5, 7, Piece.BLACK);
        board[6][7] = new Knight(6, 7, Piece.BLACK);
        board[7][7] = new Rook(7, 7, Piece.BLACK);
        for (int i = 0; i < Piece.MAX_X; i++) {
            board[i][6] = new Pawn(i, 6, Piece.BLACK);
        }

        boardView = new BoardView(gridPane, board);

        gameStart();
    }

}
