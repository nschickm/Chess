package com.example.chess.controller;

import com.example.chess.model.*;
import com.example.chess.view.BoardView;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

import static com.example.chess.controller.LoginController.player1name;
import static com.example.chess.controller.LoginController.player2name;

public class BoardController extends AbstractController {
    @FXML
    private GridPane gridPane = new GridPane();
    @FXML
    private Label timePlayer2Label;
    @FXML
    private Label ratingPlayer2Label;
    @FXML
    private Label namePlayer2Label;
    @FXML
    private Label timePlayer1Label;
    @FXML
    private Label ratingPlayer1Label;
    @FXML
    private Label namePlayer1Label;

    private Piece[][] board = new Piece[Piece.MAX_X][Piece.MAX_Y];
    private BoardView boardView;
    private Boolean[][] possibleMoves = new Boolean[Piece.MAX_X][Piece.MAX_Y];
    private Boolean[][] tempPossibleMoves = new Boolean[Piece.MAX_X][Piece.MAX_Y];
    private Piece selected;
    private Player currentPlayer;
    private Boolean isChecked;
    private Player winner = null;
    private Player loser = null;
    private String drawType;
    private Player player1;
    private Player player2;
    private double timeBetweenMoves = System.currentTimeMillis();
    private DatabaseHandler databaseHandler = new DatabaseHandler();
    private int pieceWhichPutsKingInCheckPosX = 0;
    private int pieceWhichPutsKingInCheckPosY = 0;

    public void gameStart() throws SQLException {
        boardView.drawBoard();

        player1 = new Player(player1name, Piece.WHITE, SettingsController.time);
        player2 = new Player(player2name, Piece.BLACK, SettingsController.time);
        currentPlayer = player1;
        timePlayer1Label.setText(new SimpleDateFormat("mm:ss").format(currentPlayer.getTimeInMillis()));
        timePlayer2Label.setText(new SimpleDateFormat("mm:ss").format(currentPlayer.getTimeInMillis()));

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                currentPlayer.setTimeInMillis(currentPlayer.getTimeInMillis() - (System.currentTimeMillis() - timeBetweenMoves));
                timeBetweenMoves = System.currentTimeMillis();

                if (currentPlayer.getTimeInMillis() <= 0) {
                    if (currentPlayer == player1) {
                        winner = player2;
                    } else {
                        winner = player1;
                    }

                    try {
                        gameEnd();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stop();
                }

                if (currentPlayer == player1) {

                    timePlayer1Label.setText(new SimpleDateFormat("mm:ss:SS").format(currentPlayer.getTimeInMillis()));
                } else {

                    timePlayer2Label.setText(new SimpleDateFormat("mm:ss:SS").format(currentPlayer.getTimeInMillis()));
                }
            }
        }.start();

