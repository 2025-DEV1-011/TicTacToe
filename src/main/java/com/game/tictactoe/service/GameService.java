package com.game.tictactoe.service;

import com.game.tictactoe.model.Game;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class GameService {
    private final Map<String, Game> games = new HashMap<>();
    private final Supplier<Game> gameSupplier;

    public GameService() {
        this(Game::new);
    }

    // For testing: inject a custom Game supplier (e.g., a mock)
    public GameService(Supplier<Game> gameSupplier) {
        this.gameSupplier = gameSupplier;
    }

    public Game createNewGame() {
        Game game = gameSupplier.get();
        games.put(game.getGameId(), game);
        return game;
    }

    public Game makeMove(String gameId, int row, int col) {
        Game game = games.get(gameId);
        game.makeMove(row, col);
        return game;
    }
}
