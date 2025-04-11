package org.example.rpsgame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(GameController.class)
class GameControllerTest {

    private static final String ROCK = GameMove.ROCK.getValue();
    private static final String SCISSOR = GameMove.SCISSOR.getValue();
    private static final String DRAW = GameResult.DRAW.getValue();
    private static final String PLAYER_1_WINS = GameResult.PLAYER_1_WINS.getValue();
    private static final String PLAYER_2_WINS = GameResult.PLAYER_2_WINS.getValue();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @Test
    void testGamePageLoads() throws Exception {
        mockMvc.perform(get("/game"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"));
    }

    @Test
    void testEvaluateWinner_InvalidInput() throws Exception {
        mockMvc.perform(post("/game")
                        .param("playerOneValue", "Invalid")
                        .param("playerTwoValue", "Rock"))
                .andExpect(status().isOk());
    }

    @Test
    void testEvaluateWinner_ReturnsDrawWhenMovesAreEqual() throws Exception {
        when(gameService.evaluateWinner(ROCK, ROCK)).thenReturn(DRAW);

        mockMvc.perform(post("/game")
                        .param("playerOneValue", ROCK)
                        .param("playerTwoValue", ROCK))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("result", DRAW))
                .andExpect(model().attribute("playerOneValue", ROCK))
                .andExpect(model().attribute("playerTwoValue", ROCK));
    }

    @Test
    void testEvaluateWinner_PlayerOneWins() throws Exception {
        when(gameService.evaluateWinner(ROCK, SCISSOR)).thenReturn(PLAYER_1_WINS);

        mockMvc.perform(post("/game")
                        .param("playerOneValue", ROCK)
                        .param("playerTwoValue", SCISSOR))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("result", PLAYER_1_WINS))
                .andExpect(model().attribute("playerOneValue", ROCK))
                .andExpect(model().attribute("playerTwoValue", SCISSOR));
    }

    @Test
    void testEvaluateWinner_PlayerTwoWins() throws Exception {
        when(gameService.evaluateWinner(SCISSOR, ROCK)).thenReturn(PLAYER_2_WINS);

        mockMvc.perform(post("/game")
                        .param("playerOneValue", SCISSOR)
                        .param("playerTwoValue", ROCK))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("result", PLAYER_2_WINS))
                .andExpect(model().attribute("playerOneValue", SCISSOR))
                .andExpect(model().attribute("playerTwoValue", ROCK));
    }
}