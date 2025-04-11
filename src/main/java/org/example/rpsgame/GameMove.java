package org.example.rpsgame;

public enum GameMove {

    ROCK("Rock"),
    PAPER("Paper"),
    SCISSOR("Scissor");

    private final String value;

    GameMove(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
