package com.game.tictactoe.model;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
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

    @Test
    void makeMove_shouldNotAllowMoveOnTakenPosition() {
        // Arrange
        Game game = new Game();
        int row = 0, col = 0;
        game.makeMove(row, col); // First move by X

        Player firstPlayer = Player.X;
        Player secondPlayer = Player.O;

        // Act: Try to move again on the same cell
        game.makeMove(row, col);

        // Assert: Board should not change, player should not switch
        assertEquals(firstPlayer, game.getBoard()[row][col], "Cell should remain as X after invalid move");
        assertEquals(secondPlayer, game.getCurrentPlayer(), "Current player should remain O after invalid move");
    }

    @Test
    void makeMove_shouldReturn_ONGOING_whenGameIsNotFinished() {
        // Arrange
        Game game = new Game();

        // Act
        int row = 0, col = 0;
        GameState currentState = game.makeMove(row, col); // First move

        // Assert
        assertThat(currentState, CoreMatchers.is(GameState.ONGOING));
    }

    @Test
    void makeMove_shouldReturnXWinsWhenXCompletesARow() {
        // Arrange
        Game game = new Game();
        // moves
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O

        // Act: Final move
        GameState result = game.makeMove(0, 2); // X wins

        // Assert
        assertEquals(GameState.X_WINS, result, "Should return X_WINS when X completes a row");
    }

    private static Player getRandomPlayer() {
        Player[] players = Player.values();
        return players[new Random().nextInt(players.length)];
    }
}