package com.gengoul.war.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundResolverTest {

    // Test cards
    private static final Card ACE_SPADES = new Card(Suit.SPADES, Rank.ACE);
    private static final Card KING_CLUBS = new Card(Suit.CLUBS, Rank.KING);
    private static final Card KING_HEARTS = new Card(Suit.HEARTS, Rank.KING);
    private static final Card QUEEN_HEARTS = new Card(Suit.HEARTS, Rank.QUEEN);
    private static final Card JACK_SPADES = new Card(Suit.SPADES, Rank.JACK);
    private static final Card NINE_DIAMONDS = new Card(Suit.DIAMONDS, Rank.NINE);
    private static final Card NINE_HEARTS = new Card(Suit.HEARTS, Rank.NINE);


    private RoundResolver roundResolver;

    @BeforeEach
    void setUp() {
        roundResolver = new RoundResolver();
    }

    @Test
    void givenLessThanTwoPlayers_whenResolveRound_thenThrowIllegalArgumentException() {
        // Given
        Player p1 = createPlayer(
                "J1",
                ACE_SPADES
        );
        List<Player> players = List.of(p1);

        assertThrows(
                IllegalArgumentException.class, // Then
                () -> roundResolver.resolveRound(players) // When
        );
    }

    @Test
    void givenPlayerWithoutCards_whenResolveRound_thenThrowIllegalStateException() {
        // Given
        Player p1 = createPlayer(
                "J1",
                ACE_SPADES
        );
        Player p2 = new Player("J2");
        List<Player> players = List.of(p1, p2);

        assertThrows(
                IllegalStateException.class, // Then
                () -> roundResolver.resolveRound(players) // When
        );
    }

    @Test
    void givenDifferentCards_whenResolveRound_thenHighestCardWinsRound() {
        // Given
        Player p1 = createPlayer(
                "J1",
                ACE_SPADES,
                NINE_DIAMONDS
        );

        Player p2 = createPlayer(
                "J2",
                QUEEN_HEARTS,
                KING_CLUBS
        );
        List<Player> players = List.of(p1, p2);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        assertSame(p1, result.winner());
    }

    @Test
    void givenTieWithRemainingCards_whenResolveRound_thenBattleContinues() {
        // Given
        Player p1 = createPlayer(
                "J1",
                KING_CLUBS,
                ACE_SPADES
        );
        Player p2 = createPlayer(
                "J2",
                KING_HEARTS,
                QUEEN_HEARTS
        );
        List<Player> players = List.of(p1, p2);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        assertSame(p1, result.winner());
        assertEquals(2, result.steps().size());
    }

    @Test
    void givenTieWithoutRemainingCards_whenResolveRound_thenRoundEndsWithoutWinner() {
        // Given
        Player p1 = createPlayer(
                "J1",
                KING_CLUBS,
                NINE_DIAMONDS
        );

        Player p2 = createPlayer(
                "J2",
                KING_HEARTS,
                NINE_HEARTS
        );
        List<Player> players = List.of(p1, p2);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        assertNull(result.winner());
    }

    @Test
    void givenTie_whenResolveRound_thenPlayedCardsAreDiscarded() {
        // Given
        Player p1 = createPlayer(
                "J1",
                QUEEN_HEARTS
        );
        Player p2 = createPlayer(
                "J2",
                JACK_SPADES
        );
        Player p3 = createPlayer(
                "J3",
                KING_CLUBS,
                NINE_DIAMONDS
        );
        Player p4 = createPlayer(
                "J4",
                KING_HEARTS,
                NINE_HEARTS
        );
        List<Player> players = List.of(p1, p2, p3, p4);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        assertNull(result.winner());
        assertTrue(p1.getCards().isEmpty());
        assertTrue(p2.getCards().isEmpty());
        assertTrue(p3.getCards().isEmpty());
        assertTrue(p4.getCards().isEmpty());
    }

    @Test
    void givenBattle_whenResolveRound_thenRoundStepsContainEveryBattleStep() {
        // Given
        Player p1 = createPlayer(
                "J1",
                KING_CLUBS,
                ACE_SPADES
        );
        Player p2 = createPlayer(
                "J2",
                KING_HEARTS,
                QUEEN_HEARTS
        );
        List<Player> players = List.of(p1, p2);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        List<RoundStep> steps = result.steps();
        RoundStep firstStep = steps.get(0);
        RoundStep secondStep = steps.get(1);

        assertEquals(2, steps.size());

        assertEquals(2, firstStep.playedCards().size());
        assertEquals(KING_CLUBS, firstStep.playedCards().get(p1));
        assertEquals(KING_HEARTS, firstStep.playedCards().get(p2));

        assertEquals(2, secondStep.playedCards().size());
        assertEquals(ACE_SPADES, secondStep.playedCards().get(p1));
        assertEquals(QUEEN_HEARTS, secondStep.playedCards().get(p2));
    }

    @Test
    void givenThreePlayers_whenResolveRound_thenOnlyTiedLeadersContinueBattle() {
        // Given
        Player p1 = createPlayer(
                "J1",
                KING_CLUBS,
                ACE_SPADES
        );
        Player p2 = createPlayer(
                "J2",
                KING_HEARTS,
                QUEEN_HEARTS
        );
        Player p3 = createPlayer(
                "J3",
                JACK_SPADES,
                NINE_HEARTS
        );
        List<Player> players = List.of(p1, p2, p3);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        RoundStep secondStep = result.steps().get(1);
        assertTrue(secondStep.playedCards().containsKey(p1));
        assertTrue(secondStep.playedCards().containsKey(p2));
        assertFalse(secondStep.playedCards().containsKey(p3));
    }

    @Test
    void givenBattle_whenResolveRound_thenWinnerCollectsAllPlayedCards() {
        // Given
        Player p1 = createPlayer(
                "J1",
                KING_CLUBS,
                ACE_SPADES
        );
        Player p2 = createPlayer(
                "J2",
                KING_HEARTS,
                QUEEN_HEARTS
        );
        List<Player> players = List.of(p1, p2);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        assertSame(p1, result.winner());
        // étant donné que les cartes gagnées sont mélangées...
        List<Card> expected = List.of(KING_CLUBS, KING_HEARTS, ACE_SPADES, QUEEN_HEARTS);
        List<Card> actual = p1.getCards();
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
        assertEquals(expected.size(), actual.size());
        assertTrue(p2.getCards().isEmpty());
    }

    @Test
    void givenTieAndOnlyOneLeaderHasRemainingCards_whenResolveRound_thenRemainingLeaderWinsRound() {
        // Given
        Player p1 = createPlayer(
                "J1",
                KING_CLUBS
        );
        Player p2 = createPlayer(
                "J2",
                KING_HEARTS,
                QUEEN_HEARTS
        );
        List<Player> players = List.of(p1, p2);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        assertSame(p2, result.winner());
    }

    // Helper methods

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
