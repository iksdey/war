package com.gengoul.war.domain;

public record Card(Suit suit, Rank value) {

    @Override
    public String toString() {
        return value.getSymbol() + suit.getSymbol();
    }
}
