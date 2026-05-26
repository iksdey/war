package com.gengoul.war.domain;

import java.util.List;

public class Player {

    private final String name;
    private final Deck deck;

    public Player(String name) {
        this.name = name;
        this.deck = new Deck();
    }

    // TODO s'en débarrasser si possible
    // Package-private constructor for testing purposes
    Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    /**
     * Le joueur prend une carte dans le cadre de la distribution
     */
    public void receiveCard(Card card) {
        deck.addOnTop(card);
    }

    public Card drawCard() {
        return deck.drawCard();
    }

    public void winCards(List<Card> cards) {
        cards.forEach(deck::addToBottom);
    }

    public boolean hasCards() {
        return !deck.isEmpty();
    }

    /**
     * Expose une liste immuable des cartes du joueur
     */
    public List<Card> getCards() {
        return deck.getCards();
    }

    public String getName() {
        return name;
    }
}
