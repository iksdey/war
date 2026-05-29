package com.gengoul.war.domain;

import java.util.Collections;
import java.util.List;

public class Player {

    private final String name;
    private final Deck deck;

    public Player(String name) {
        this.name = name;
        this.deck = new Deck();
    }

    /**
     * Le joueur reçoit une carte dans le cadre de la distribution
     */
    public void receiveCard(Card card) {
        deck.addOnTop(card);
    }

    public Card drawCard() {
        return deck.drawCard();
    }

    public void winCards(List<Card> cards) {
        Collections.shuffle(cards);
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
