package com.game.tictactoe.controller;

import com.game.tictactoe.model.Game;
import com.game.tictactoe.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WebControllerTest {
    @Mock
    private GameService gameService;

    @InjectMocks
    private WebController webController;

    private MockMvc mockMvc;
    private final Game mockGame = new Game();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(webController).build();

        given(gameService.createNewGame()).willReturn(mockGame);
    }

    @Test
    void newGame_shouldReturnNewGameState() throws Exception {
        mockMvc.perform(post("/api/game"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.gameId").exists())
                .andExpect(jsonPath("$.board").isArray())
                .andExpect(jsonPath("$.board.length()").value(3))
                .andExpect(jsonPath("$.board[0].length()").value(3))
                .andExpect(jsonPath("$.currentPlayer").value("X"));
    }

    @Test
    void makeMove_shouldUpdateBoardAndReturnGameState_FromServiceLayer() throws Exception {
        String gameId = mockGame.getGameId();
        int row = 1;
        int col = 1;
        mockGame.getBoard()[row][col] = "X";
        given(gameService.makeMove(gameId, row, col)).willReturn(mockGame);

        mockMvc.perform(post("/game/" + gameId + "/move")
                        .contentType("application/json")
                        .content("{\"row\":1,\"col\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.board[1][1]").value("X"))
                .andExpect(jsonPath("$.gameId").value(gameId));
    }
}