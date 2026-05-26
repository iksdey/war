package com.gengoul.war.domain;

import java.util.List;
import java.util.Random;

public class WarGame {

    private Deck cards;
    private List<Player> players;

    // TODO check if too many players ?
    public WarGame(Deck cards, int playerCount) {
        
    }

    public boolean isOver() {
        return new Random().nextBoolean();
    }

    // TODO : ça ou bien deux méthodes : void nextRound() et RoundResult getLastRoundResult() ?
    public RoundResult nextRound() {
        // TODO : le fait que cette classe modifie les états des Decks des joueurs, c'est grave ou bien c'est la seule manière ?
        // est-ce un effet de bord ? ça peut être fait différemment ?
        return null;
    }

    public void playUntilGameOver() {
        while (!isOver()) {
            nextRound();
        }
    }
}
