package com.game.tictactoe.model;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    @Test
    void makeMove_shouldUpdateBoardAtGivenPosition() {
        // Arrange
        int row = 1, col = 1;
        Game game = new Game();

        // Act
        game.makeMove(row, col);

        // Assert
        assertEquals(Player.X, game.getBoard()[row][col], "Cell should be updated to Player.X after move");
    }


    @Test
    void makeMove_shouldSwitchCurrentPlayerAfterSuccessfulMove() {
        // Arrange
        Player randomPlayer = getRandomPlayer();
        Game game = new Game(randomPlayer);

        // Act
        int row = 0, col = 0;
        game.makeMove(row, col);

        // Assert
        Player expectedPlayer = (randomPlayer == Player.X) ? Player.O : Player.X;
        assertEquals(expectedPlayer, game.getCurrentPlayer(), "Player should be O after First move");
    }

    private static Player getRandomPlayer() {
        Player[] players = Player.values();
        return players[new Random().nextInt(players.length)];
    }
}