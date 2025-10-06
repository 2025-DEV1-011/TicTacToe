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

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(webController).build();
    }

    @Test
    void newGame_shouldReturnNewGameState() throws Exception {
        Game mockGame = new Game();
        given(gameService.createNewGame()).willReturn(mockGame);
        mockMvc.perform(post("/api/game"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.gameId").exists())
                .andExpect(jsonPath("$.board").isArray())
                .andExpect(jsonPath("$.board.length()").value(3))
                .andExpect(jsonPath("$.board[0].length()").value(3))
                .andExpect(jsonPath("$.currentPlayer").value("X"));
    }
}