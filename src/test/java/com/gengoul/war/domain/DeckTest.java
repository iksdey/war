package com.gengoul.war.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void givenDeck_whenShuffle_thenStillContainsSameCards() {
        // Given
        Deck deck = createFourCardDeck();
        List<Card> cardsBeforeShuffle = deck.getCards();

        // When
        deck.shuffle();

        // Then
        List<Card> cardsAfterShuffle = deck.getCards();

        assertEquals(cardsBeforeShuffle.size(), cardsAfterShuffle.size());
        assertTrue(cardsAfterShuffle.containsAll(cardsBeforeShuffle));
    }

    @Test
    void givenDeck_whenDraw_thenFirstCardIsDrawn() {
        // Given
        Deck deck = createFourCardDeck();

        // When
        Card drawnCard = deck.drawCard();

        // Then
        // TODO aller directement chercher l'élément un dans le deck retourné par createFourCardDeck() ?
        //  ce test peut casser facilement si on change createFourCardDeck()...
        assertEquals(new Card(Suit.DIAMONDS, Rank.QUEEN), drawnCard);
        assertFalse(deck.getCards().contains(drawnCard));
    }

    @Test
    void givenDeck_whenAddCardOnTop_thenCardIsOnTop() {
        // Given
        // TODO use fourCardDeck ?
        Deck deck = createOneCardDeck();
        Card addedCard = new Card(Suit.HEARTS, Rank.KING);

        // When
        deck.addOnTop(addedCard);

        // Then
        Card drawnCard = deck.drawCard();

        assertEquals(addedCard, drawnCard);
    }

    @Test
    void givenDeck_whenAddCardToBottom_thenCardIsAtBottom() {
        // Given
        // TODO use fourCardDeck ?
        Deck deck = createOneCardDeck();
        Card addedCard = new Card(Suit.HEARTS, Rank.KING);

        // When
        deck.addToBottom(addedCard);

        // Then
        // TODO ok ?
        deck.drawCard();
        Card drawnCard = deck.drawCard();

        assertEquals(addedCard, drawnCard);
    }

    @Test
    void givenEmptyDeck_whenDraw_thenReturnNull() {
        // Given
        Deck deck = createEmptyDeck();

        // When
        Card drawnCard = deck.drawCard();

        // Then
        assertNull(drawnCard);
    }

    @Test
    void givenEmptyDeck_whenIsEmpty_thenReturnTrue() {
        // Given
        Deck deck = createEmptyDeck();

        // When
        boolean isEmpty = deck.isEmpty();

        // Then
        assertTrue(isEmpty);
    }

    @Test
    void givenNotEmptyDeck_whenIsEmpty_thenReturnFalse() {
        // Given
        Deck deck = createOneCardDeck();

        // When
        boolean isEmpty = deck.isEmpty();

        // Then
        assertFalse(isEmpty);
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
