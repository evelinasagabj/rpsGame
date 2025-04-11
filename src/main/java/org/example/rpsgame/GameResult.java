package org.example.rpsgame;

public enum GameResult {

    PLAYER_1_WINS("Player 1 wins!"),
    PLAYER_2_WINS("Player 2 wins!"),
    DRAW("It's a tie!");

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
