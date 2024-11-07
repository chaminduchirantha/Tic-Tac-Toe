package com.assignment.tictactoe.service.tictactoe;

public class BoardImpl implements Board {
    Piece[][] pieces;
    private Piece[][] board;

    public BoardImpl() {
        pieces = new Piece[3][3];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && pieces[row][col] == Piece.EMPTY;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        if (isLegalMove(row, col)) {
            pieces[row][col] = piece;
        }
    }

    public boolean isGameOver() {
        return this.checkWinner() != null || this.isBoardFull();
    }

    private boolean isBoardFull() {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if (this.board[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public Piece checkWinner() {
        for (int i = 0; i <= 2; i++) {
            if (pieces[i][0] == pieces[i][1] && pieces[i][0] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) {
                return pieces[i][0];
            }
            if (pieces[0][i] == pieces[1][i] && pieces[0][i] == pieces[2][i] && pieces[i][0] != Piece.EMPTY) {
                return pieces[0][i];
            }
        }

        if (pieces[0][0] == pieces[1][1] && pieces[0][0] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) {
            return pieces[0][0];
        } else if (pieces[0][2] == pieces[1][1] && pieces[0][2] == pieces[2][0] && pieces[0][2] != Piece.EMPTY){
            return pieces[0][2];
        } else {
            return null;
        }
    }

    @Override
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    System.out.print("- ");
                } else {
                    System.out.print(pieces[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean isFull(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public Piece[][] getPieces() {
        return pieces;
    }
}
