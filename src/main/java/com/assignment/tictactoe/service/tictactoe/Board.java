package com.assignment.tictactoe.service.tictactoe;

public interface Board {
    void initializeBoard();
    boolean isLegalMove(int row, int col);
    void updateMove(int row, int col, Piece piece);
    Piece checkWinner();
    void printBoard();
    boolean isGameOver();
    boolean isFull();
    Piece[][] getPieces();
}
