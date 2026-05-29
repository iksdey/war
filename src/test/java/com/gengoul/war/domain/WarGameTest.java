package com.gengoul.war.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarGameTest {

    // Test cards
    private static final Card ACE_SPADES = new Card(Suit.SPADES, Rank.ACE);
    private static final Card QUEEN_HEARTS = new Card(Suit.HEARTS, Rank.QUEEN);
    private static final Card NINE_DIAMONDS = new Card(Suit.DIAMONDS, Rank.NINE);
    private static final Card NINE_HEARTS = new Card(Suit.HEARTS, Rank.NINE);
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
        // Given
        Deck deck = createDeck(
                ACE_SPADES,
                QUEEN_HEARTS,
                NINE_DIAMONDS,
                NINE_HEARTS,
                TWO_CLUBS
        );

        // When
        WarGame warGame = new WarGame(deck, 3);

        // Then
        List<Player> players = warGame.getPlayers();

        assertEquals(List.of(TWO_CLUBS), players.get(0).getCards());
        assertEquals(List.of(NINE_HEARTS), players.get(1).getCards());
        assertEquals(List.of(NINE_DIAMONDS), players.get(2).getCards());
    }

    @Test
    void givenOnlyOnePlayerHasCardsLeft_whenCallingIsOver_thenReturnsTrue() {
        // Given
        Player p1 = createPlayer("J1", ACE_SPADES);
        Player p2 = createPlayer("J2");
        Player p3 = createPlayer("J3");
        WarGame warGame = new WarGame(List.of(p1, p2, p3));

        // When
        boolean isOver = warGame.isOver();

        // Then
        assertTrue(isOver);
    }

    @Test
    void givenSeveralPlayersHaveCardsLeft_whenCallingIsOver_thenReturnsFalse() {
        // Given
        Player p1 = createPlayer("J1", ACE_SPADES);
        Player p2 = createPlayer("J2", QUEEN_HEARTS);
        Player p3 = createPlayer("J3");
        WarGame warGame = new WarGame(List.of(p1, p2, p3));

        // When
        boolean isOver = warGame.isOver();

        // Then
        assertFalse(isOver);
    }

    // Helpers

    private Deck createOneCardDeck() {
        Deck deck = new Deck();
        deck.addOnTop(new Card(Suit.SPADES, Rank.ACE));
        return deck;
    }

    private Deck createDeck(Card... cards) {
        Deck deck = new Deck();
        for (Card card : cards) {
            deck.addOnTop(card);
        }
        return deck;
    }

    /**
     * @return a Player with the given Cards placed in their Deck from top to bottom
     */
    private Player createPlayer(String name, Card... cards) {
        Player player = new Player(name);
        for (int i = cards.length - 1; i >= 0; i--) {
            player.receiveCard(cards[i]);
        }
        return player;
    }
}
