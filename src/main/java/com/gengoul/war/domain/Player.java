package com.gengoul.war.domain;

import java.util.List;

public class Player {

    private final String name;
    private final Deck deck;

    public Player(String name) {
        this.name = name;
        this.deck = new Deck();
    }

    // Expose une liste immuable des cartes du joueur
    // TODO : encapsulation utile ?
    public List<Card> getCards() {
        return deck.getCards();
    }

    public String getName() {
        return name;
    }
}
