package com.gengoul.war.ui;

import com.gengoul.war.domain.Card;
import com.gengoul.war.domain.Player;
import com.gengoul.war.domain.RoundResult;
import com.gengoul.war.domain.RoundStep;

import java.util.List;
import java.util.Map;

public class ConsoleRenderer {

    private static final int MAX_CHARACTERS_PER_LINE = 100;
    private static final String CARD_SEPARATOR = "  ";

    public void displayCards(List<Card> cards) {
        int charCount = 0;
        for (Card card : cards) {
            if (charCount > MAX_CHARACTERS_PER_LINE) {
                System.out.println();
                charCount = 0;
            }
            charCount = charCount + CARD_SEPARATOR.length() + 2;
            System.out.print(card + CARD_SEPARATOR);
        }
        System.out.println();
        System.out.println();
    }

    public void displayPlayersDecks(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName());
            displayCards(player.getCards());
            System.out.println();
        }
    }

    public void displayRound(RoundResult round) {
        List<RoundStep> steps = round.steps();
        for (int i=0; i<steps.size(); i++) {
            if (i > 0) {
                System.out.println("BATAILLE !");
            }
            for (Map.Entry<Player, Card> entry : steps.get(i).playedCards().entrySet()) {
                System.out.println(entry.getKey().getName() + " -> " + entry.getValue());
            }
        }

        Player winner = round.winner();
        if (winner != null) {
            System.out.println(round.winner().getName() + " remporte le tour");
        } else {
            System.out.println("Personne n'a gagné le tour");
        }

        System.out.println();
    }

    public void displayMainMenu() {
        System.out.println("""
            ************* Menu principal *************
            1- Afficher les cartes
            2- Mélanger les cartes
            3- Commencer la partie
            ******************************************
            Votre choix ?
            """);
    }

    public void displayGameMenu() {
        System.out.println("""
            **************** Menu jeu ****************
            1- Main suivante
            2- Afficher la distribution des cartes par joueur
            3- Automatiser les mains jusqu’à la fin de la partie
            ******************************************
            Votre choix ?
            """);
    }

    public void askNumberOfPlayers(int min, int max) {
        System.out.println("Combien de joueurs (entre " + min + " et " + max + ") ?");
    }

    public void displayWinner(Player player) {
        if (player == null) {
            System.out.println("Partie nulle !");
        } else {
            System.out.println(player.getName() + " remporte la partie !");
        }
    }
}
