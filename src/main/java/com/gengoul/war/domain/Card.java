package com.gengoul.war.domain;

public class Card {

    private final Suit suit;
    private final Rank value;

    public Card(Suit suit, Rank value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.getSymbol() + suit.getSymbol();
    }
}
