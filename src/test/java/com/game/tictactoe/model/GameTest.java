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

    @Test
    void makeMove_shouldReturnOWinsWhenOCompletesARow() {
        // Arrange
        Game game = new Game();

        // moves
        game.makeMove(1, 2); // X
        game.makeMove(0, 0); // O
        game.makeMove(2, 2); // X
        game.makeMove(0, 1); // O
        game.makeMove(2, 1); // X

        // Act: Final move
        GameState result = game.makeMove(0, 2); // O wins (row 0)

        // Assert
        assertEquals(GameState.O_WINS, result, "Should return O_WINS when O completes a row");
    }

    @Test
    void makeMove_shouldReturnXWinsWhenXCompletesAColumn() {
        // Arrange
        Game game = new Game();
        // Moves
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 1); // O

        // Act: Final move
        GameState result = game.makeMove(2, 0); // X wins by column

        // Assert
        assertEquals(GameState.X_WINS, result, "Should return X_WINS when X completes a column");
    }

    @Test
    void makeMove_shouldReturnOWinsWhenOCompletesAColumn() {
        // Arrange
        Game game = new Game();
        // Moves
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 1); // O
        game.makeMove(2, 2); // X

        // Act: Final move
        GameState result = game.makeMove(2, 1); // O wins by column

        // Assert
        assertEquals(GameState.O_WINS, result, "Should return O_WINS when O completes a column");
    }

    @Test
    void makeMove_shouldReturnXWinsWhenXCompletesADiagonal() {
        // Arrange
        Game game = new Game();
        // X will fill the main diagonal (0,0), (1,1), (2,2)
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 1); // X
        game.makeMove(0, 2); // O

        // Act: Final move
        GameState result = game.makeMove(2, 2); // X wins by diagonal

        // Assert
        assertEquals(GameState.X_WINS, result, "Should return X_WINS when X completes a diagonal");
    }

    @Test
    void makeMove_shouldReturnXWinsWhenXCompletesAntiDiagonal() {
        // Arrange
        Game game = new Game();
        // X will fill the anti-diagonal (0,2), (1,1), (2,0)
        game.makeMove(0, 2); // X
        game.makeMove(0, 0); // O
        game.makeMove(1, 1); // X
        game.makeMove(1, 0); // O

        // Act: Final move
        GameState result = game.makeMove(2, 0); // X wins by anti-diagonal

        // Assert
        assertEquals(GameState.X_WINS, result, "Should return X_WINS when X completes the anti-diagonal");
    }

    @Test
    void makeMove_shouldReturnOWinsWhenOCompletesMainDiagonal() {
        // Arrange
        Game game = new Game();
        // O will fill the main diagonal (0,0), (1,1), (2,2)
        game.makeMove(0, 1); // X
        game.makeMove(0, 0); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 1); // O
        game.makeMove(2, 1); // X

        // Act: Final move
        GameState result = game.makeMove(2, 2); // O wins by main diagonal

        // Assert
        assertEquals(GameState.O_WINS, result, "Should return O_WINS when O completes the main diagonal");
    }

    @Test
    void makeMove_shouldReturnOWinsWhenOCompletesAntiDiagonal() {
        // Arrange
        Game game = new Game();
        // O will fill the anti-diagonal (0,2), (1,1), (2,0)
        game.makeMove(0, 0); // X
        game.makeMove(0, 2); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 1); // O
        game.makeMove(2, 2); // X

        // Act: Final move
        GameState result = game.makeMove(2, 0); // O wins by anti-diagonal

        // Assert
        assertEquals(GameState.O_WINS, result, "Should return O_WINS when O completes the anti-diagonal");
    }

    private static Player getRandomPlayer() {
        Player[] players = Player.values();
        return players[new Random().nextInt(players.length)];
    }
}