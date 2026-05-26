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

    // TODO le découpage en une fonction newGame() est-il logique ?
    public void run() {
        Deck fullDeck = Deck.createFullDeck();
        while (true) {
            MainMenuAction mainMenuAction = menu.mainMenu();
            switch (mainMenuAction) {
                case DISPLAY_CARDS -> renderer.displayCards(fullDeck.getCards());
                case SHUFFLE_CARDS -> fullDeck.shuffle();
                case NEW_GAME -> playGame(fullDeck);
                default -> throw new IllegalStateException("Unexpected main menu action: " + mainMenuAction);
            }
        }
    }

    // TODO nom dégueulasse ?
    private void playGame(Deck deck) {
        // TODO Menu::askPlayerCount
        System.out.println("Combien de joueurs (entre " + MIN_PLAYERS + " et " + MAX_PLAYERS + ") ?");
        int playerCount = menu.getUserEntry(MIN_PLAYERS, MAX_PLAYERS);
        WarGame warGame = new WarGame(deck, playerCount);

        // Boucle de partie
        // TODO c'est pas un peu sale de laisser tant de contrôle sur le cours du jeu à la classe App et pas WarGame ?
        while (!warGame.isOver()) { // TODO negative condition
            GameMenuAction gameMenuAction = menu.gameMenu();
            switch (gameMenuAction) {
                case NEXT_ROUND -> {
                    RoundResult round = warGame.nextRound();
                    renderer.displayRound(round);
                }
                case DISPLAY_DECKS -> {
                    // TODO syntaxe one-line pas claire ?
                    // TODO déléguer plus de choses à ConsoleRenderer ?
                    warGame.getPlayers().forEach(player -> {
                        System.out.println(player.getName());
                        renderer.displayCards(player.getCards());
                    });
                }
                case GO_TO_END_OF_GAME -> {
                    // TODO ou bien on fait un while(...) {nextTurn} pour afficher toutes les mains...
                    warGame.playUntilGameOver();
                    // TODO car ici on véréfie que le game is over dans WarGame et dans le while ici
                }
                default -> throw new IllegalStateException("Unexpected game menu action: " + gameMenuAction);
            }
        }

        System.out.println(warGame.getWinner().getName() + " a gagné la partie !");
    }
}
