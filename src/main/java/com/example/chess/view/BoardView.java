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

public class BoardView {
    private GridPane gridPane;
    private ArrayList<ImageView> images = new ArrayList<>();
    Piece[][] board;

    public BoardView(GridPane gridPane, Piece[][] board) {
        this.gridPane = gridPane;
        this.board = board;
    }

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

    public String showPlayer1Data() throws SQLException {
        String player1 = "";
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT wins AS wins, losses AS losses, draws AS draws FROM t_user WHERE username = '" + player1name + "';");

            if (resultSet.next()) {
                player1 = "Wins: " + resultSet.getString("wins") + " / Lose: " + resultSet.getString("losses") + " / Draws: " + resultSet.getString("draws");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player1;
    }


    public String showPlayer2Data() throws SQLException {
        String player2 = "";
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT wins AS wins, losses AS losses, draws AS draws FROM t_user WHERE username = '" + player2name + "';");

            if (resultSet.next()) {
                player2 ="Wins: " + resultSet.getString("wins") + " / Lose: " + resultSet.getString("losses") + " / Draws: " + resultSet.getString("draws");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player2;
    }

    public void setDraw(){
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("UPDATE t_user SET draws = IFNULL(draws, 0) + 1 WHERE username ='" + player1name  + "';");
            ResultSet resultSet1 = statement.executeQuery("UPDATE t_user SET draws = IFNULL(draws, 0) + 1 WHERE username ='" + player2name  + "';");

            resultSet1.close();
            resultSet.close();
            statement1.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setWin(String player){
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("UPDATE t_user SET wins = IFNULL(wins, 0) + 1 WHERE username ='" + player  + "';");


            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setLose(String player){
        Connection connection = Database.getConnection();
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("UPDATE t_user SET losses = IFNULL(losses, 0) + 1 WHERE username ='" + player  + "';");

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