        ratingPlayer1Label.setText(databaseHandler.getPlayerData(player1));
        ratingPlayer2Label.setText(databaseHandler.getPlayerData(player2));
        namePlayer1Label.setText(player1name);
        namePlayer2Label.setText(player2name);
    }

    public void gameEnd() throws IOException {
        Stage stage = (Stage) timePlayer2Label.getScene().getWindow();
        stage.close();

        System.out.println("GamenEnd");

        ButtonType again = new ButtonType("Play again", ButtonBar.ButtonData.OK_DONE);
        ButtonType endGame = new ButtonType("Close game", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to play again?", again, endGame);
        alert.setTitle("Game End");
        alert.setHeaderText(null);

        System.out.println("winner: " + winner);
        if (winner != null) {
            alert.setContentText(winner.getName() + " won the game");
            alert.show();
            databaseHandler.setWin(winner.getName());
            databaseHandler.setLose(loser.getName());

        } else {
            alert.setContentText("The game ended by " + drawType);
            databaseHandler.setDraw();
        }
        alert.getDialogPane().setMinHeight(Region.USE_COMPUTED_SIZE);

        Optional<ButtonType> result = alert.showAndWait();

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

        if (!result.isPresent()) {

        } else if (result.orElse(endGame) == again) {
            System.out.println("sdf");
            LoginController l = this.loadFxmlFile(
                    "testlogindesign.fxml",
                    "Player 1 login",
                    null,
                    LoginController.class);
            //oke button is pressed

        } else if (result.equals(endGame)) {
            // cancel button is pressed
        }
    }

    @FXML
    void fieldClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();

        int col = GridPane.getColumnIndex(node);
        int row = GridPane.getRowIndex(node);
        if (selected == null) {

            if (board[col][Piece.MAX_Y - row - 1] != null) {
                selected = board[col][Piece.MAX_Y - row - 1];

                if (Objects.equals(selected.getColor(), currentPlayer.getColor())) {
                    possibleMoves = board[col][Piece.MAX_Y - row - 1].getPossibleMoves(board);
                    boardView.drawPossibleMoves(possibleMoves);
                }
            }
        } else {
            try {

                int kingX = -1;
                int kingY = -1;
                if (possibleMoves != null) {

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


                        //if (checkForCheck(board)) {
                            //System.out.println("IsCheckmate: " + isCheckmate());
                        //}



                        if (currentPlayer == player1) {
                            System.out.println("True/False: " + checkForCheck(board));
                            if (checkForCheck(board)){
                                System.out.println("isCheckmate: " + isCheckmate());
                            }
                            System.out.println("18777");
                            currentPlayer = player2;
                        } else {
                            if (checkForCheck(board)){
                                if (isCheckmate()){
                                    try {
                                        System.out.println();
                                        gameEnd();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                            currentPlayer = player1;
                            System.out.println("True/False: " + checkForCheck(board));
                            System.out.println("18777");
                        }

                    } else {
                        System.out.println("Move not possible");
                    }
                }


            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            possibleMoves = null;
            selected = null;
            boardView.drawBoard();
        }

    }

    public void drawButtonClicked(ActionEvent actionEvent) throws IOException {
        drawType = "offered draw";
        System.out.println("drwa");
        gameEnd();
    }

    public void resignButtonClicked(ActionEvent actionEvent) throws IOException {
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

    public boolean checkForCheck(Piece[][] board) {
        Piece currentKing = null;
        int kingPosX = 0;
        int kingPosY = 0;


        /**
         * Get position of current King
         */
        for (int i = 0; i < Piece.MAX_X; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (board[i][j] != null) {

                    if (Objects.equals(board[i][j].getColor(), currentPlayer.getOppositeColor()) && Objects.equals(board[i][j].getName(), "king")) {
                        kingPosX = i;
                        kingPosY = j;
                    }
                }
            }
        }


        for (int i = 0; i < Piece.MAX_X; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (board[i][j] != null) {

                    /*
                    If any of the current Players Pieces is able to move to the opposite Players King, the King is in Check
                     */
                    if (Objects.equals(board[i][j].getColor(), currentPlayer.getColor())) {


                        tempPossibleMoves = board[i][j].getPossibleMoves(board);

                        if (tempPossibleMoves[kingPosX][kingPosY]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate() {

        Piece[][] currentBoard = Piece.deepCopy(board);

        Boolean[][] tempPossibleMovesForKing = new Boolean[Piece.MAX_X][Piece.MAX_Y];

        int kingPosX = 0;
        int kingPosY = 0;

        Piece currentKing = null;

        /*
        get position of the current King
         */
        for (int i = 0; i < Piece.MAX_X ; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (board[i][j] != null) {

                    if (Objects.equals(board[i][j].getColor(), currentPlayer.getOppositeColor()) && Objects.equals(board[i][j].getName(), "king")) {
                        currentKing = board[i][j];
                        kingPosX = i;
                        kingPosY = j;
                    }
                }
            }
        }


        int tempCount = 0;
        int tempCount1 = 0;

        for (int i = 0; i < (Piece.MAX_X); i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (currentBoard[i][j] != null) {
                    if (Objects.equals(currentBoard[i][j].getColor(), currentPlayer.getOppositeColor()) && Objects.equals(currentBoard[i][j].getName(), "king")) {
                        tempPossibleMovesForKing = currentBoard[i][j].getPossibleMoves(currentBoard);

                        pieceWhichPutsKingInCheckPosX = i;
                        pieceWhichPutsKingInCheckPosY = j;

                        for (int k = 0; k < (Piece.MAX_X ); k++) {
                            for (int l = 0; l < Piece.MAX_Y; l++) {
                                if (tempPossibleMovesForKing[k][l]) {
                                    System.out.println("pos: " + k + " " + l);
                                    System.out.println("king: " + kingPosX + " " + kingPosY);



                                    System.out.println("pieceOn1: " + currentBoard[kingPosX][kingPosY]);
                                    currentBoard[kingPosX][kingPosY] = null;
                                    currentBoard[k][l] = currentKing;
                                    System.out.println("pieceOn2: " + currentBoard[kingPosX][kingPosY]);
                                    tempCount++;


                                    /*
                                    If all possible moves for the current King equal in a check, the King is in Checkmate
                                     */
                                    if (checkForCheck(currentBoard)) {
                                        System.out.println(Arrays.deepToString(currentBoard).replace("]", "]\n"));
                                        System.out.println("yes");
                                        tempCount1++;
                                    } else {
                                        System.out.println("no");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (tempCount == tempCount1) {
            for (int i = 0; i < Piece.MAX_X; i++) {
                for (int j = 0; j < Piece.MAX_Y; j++) {
                    if (board[i][j] != null) {

                    /*
                    Run through all Opposite Players and see if the can move to positions which block or hit the piece that put the opposite king in check
                     */
                        if (Objects.equals(board[i][j].getColor(), currentPlayer.getOppositeColor())) {
                            Boolean[][] movesOfCurrentPieceInLoop = board[i][j].getPossibleMoves(currentBoard);


                            for (int k = 0; k < Piece.MAX_X; k++){
                                for (int l = 0; l < Piece.MAX_Y; l++){
                                    if (tempPossibleMoves[k][l]){

                                        /*
                                        If current Piece in Loop can block the way of the Piece which put the King in Check to the king OR If current Piece in Loop can hit this piece
                                         */
                                        if (movesOfCurrentPieceInLoop[k][l] || movesOfCurrentPieceInLoop[pieceWhichPutsKingInCheckPosX][pieceWhichPutsKingInCheckPosY]) {
                                            return false;
                                        } {
                                            winner = currentPlayer;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return false;
        }


        /*
        for (int i = 1; i < Piece.MAX_X - 1; i++) {
            for (int j = 0; j < Piece.MAX_Y; j++) {
                if (board[i][j] != null){

                    if (Objects.equals(board[i][j].getColor(), currentPlayer.getOppositeColor()) && Objects.equals(board[i][j].getName())){
                        System.out.println("CurrentPosKing: " + kingPosX + " " + kingPosY);
                        System.out.println(board[i][j].getName() + " " + i + " " + j);

                        tempPossibleMoves = board[i][j].getPossibleMoves(board);
                        System.out.println("kk: " + tempPossibleMoves[kingPosX][kingPosY]);
                        if (tempPossibleMoves[kingPosX][kingPosY]){
                            return true;
                        }
                    }
                }
            }
        }

        System.out.println("Current King: " + currentKing.getPossibleMoves(currentBoard));


        possiblePositionsKings.add(new King(currentKing.getX() + 1, currentKing.getY() - 1,currentPlayer.getColor()));

        return true;

         */
        return false;
    }




}
