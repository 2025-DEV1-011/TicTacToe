package com.game.tictactoe.model;

import java.util.UUID;

public class Game {
    private final String gameId;
    private final Player[][] board;
    private Player currentPlayer;

    public Game() {
        this.gameId = UUID.randomUUID().toString();
        this.board = new Player[3][3];
        this.currentPlayer = Player.X;
    }

    Game(Player player) {
        this.gameId = UUID.randomUUID().toString();
        this.board = new Player[3][3];
        this.currentPlayer = player;
    }

    public String getGameId() {
        return gameId;
    }

    public Player[][] getBoard() {
        return board;
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;

        // Switch player after move
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
