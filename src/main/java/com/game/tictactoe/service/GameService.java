package com.game.tictactoe.service;

import com.game.tictactoe.model.Game;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    private final Map<String, Game> games = new HashMap<>();

    public Game createNewGame() {
        Game game = new Game();
        games.put(game.getGameId(), game);
        return game;
    }

    public Game makeMove(String gameId, int row, int col) {
        Game game = games.get(gameId);
        game.getBoard()[row][col] = "X";
        return game;
    }
}
