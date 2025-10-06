package com.game.tictactoe.model;

import java.util.UUID;

public class Game {
    private final String gameId;
    private final Player[][] board;
    private Player currentPlayer;
    private final GameState gameState = GameState.ONGOING;

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

    public GameState makeMove(int row, int col) {
        if (board[row][col] != null || gameState != GameState.ONGOING) {
            // Position already taken or game already finished
            return gameState;
        }

        board[row][col] = currentPlayer;

        // Switch player after move
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;

        return gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
