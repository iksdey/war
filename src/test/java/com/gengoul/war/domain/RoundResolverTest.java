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
        // TODO check RoundResult contents
        assertSame(p1, result.winner());
    }

    @Test
    void givenDifferentCards_whenResolveRound_thenWinnerCollectsPlayedCards() {
        /* TODO :
            use three players for this test case
            check that won cards are at the bottom of the winner's deck and in the right order
        */
        // Given
        Player p1 = createPlayer(
                "J1",
                ACE_SPADES,
                NINE_DIAMONDS
        );
        Player p2 = createPlayer(
                "J2",
                KING_CLUBS
        );
        Player p3 = createPlayer(
                "J3",
                QUEEN_HEARTS
        );
        List<Player> players = List.of(p1, p2, p3);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        assertSame(p1, result.winner());
        assertEquals(
                List.of(NINE_DIAMONDS, ACE_SPADES, KING_CLUBS, QUEEN_HEARTS),
                p1.getCards()
        );
        assertTrue(p2.getCards().isEmpty());
        assertTrue(p3.getCards().isEmpty());
    }

    @Test
    void givenTieWithRemainingCards_whenResolveRound_thenBattleContinues() {
        /* TODO
            Égalité simple suivie d’un départage
         */
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
        // TODO really check that RoundResult CONTENT is what we excpect
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
        // TODO check RoundResult contents
        assertNull(result.winner());
    }

    @Test
    void givenTieWithoutRemainingCards_whenResolveRound_thenPlayedCardsAreDiscarded() {
        /* TODO
            Vérifie qu’aucun joueur ne récupère le pot.
            on peut mettre quatre joueurs, deux qui perdent au premier step, les deux autres qui finissent leur deck au 2eme step par une égalité
            puis on vérifie que les decks des deux joueurs suivants sont bien leur précédents deck amputés de la carte jouée au premier step
         */
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
        assertEquals(2, result.steps().size());
        assertTrue(p1.getCards().isEmpty());
        assertTrue(p2.getCards().isEmpty());
        assertTrue(p3.getCards().isEmpty());
        assertTrue(p4.getCards().isEmpty());
    }

    @Test
    void givenBattle_whenResolveRound_thenRoundStepsContainEveryBattleStep() {
        /* TODO
            Vérifie la construction correcte des RoundStep, on peut faire un cas avec deux steps et deux joueurs
         */
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

        // TODO unreadable ?!
        assertEquals(2, steps.size());
        assertEquals(2, steps.get(0).playedCards().size());
        assertEquals(KING_CLUBS, steps.get(0).playedCards().get(p1));
        assertEquals(KING_HEARTS, steps.get(0).playedCards().get(p2));
        assertEquals(2, steps.get(1).playedCards().size());
        assertEquals(ACE_SPADES, steps.get(1).playedCards().get(p1));
        assertEquals(QUEEN_HEARTS, steps.get(1).playedCards().get(p2));
    }

    @Test
    void givenThreePlayers_whenResolveRound_thenOnlyTiedLeadersContinueBattle() {
        /* TODO
            on vérifie qu'un des joueurs sort bien de la bataille à l'issue du premier step et que les autres continuent un step
         */
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
                JACK_SPADES
        );
        List<Player> players = List.of(p1, p2, p3);

        // When
        RoundResult result = roundResolver.resolveRound(players);

        // Then
        RoundStep battleStep = result.steps().get(1);

        assertSame(p1, result.winner());
        assertEquals(2, result.steps().size());
        // TODO unreadable
        assertTrue(battleStep.playedCards().containsKey(p1));
        assertTrue(battleStep.playedCards().containsKey(p2));
        assertFalse(battleStep.playedCards().containsKey(p3));
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
