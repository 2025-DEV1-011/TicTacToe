package com.game.tictactoe.model;

import java.util.UUID;

public class Game {
    private final String gameId;
    private final Player[][] board;
    private Player currentPlayer;
    private GameState gameState = GameState.ONGOING;

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

        // Row-wise win detection
        boolean rowWin = true;
        for (int c = 0; c < 3; c++) {
            if (board[row][c] != currentPlayer) {
                rowWin = false;
                break;
            }
        }
        if (rowWin) {
            gameState = (currentPlayer == Player.X) ? GameState.X_WINS : GameState.O_WINS;
            return gameState;
        }

        // Column-wise win detection
        boolean colWin = true;
        for (int r = 0; r < 3; r++) {
            if (board[r][col] != currentPlayer) {
                colWin = false;
                break;
            }
        }
        if (colWin) {
            gameState = (currentPlayer == Player.X) ? GameState.X_WINS : GameState.O_WINS;
            return gameState;
        }

        // Switch player after move
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;

        return gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
