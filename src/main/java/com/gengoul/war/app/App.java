package com.gengoul.war.app;

import com.gengoul.war.domain.*;
import com.gengoul.war.ui.ConsoleRenderer;
import com.gengoul.war.ui.GameMenuAction;
import com.gengoul.war.ui.MainMenuAction;
import com.gengoul.war.ui.Menu;

public class App {

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private final Menu menu = new Menu();
    private final ConsoleRenderer renderer = new ConsoleRenderer();

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        Deck fullDeck = Deck.createFullDeck();
        while (true) {
            MainMenuAction mainMenuAction = menu.mainMenu();
            switch (mainMenuAction) {
                case DISPLAY_CARDS -> renderer.displayCards(fullDeck.getCards());
                case SHUFFLE_CARDS -> fullDeck.shuffle();
                case NEW_GAME -> newGame(fullDeck);
                default -> throw new IllegalStateException("Unexpected main menu action: " + mainMenuAction);
            }
        }
    }

    private void newGame(Deck deck) {
        int playerCount = menu.getUserEntry(MIN_PLAYERS, MAX_PLAYERS);
        WarGame warGame = new WarGame(deck, playerCount);

        // Boucle de partie
        while (!warGame.isOver()) { // TODO negative condition
            GameMenuAction gameMenuAction = menu.gameMenu();
            switch (gameMenuAction) {
                case NEXT_ROUND -> {
                    warGame.nextRound();
                }
                case DISPLAY_DECKS -> {
                    System.out.println("J'affiche les decks des joueurs...");
                }
                case GO_TO_END_OF_GAME -> {
                    // TODO ou bien on fait un while(...) {nextTurn} pour afficher toutes les mains...
                    warGame.playUntilGameOver();
                }
                default -> throw new IllegalStateException("Unexpected game menu action: " + gameMenuAction);
            }
        }

        System.out.println("MACHIN a gagné la partie !");
    }
}
