package com.gengoul.war.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundResolverTest {

    // Test cards
    private static final Card ACE_SPADES = new Card(Suit.SPADES, Rank.ACE);
    private static final Card KING_CLUBS = new Card(Suit.CLUBS, Rank.KING);
    private static final Card QUEEN_HEARTS = new Card(Suit.HEARTS, Rank.QUEEN);
    private static final Card NINE_DIAMONDS = new Card(Suit.DIAMONDS, Rank.NINE);
    private static final Card NINE_HEARTS = new Card(Suit.HEARTS, Rank.NINE);
    private static final Card NINE_CLUBS = new Card(Suit.CLUBS, Rank.NINE);
    private static final Card TWO_CLUBS = new Card(Suit.CLUBS, Rank.TWO);

    private RoundResolver roundResolver;

    @BeforeEach
    void setUp() {
        roundResolver = new RoundResolver();
    }

    @Test
    void givenDifferentCards_whenResolve_thenHighestCardWinsRound() {
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
