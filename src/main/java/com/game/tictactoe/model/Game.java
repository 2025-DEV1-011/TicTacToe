package com.game.tictactoe.model;

import java.util.UUID;

public class Game {
    private final String gameId;
    private final String[][] board;
    private final String currentPlayer;

    public Game() {
        this.gameId = UUID.randomUUID().toString();
        this.board = new String[3][3];
        this.currentPlayer = "X";
    }

    public String getGameId() {
        return gameId;
    }

    public String[][] getBoard() {
        return board;
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}

