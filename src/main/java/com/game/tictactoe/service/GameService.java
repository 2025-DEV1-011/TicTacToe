package com.game.tictactoe.service;

import com.game.tictactoe.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    public Game createNewGame() {
        return new Game();
    }

    public Game makeMove(String gameId, int row, int col) {
        return null;
    }
}

