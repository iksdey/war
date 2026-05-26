package com.gengoul.war.ui;

public enum GameMenuAction {

    NEXT_TURN(1),
    DISPLAY_PLAYERS_DECKS(2),
    AUTOMATE_TO_END(3);

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
