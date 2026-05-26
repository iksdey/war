package com.gengoul.war.app;

import com.gengoul.war.domain.*;
import com.gengoul.war.ui.ConsoleRenderer;
import com.gengoul.war.ui.GameMenuAction;
import com.gengoul.war.ui.MainMenuAction;
import com.gengoul.war.ui.Menu;

import java.util.Scanner;

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
                case START_GAME -> startGame(fullDeck);
                default -> throw new IllegalStateException("Unexpected main menu action: " + mainMenuAction);
            }
        }
    }

    private static void startGame(Deck deck) {
        int playerCount = getUserEntry(2,4);

        WarGame warGame = new WarGame(deck, playerCount);
        Menu menu = new Menu();

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

    // TODO duplicated in Menu
    private static int getUserEntry(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int entry = scanner.nextInt();
                if (entry >= min && entry <= max) {
                    return entry;
                }
            } else {
                scanner.next();
            }
            System.out.println("Entrée invalide.");
        }
    }
}
