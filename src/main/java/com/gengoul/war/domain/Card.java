package com.gengoul.war.domain;

import java.util.Objects;

// TODO record ?
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }
}
