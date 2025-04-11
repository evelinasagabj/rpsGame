package org.example.rpsgame;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    private static final String ROCK = GameMove.ROCK.getValue();
    private static final String PAPER = GameMove.PAPER.getValue();
    private static final String SCISSOR = GameMove.SCISSOR.getValue();
    private static final String DRAW = GameResult.DRAW.getValue();
    private static final String PLAYER_1_WINS = GameResult.PLAYER_1_WINS.getValue();
    private static final String PLAYER_2_WINS = GameResult.PLAYER_2_WINS.getValue();

    @ParameterizedTest
    @MethodSource("provideGameServiceTestData")
    void testEvaluateWinner_returnsExpectedResult(String playerOneValue, String playerTwoValue, String expectedResult) {
        String result = gameService.evaluateWinner(playerOneValue, playerTwoValue);
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> provideGameServiceTestData() {
        return Stream.of(
                Arguments.of(ROCK, ROCK, DRAW),
                Arguments.of(PAPER, PAPER, DRAW),
                Arguments.of(SCISSOR, SCISSOR, DRAW),
                Arguments.of(ROCK, SCISSOR, PLAYER_1_WINS),
                Arguments.of(SCISSOR, PAPER, PLAYER_1_WINS),
                Arguments.of(PAPER, ROCK, PLAYER_1_WINS),
                Arguments.of(SCISSOR, ROCK, PLAYER_2_WINS),
                Arguments.of(PAPER, SCISSOR, PLAYER_2_WINS),
                Arguments.of(ROCK, PAPER, PLAYER_2_WINS)
        );
    }
}
