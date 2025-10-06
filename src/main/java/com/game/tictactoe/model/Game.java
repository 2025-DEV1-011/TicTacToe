package com.game.tictactoe.model;

import java.util.UUID;

public class Game {
    private final String gameId;
    private final Player[][] board;
    private final Player currentPlayer;

    public Game() {
        this.gameId = UUID.randomUUID().toString();
        this.board = new Player[3][3];
        this.currentPlayer = Player.X;
    }

    public String getGameId() {
        return gameId;
    }

    public Player[][] getBoard() {
        return board;
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
