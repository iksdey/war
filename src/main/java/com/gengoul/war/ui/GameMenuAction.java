package com.gengoul.war.ui;

public enum GameMenuAction {

    NEXT_ROUND(1),
    DISPLAY_DECKS(2),
    GO_TO_END_OF_GAME(3);

    private final int value;

    GameMenuAction(int value) {
        this.value = value;
    }

    public static GameMenuAction fromInt(int value) {
        for (GameMenuAction action : values()) {
            if (action.value == value) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown game menu action: " + value);
    }
}
