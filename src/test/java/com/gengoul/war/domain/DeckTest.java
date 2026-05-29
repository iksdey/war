package com.gengoul.war.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    // Test cards
    private static final Card ACE_SPADES = new Card(Suit.SPADES, Rank.ACE);
    private static final Card KING_HEARTS = new Card(Suit.HEARTS, Rank.KING);
    private static final Card FIVE_CLUBS = new Card(Suit.CLUBS, Rank.FIVE);

    @Test
    void givenDeck_whenShuffle_thenStillContainsSameCards() {
        // Given
        Deck deck = createDeck(
                ACE_SPADES,
                KING_HEARTS,
                FIVE_CLUBS
        );
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
        Deck deck = createDeck(
                ACE_SPADES,
                KING_HEARTS
        );

        // When
        Card drawnCard = deck.drawCard();

        // Then
        assertSame(ACE_SPADES, drawnCard);
        assertFalse(deck.getCards().contains(drawnCard));
    }

    @Test
    void givenDeck_whenAddCardOnTop_thenCardIsOnTop() {
        // Given
        // Testing addOnTop with a Deck set up using addOnTop is acceptable
        // as it is used to create a one Card Deck
        Deck deck = createOneCardDeck();

        // When
        deck.addOnTop(KING_HEARTS);

        // Then
        Card drawnCard = deck.drawCard();

        assertSame(KING_HEARTS, drawnCard);
    }

    @Test
    void givenDeck_whenAddCardToBottom_thenCardIsAtBottom() {
        // Given
        Deck deck = createOneCardDeck();

        // When
        deck.addToBottom(KING_HEARTS);

        // Then
        deck.drawCard(); // draw first card, the one from createOneCardDeck()
        Card drawnCard = deck.drawCard(); // draw the card addedToBottom

        assertSame(KING_HEARTS, drawnCard);
    }

    @Test
    void givenEmptyDeck_whenDraw_thenReturnsNull() {
        // Given
        Deck deck = createEmptyDeck();

        // When
        Card drawnCard = deck.drawCard();

        // Then
        assertNull(drawnCard);
    }

    @Test
    void givenEmptyDeck_whenIsEmpty_thenReturnsTrue() {
        // Given
        Deck deck = createEmptyDeck();

        // When
        boolean isEmpty = deck.isEmpty();

        // Then
        assertTrue(isEmpty);
    }

    @Test
    void givenNotEmptyDeck_whenIsEmpty_thenReturnsFalse() {
        // Given
        Deck deck = createOneCardDeck();

        // When
        boolean isEmpty = deck.isEmpty();

        // Then
        assertFalse(isEmpty);
    }

    // Helper methods

    private Deck createEmptyDeck() {
        return new Deck();
    }

    // We assume that Deck::addOnTop method works as intended

    private Deck createOneCardDeck() {
        Deck deck = new Deck();
        deck.addOnTop(new Card(Suit.DIAMONDS, Rank.TWO));
        return deck;
    }

    private Deck createDeck(Card... cardsFromTopToBottom) {
        Deck deck = new Deck();
        for (int i = cardsFromTopToBottom.length - 1; i >= 0; i--) {
            deck.addOnTop(cardsFromTopToBottom[i]);
        }
        return deck;
    }
}
