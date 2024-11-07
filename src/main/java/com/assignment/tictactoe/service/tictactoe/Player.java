package com.assignment.tictactoe.service.tictactoe;

public abstract class Player {
    Board board;

    public Player(Board board) {
        this.board = board;
    }

    abstract public void move(int row, int col);

    }
