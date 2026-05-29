package com.gengoul.war.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarGameIntegrationTest {

    private static final Card ACE_SPADES = new Card(Suit.SPADES, Rank.ACE);
    private static final Card KING_CLUBS = new Card(Suit.CLUBS, Rank.KING);
    private static final Card KING_HEARTS = new Card(Suit.HEARTS, Rank.KING);
    private static final Card QUEEN_HEARTS = new Card(Suit.HEARTS, Rank.QUEEN);
    private static final Card NINE_DIAMONDS = new Card(Suit.DIAMONDS, Rank.NINE);
    private static final Card NINE_HEARTS = new Card(Suit.HEARTS, Rank.NINE);

    @Test
    void givenTwoPlayerDeck_whenPlayUntilGameOver_thenWinnerIsReturned() {
        // Given
        Deck deck = createDeck(
                ACE_SPADES,
                KING_CLUBS
        );
        WarGame warGame = new WarGame(deck, 2);
        Player expectedWinner = warGame.getPlayers().getFirst();

        // When
        warGame.playUntilGameOver();

        // Then
        assertTrue(warGame.isOver());
        assertSame(expectedWinner, warGame.getWinner());
        assertContainsSameCards(
                List.of(ACE_SPADES, KING_CLUBS),
                expectedWinner.getCards()
        );
    }

    @Test
    void givenBattleDeck_whenNextRound_thenBattleIsResolvedAndPlayerDecksAreUpdated() {
        // Given
        Deck deck = createDeck(
                ACE_SPADES,
                QUEEN_HEARTS,
                KING_CLUBS,
                KING_HEARTS
        );
        WarGame warGame = new WarGame(deck, 2);
        Player expectedWinner = warGame.getPlayers().getFirst();
        Player expectedLoser = warGame.getPlayers().get(1);

        // When
        RoundResult result = warGame.nextRound();

        // Then
        assertSame(expectedWinner, result.winner());
        assertEquals(2, result.steps().size());
        assertContainsSameCards(
                List.of(KING_CLUBS, KING_HEARTS, ACE_SPADES, QUEEN_HEARTS),
                expectedWinner.getCards()
        );
        assertTrue(expectedLoser.getCards().isEmpty());
        assertTrue(warGame.isOver());
    }

    @Test
    void givenSameValuesDecks_whenPlayUntilGameOver_thenGameEndsWithoutWinner() {
        // Given
        Deck deck = createDeck(
                NINE_DIAMONDS,
                NINE_HEARTS,
                KING_CLUBS,
                KING_HEARTS
        );
        WarGame warGame = new WarGame(deck, 2);

        // When
        warGame.playUntilGameOver();

        // Then
        assertTrue(warGame.isOver());
        assertNull(warGame.getWinner());
        assertTrue(warGame.getPlayers().get(0).getCards().isEmpty());
        assertTrue(warGame.getPlayers().get(1).getCards().isEmpty());
    }

    /**
     * @return a Deck with the given Cards placed from top to bottom
     */
    private Deck createDeck(Card... cardsFromTopToBottom) {
        Deck deck = new Deck();
        for (int i = cardsFromTopToBottom.length - 1; i >= 0; i--) {
            deck.addOnTop(cardsFromTopToBottom[i]);
        }
        return deck;
    }

    private void assertContainsSameCards(List<Card> expected, List<Card> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }
}
