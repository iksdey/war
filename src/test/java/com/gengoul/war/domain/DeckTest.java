package com.gengoul.war.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckTest {

    @Test
    void givenDeck_whenShuffle_thenSameNumberOfCards() {
        // given
        Deck deck = createFourCardDeck();

        // when

        // then

    }

    @Test
    void givenDeck_whenShuffle_thenSameCardsKept() {
        Deck deck = createFourCardDeck();

    }

    @Test
    void givenEmptyDeck_whenDraw_thenXXX() {

    }

    @Test
    void givenDeck_whenDraw_thenFirstCardIsRemoved() {

    }

    @Test
    void givenDeck_whenDraw_thenFirstCardIsDrawn() {

    }

    @Test
    void givenDeck_whenAddCardOnTop_thenCardIsOnTop() {

    }

    @Test
    void givenDeck_whenAddCardAtBottom_thenCardIsAtBottom() {

    }

    @Test
    void givenEmptyDeck_whenIsEmpty_thenReturnTrue() {
        // given
        Deck deck = createEmptyDeck();

        // when
        boolean isEmpty = deck.isEmpty();

        // then
        assertTrue(isEmpty);
    }

    @Test
    void givenNotEmptyDeck_whenIsEmpty_thenReturnFalse() {
        // use createOneCardDeck()
    }

    private Deck createFourCardDeck() {
        Deck deck = new Deck();
        deck.addOnTop(new Card(Suit.SPADES, Rank.ACE));
        deck.addOnTop(new Card(Suit.HEARTS, Rank.KING));
        deck.addOnTop(new Card(Suit.CLUBS, Rank.FIVE));
        deck.addOnTop(new Card(Suit.DIAMONDS, Rank.QUEEN));
        return deck;
    }

    private Deck createEmptyDeck() {
        return new Deck();
    }

    private Deck createOneCardDeck() {
        Deck deck = new Deck();
        deck.addOnTop(new Card(Suit.SPADES, Rank.ACE));
        return deck;
    }
}
