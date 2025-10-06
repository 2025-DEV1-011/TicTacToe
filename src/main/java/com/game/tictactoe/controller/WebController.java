package com.game.tictactoe.controller;

import com.game.tictactoe.model.Game;
import com.game.tictactoe.model.MoveRequest;
import com.game.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    private final GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/api/game")
    public ResponseEntity<Game> newGame() {
        Game game = gameService.createNewGame();
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @PostMapping("/api/game/{gameId}/move")
    public ResponseEntity<Game> makeMove(@PathVariable String gameId, @RequestBody MoveRequest moveRequest) {
        Game updatedGame = gameService.makeMove(gameId, moveRequest.row(), moveRequest.col());
        return ResponseEntity.ok(updatedGame);
    }
}
