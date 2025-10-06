package com.game.tictactoe.service;

import com.game.tictactoe.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Supplier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Spy
    private Game mockGame;

    private final GameService service = new GameService(new Supplier<>() {
        @Override
        public Game get() {
            return mockGame;
        }
    });

    @BeforeEach
    void setUp() {
        when(mockGame.getGameId()).thenReturn("test_game");
    }

    @Test
    void makeMove_shouldUpdateGameBoard() {
        // Arrange
        Game game = service.createNewGame();
        String gameId = game.getGameId();
        int row = 0, col = 2;

        // Act
        service.makeMove(gameId, row, col);

        // Assert
        verify(mockGame).makeMove(row, col);
    }
}
