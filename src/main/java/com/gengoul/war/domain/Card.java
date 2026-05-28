package com.gengoul.war.domain;

public record Card(Suit suit, Rank rank) implements Comparable<Card> {

    @Override
    public String toString() {
        return rank.getSymbol() + suit.getSymbol();
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(
                this.rank.getValue(),
                other.rank.getValue()
        );
    }
}
