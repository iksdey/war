package com.gengoul.war.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarGameTest {

    // Test cards
    private static final Card ACE_SPADES = new Card(Suit.SPADES, Rank.ACE);
    private static final Card QUEEN_HEARTS = new Card(Suit.HEARTS, Rank.QUEEN);
    private static final Card NINE_DIAMONDS = new Card(Suit.DIAMONDS, Rank.NINE);
    private static final Card NINE_HEARTS = new Card(Suit.HEARTS, Rank.NINE);
    private static final Card NINE_CLUBS = new Card(Suit.CLUBS, Rank.NINE);
    private static final Card TWO_CLUBS = new Card(Suit.CLUBS, Rank.TWO);

    @Test
    void givenNotEnoughCards_whenConstructor_thenThrowsIllegalArgumentException() {
        // Given
        int playerCount = 2;
        Deck deck = createOneCardDeck();

        assertThrows(
                IllegalArgumentException.class, // Then
                () -> new WarGame(deck, playerCount) // When
        );
    }

    @Test
    void givenDeck_whenConstructor_thenCardsSequentiallyDistributed() {

    }

    @Test
    void givenAPlayersDeckIsEmpty_whenCallingIsOver_returnsTrue() {

    }

    @Test
    void givenNoPlayersDeckIsEmpty_whenCallingIsOver_returnsFalse() {

    }

    /*
     * TODO tests de la méthode nextRound (on vérifie dans chaque test l'object RoundResult renvoyé + l'état des Deck des joueurs et autres) :
     *  - un joueur gagne le tour
     *  - une bataille
     *
     */

    // Helpers

    private Deck createDeck(Card... cards) {
        Deck deck = new Deck();
        for (Card card : cards) {
            deck.addOnTop(card);
        }
        return deck;
    }

    // TODO remplacer par createDeck(Card...) pour plus de clarté ?
    private Deck createOneCardDeck() {
        Deck deck = new Deck();
        deck.addOnTop(new Card(Suit.SPADES, Rank.ACE));
        return deck;
    }
}
