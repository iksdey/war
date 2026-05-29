package com.gengoul.war.app;

import com.gengoul.war.domain.Deck;
import com.gengoul.war.domain.WarGame;
import com.gengoul.war.ui.ConsoleRenderer;
import com.gengoul.war.ui.GameMenuAction;
import com.gengoul.war.ui.InputHandler;
import com.gengoul.war.ui.MainMenuAction;

public class App {

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private final ConsoleRenderer renderer = new ConsoleRenderer();
    private final InputHandler inputHandler = new InputHandler();

    private Deck deck = Deck.createFullDeck();

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        while (true) {
            renderer.displayMainMenu();
            MainMenuAction mainMenuAction = MainMenuAction.fromInt(inputHandler.askInt(1, 3));
            switch (mainMenuAction) {
                case DISPLAY_CARDS -> renderer.displayCards(deck.getCards());
                case SHUFFLE_CARDS -> deck.shuffle();
                case NEW_GAME -> newGame();
                default -> throw new IllegalStateException("Unexpected main menu action: " + mainMenuAction);
            }
        }
    }

    private void newGame() {
        renderer.askNumberOfPlayers(MIN_PLAYERS, MAX_PLAYERS);
        int playerCount = inputHandler.askInt(MIN_PLAYERS, MAX_PLAYERS);
        WarGame warGame = new WarGame(deck, playerCount);

        // Boucle de partie
        while (!warGame.isOver()) {
            renderer.displayGameMenu();
            GameMenuAction gameMenuAction = GameMenuAction.fromInt(inputHandler.askInt(1, 3));
            switch (gameMenuAction) {
                case NEXT_ROUND -> renderer.displayRound(warGame.nextRound());
                case DISPLAY_DECKS -> renderer.displayPlayersDecks(warGame.getPlayers());
                case GO_TO_END_OF_GAME -> warGame.playUntilGameOver();
                default -> throw new IllegalStateException("Unexpected game menu action: " + gameMenuAction);
            }
        }

        renderer.displayWinner(warGame.getWinner());
        deck = Deck.createFullDeck();
    }
}
