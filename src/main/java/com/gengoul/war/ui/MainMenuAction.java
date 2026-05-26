package com.gengoul.war.ui;

public enum MainMenuAction {

    DISPLAY_CARDS(1),
    SHUFFLE_CARDS(2),
    START_GAME(3);

    private final int value;

    MainMenuAction(int value) {
        this.value = value;
    }

    public static MainMenuAction fromInt(int value) {
        for (MainMenuAction action : values()) {
            if (action.value == value) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown main menu action: " + value);
    }
}
