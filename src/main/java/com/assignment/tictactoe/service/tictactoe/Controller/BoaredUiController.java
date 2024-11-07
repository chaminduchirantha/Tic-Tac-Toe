package com.assignment.tictactoe.service.tictactoe.Controller;

import com.assignment.tictactoe.service.tictactoe.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BoaredUiController {
    private Board board = new BoardImpl();
    private Player aiPlayer = new AiPlayer(board);
    private Player humanPlayer = new HumanPlayer(board);
    private boolean gameOver = false;

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Label lblWinner;

    @FXML
    void ActionEight(ActionEvent event) {
        buttonAction(2, 1, btn8);
    }

    @FXML
    void ActionFive(ActionEvent event) {
        buttonAction(1, 1, btn5);
    }

    @FXML
    void ActionFour(ActionEvent event) {
        buttonAction(1, 0, btn4);
    }

    @FXML
    void ActionNine(ActionEvent event) {
        buttonAction(2, 2, btn9);
    }

    @FXML
    void ActionOne(ActionEvent event) {
        buttonAction(0, 0, btn1);
    }

    @FXML
    void ActionSeven(ActionEvent event) {
        buttonAction(2, 0, btn7);
    }

    @FXML
    void ActionSix(ActionEvent event) {
        buttonAction(1, 2, btn6);
    }

    @FXML
    void ActionThree(ActionEvent event) {
        buttonAction(0, 2, btn3);
    }

    @FXML
    void ActionTwo(ActionEvent event) {
        buttonAction(0, 1, btn2);
    }

    @FXML
    void actionOnReset(ActionEvent event) {

        board = new BoardImpl();
        aiPlayer = new AiPlayer(board);
        humanPlayer = new HumanPlayer(board);
        gameOver = false;

        Button[] buttons = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        for (Button button : buttons) {
            button.setDisable(false);
            button.setStyle("");
        }

        updateBoard();
        lblWinner.setText("");
    }

    private void buttonAction(int row, int col, Button button) {
        if (gameOver || !board.isLegalMove(row, col)) {
            return;
        }

        humanPlayer.move(row, col);
        updateBoard();

        if (board.checkWinner() == Piece.X) {
            endGame("Human Wins!");
            return;
        }

        if (board.isFull()) {
            endGame("Draw!");
            return;
        }

        aiPlayer.move(-1, -1);
        updateBoard();

        if (board.checkWinner() == Piece.O) {
            endGame("AI Wins!");
            return;
        }

        if (board.isFull()) {
            endGame("Draw!");
        }
    }

    private void endGame(String message) {
        gameOver = true;
        lblWinner.setText(message);
        disableAllButtons();
    }

    private void disableAllButtons() {
        Button[] buttons = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    private void updateBoard() {
        Button[][] buttons = {
                {btn1, btn2, btn3},
                {btn4, btn5, btn6},
                {btn7, btn8, btn9}
        };

        Piece[][] pieces = board.getPieces();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.X) {
                    buttons[i][j].setText("X");
                } else if (pieces[i][j] == Piece.O) {
                    buttons[i][j].setText("O");
                } else {
                    buttons[i][j].setText(" ");
                }
            }
        }
    }

    @FXML
    public void initialize() {
        updateBoard();
        lblWinner.setText("");
    }
}