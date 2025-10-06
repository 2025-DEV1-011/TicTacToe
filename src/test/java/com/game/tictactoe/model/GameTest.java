package com.game.tictactoe.model;

import org.junit.jupiter.api.Test;

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
}