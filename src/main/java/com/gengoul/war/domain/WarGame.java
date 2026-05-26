package com.gengoul.war.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WarGame {

    private final Deck cards;
    private final List<Player> players = new ArrayList<>();

    // TODO check here if too many players ?
    public WarGame(Deck cards, int playerCount) {
        this.cards = cards;
        for (int i=1; i<=playerCount; i++) {
            players.add(new Player("Joueur " + i));
        }
        // TODO distribution des cartes : on fait en sorte que tout le monde ait le même
        //  nombre de carte, on discard ce qui est en trop
    }

    // Expose une liste immuable des joueurs
    // TODO copie immuable/encapsulation utile ?
    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public boolean isOver() {
        return new Random().nextBoolean();
    }

    // TODO : ça ou bien deux méthodes : void nextRound() et RoundResult getLastRoundResult() ?
    public RoundResult nextRound() {
        return new RoundResult(
                players.stream().findAny().orElseThrow(() -> new IllegalStateException("No players")).getName(),
                List.of()
        );
    }

    public void playUntilGameOver() {
        while (!isOver()) {
            nextRound();
        }
    }

    public Player getWinner() {
        return players.stream().findAny().get();
    }
}
