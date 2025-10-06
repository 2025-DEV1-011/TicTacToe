package com.game.tictactoe.model;

import lombok.Getter;

import java.util.UUID;

@Getter
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

    /**
     * Visible for testing only.
     */
    Game(Player player) {
        this.gameId = UUID.randomUUID().toString();
        this.board = new Player[3][3];
        this.currentPlayer = player;
    }

    public GameState makeMove(int row, int col) {
        if (board[row][col] != null || gameState != GameState.ONGOING) {
            // Position already taken or game already finished
            return gameState;
        }

        board[row][col] = currentPlayer;

        if (isRowWin(row)) {
            gameState = (currentPlayer == Player.X) ? GameState.X_WINS : GameState.O_WINS;
            return gameState;
        }

        if (isColWin(col)) {
            gameState = (currentPlayer == Player.X) ? GameState.X_WINS : GameState.O_WINS;
            return gameState;
        }

        if (isMainDiagonalWin(row, col)) {
            gameState = (currentPlayer == Player.X) ? GameState.X_WINS : GameState.O_WINS;
            return gameState;
        }

        if (isAntiDiagonalWin(row, col)) {
            gameState = (currentPlayer == Player.X) ? GameState.X_WINS : GameState.O_WINS;
            return gameState;
        }

        // Switch player after move
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;

        return gameState;
    }

    private boolean isRowWin(int row) {
        for (int c = 0; c < 3; c++) {
            if (board[row][c] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private boolean isColWin(int col) {
        for (int r = 0; r < 3; r++) {
            if (board[r][col] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private boolean isMainDiagonalWin(int row, int col) {
        if (row != col) return false;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private boolean isAntiDiagonalWin(int row, int col) {
        if (row + col != 2) return false;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] != currentPlayer) {
                return false;
            }
        }
        return true;
    }
}
