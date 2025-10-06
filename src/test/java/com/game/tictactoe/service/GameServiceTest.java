package com.game.tictactoe.service;

import com.game.tictactoe.model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameServiceTest {

    private final GameService service = new GameService();

    @Test
    void makeMove_shouldUpdateBoard() {
        // Arrange
        Game game = service.createNewGame();

        // Act
        String gameId = game.getGameId();
        int row = 0, col = 2;
        Game updated = service.makeMove(gameId, row, col);

        // Assert
        assertEquals("X", updated.getBoard()[row][col]);
        assertEquals(gameId, updated.getGameId());
    }
}

