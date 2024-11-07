package com.assignment.tictactoe.service.tictactoe;

public class AiPlayer extends Player {
    private static final int winScore = 10;
    private static final int loseScore = -10;

    public AiPlayer(Board board) {
        super(board);

    }

    @Override
    public void move(int row, int col) {
        int[] bestMove = findBestMove(board.getPieces());

        if (board.isLegalMove(bestMove[0], bestMove[1])) {
            board.updateMove(bestMove[0], bestMove[1], Piece.O);
        } else {
            makeFirstAvailableMove();
        }
    }

    private void makeFirstAvailableMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isLegalMove(i, j)) {
                    board.updateMove(i, j, Piece.O);
                    return;
                }
            }
        }
    }

    private int[] findBestMove(Piece[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {0, 0};
//        meke bestMove kiyana Array ekak hadala ekat dagannawa row ekai colomn ekai ( 0 , 0 )

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Piece.EMPTY) {
                    //ee colomn ekai row ekai emptyda kiyala balanawa emptynm
                    board[i][j] = Piece.O;
                    //danata ee row ekai colmn ekatai "o" daganna kiyanawa
                    int score = minimax(board, 0, false);//methan isMaximising False karala nisa is Maximising kiyana if eka wada karanne na else eka thamai wada karanne
                    board[i][j] = Piece.EMPTY;//nathnm empty karaganna kiyanawa

                    if (score > bestScore) {//methanadi score eka bestscor ekata wada wadin nm scor eka best Score ekata assign wenawa
                        //best move eke thiyene 0 index ekata i kiyana row eke value eka asign karanawa
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;//bestMove kiyana method eke thiyena array eke 1 weni index eka ta colmn ekata awa value eka dagannawa
                    }
                }
            }
        }
//ita passe hodama bestMove eka return karanawa move kiyana method ekata
        return bestMove;
    }

    private int minimax(Piece[][] board, int depth, boolean isMaximizing) {
        int score = evaluateBoard(board);//kauru hari dinnada kiyala balanava
        if (score == winScore) return score - depth;// ai eka puluwan tharam ikmanata dinanna balanava
        if (score == loseScore) return score + depth;// ai eka puluwan tharam pahuwela paradenna balanava
        if (isGameOver(board)) return 0; // draw wenava


        if (isMaximizing) {// ai eke dinana chance eka maximize karanava

            int bestScore = Integer.MIN_VALUE;//hodama score ekata danata aduma value eka danava
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == Piece.EMPTY) {// board eke piece eka empty nan ekata danata O danava
                        board[i][j] = Piece.O;//e bord eke row ekatai colomn ekatai 'O' dagannawa
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false));//false unam aaye else eka wada karanawa
                        //recurition methods wage
                        board[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;//aduma scor ekata wadi value ekak dagannawa mokada x kiyanne human nisa humanta dinanna dena awastha adu karanna oone
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == Piece.EMPTY) {
                        board[i][j] = Piece.X;
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true));//mekata aawama depth eka ekakin wadi wenawa is maximising kiyana eka true wenawa iitapasse uda if eka wada karanawa
                        board[i][j] = Piece.EMPTY;
                    }
                }
            }
            return bestScore;
        }
    }

    private boolean isGameOver(Piece[][] board) {
// check karanawa thawa dinanna awastha thiyenawada kiyala
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Piece.EMPTY) { //bord kiyana array eke row ekai colomn ekai empty da kiyala balanawa empty nm retun karanawa false
                    return false;  // nathnam return karanawa true;
                }
            }
        }
        return true;
    }

    private int evaluateBoard(Piece[][] board) {
        // check karanawa bord ekek thiyene hama row ekakama
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == Piece.O) return winScore;
                if (board[row][0] == Piece.X) return loseScore;
            }
        }

        // Check karanawa hama colomn ekakma
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == Piece.O) return winScore;
                if (board[0][col] == Piece.X) return loseScore;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == Piece.O) return winScore;
            if (board[0][0] == Piece.X) return loseScore;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == Piece.O) return winScore;
            if (board[0][2] == Piece.X) return loseScore;
        }
        return 0;
    }
}