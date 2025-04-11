package org.example.rpsgame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class GameIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGameFlow() throws Exception {
        mockMvc.perform(post("/game")
                        .param("playerOneValue", GameMove.ROCK.getValue())
                        .param("playerTwoValue", GameMove.SCISSOR.getValue()))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("result", GameResult.PLAYER_1_WINS.getValue()))
                .andExpect(model().attribute("playerOneValue", GameMove.ROCK.getValue()))
                .andExpect(model().attribute("playerTwoValue", GameMove.SCISSOR.getValue()));
    }
}
