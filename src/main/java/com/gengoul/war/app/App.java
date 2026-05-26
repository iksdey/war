package com.gengoul.war.app;

import com.gengoul.war.domain.*;
import com.gengoul.war.ui.ConsoleRenderer;
import com.gengoul.war.ui.GameMenuAction;
import com.gengoul.war.ui.MainMenuAction;
import com.gengoul.war.ui.Menu;

public class App {

    public static void main(String[] args) {
        ConsoleRenderer renderer = new ConsoleRenderer();
        Deck fullDeck = Deck.createFullDeck();

        Menu menu = new Menu();
        while (true) {
            MainMenuAction mainMenuAction = menu.mainMenu();
            switch (mainMenuAction) {
                case DISPLAY_CARDS -> renderer.displayCards(fullDeck.getCards());
                case SHUFFLE_CARDS -> fullDeck.shuffle();
                case START_GAME -> startGame();
                default -> throw new IllegalStateException("Unexpected main menu action: " + mainMenuAction);
            }
        }
    }

    private static void startGame() {
        WarGame warGame = new WarGame(4);
        Menu menu = new Menu();

        while (!warGame.isOver()) { // TODO negative condition
            GameMenuAction gameMenuAction = menu.gameMenu();
            switch (gameMenuAction) {
                case NEXT_TURN -> {
                    warGame.nextRound();
                }
                case DISPLAY_PLAYERS_DECKS -> {
                    System.out.println("J'affiche les decks des joueurs...");
                }
                case AUTOMATE_TO_END -> {
                    // TODO ou bien on fait un while(...) {nextTurn} pour afficher toutes les mains...
                    warGame.playUntilGameOver();
                }
                default -> throw new IllegalStateException("Unexpected game menu action: " + gameMenuAction);
            }
        }

        System.out.println("MACHIN a gagné la partie !");
    }
}
