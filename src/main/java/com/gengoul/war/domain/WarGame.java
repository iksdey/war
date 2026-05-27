package com.gengoul.war.domain;

import java.util.ArrayList;
import java.util.List;

public class WarGame {

    private final List<Player> players = new ArrayList<>();
    private final RoundManager roundManager = new RoundManager();

    /**
     * @param deck le jeu de cartes à utiliser pour la partie
     */
    public WarGame(Deck deck, int playerCount) {
        for (int i=1; i<=playerCount; i++) {
            players.add(new Player("Joueur " + i)); // TODO final static String pour nom ?
        }

        // On distribue le même nombre de cartes à chaque joueur
        int cardsPerPlayer = deck.getCards().size() / playerCount;
        if (cardsPerPlayer < 1) {
            throw new IllegalArgumentException("Pas assez de cartes pour jouer !");
        }
        for (int i=0; i<cardsPerPlayer; i++) {
            for (Player player : players) {
                player.receiveCard(deck.drawCard());
            }
        }
    }

    // Package-private constructor for testing purposes
    // TODO à virer si possible
    WarGame(List<Player> players) {
        this.players.addAll(players);
    }

    public boolean isOver() {
        return players.stream()
                .filter(Player::hasCards)
                .count() <= 1;
    }

    public RoundResult nextRound() {
        return roundManager.resolveRound(players);
    }

    public void playUntilGameOver() {
        // TODO inverted bool
        while (!isOver()) {
            nextRound();
        }
    }

    // Expose une liste des joueurs immuable
    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
