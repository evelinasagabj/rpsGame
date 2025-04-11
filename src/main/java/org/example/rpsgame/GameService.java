package org.example.rpsgame;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GameService {

    public static final String ROCK_VALUE = GameMove.ROCK.getValue();
    public static final String SCISSOR_VALUE = GameMove.SCISSOR.getValue();
    public static final String PAPER_VALUE = GameMove.PAPER.getValue();

    public String evaluateWinner(String playerOneValue, String playerTwoValue) {
        String result;

        if (playerOneValue.equals(playerTwoValue)) {
            result = GameResult.DRAW.getValue();
        } else if ((playerOneValue.equals(ROCK_VALUE) && playerTwoValue.equals(SCISSOR_VALUE)) ||
                (playerOneValue.equals(PAPER_VALUE) && playerTwoValue.equals(ROCK_VALUE)) ||
                (playerOneValue.equals(SCISSOR_VALUE) && playerTwoValue.equals(PAPER_VALUE))) {
            result = GameResult.PLAYER_1_WINS.getValue();
        } else {
            result = GameResult.PLAYER_2_WINS.getValue();
        }
        return result;
    }
}
