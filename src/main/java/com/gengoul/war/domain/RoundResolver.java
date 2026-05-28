package com.gengoul.war.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoundResolver {

    /**
     * TODO
     * @param players
     * @return the result of the round, its player attribute being null if it's a tie
     */
    public RoundResult resolveRound(List<Player> players) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("At least two players are required to play a round");
        }
        if (players.stream().anyMatch(player -> !player.hasCards())) {
            throw new IllegalStateException("Every player must have at least one card");
        }

        List<Player> remainingPlayers = new ArrayList<>(players);
        List<RoundStep> steps = new ArrayList<>();

        // Round main loop
        while (remainingPlayers.size() > 1) { // tant qu'il reste plus d'un joueur à l'issue de la bataille

            LinkedHashMap<Player, Card> playedCards = new LinkedHashMap<>();
            for (Player player : remainingPlayers) {
                Card card = player.drawCard();
                playedCards.put(player, card);
            }
            steps.add(new RoundStep(playedCards));

            Card highestCard = playedCards.values().stream()
                    .max(Card::compareTo)
                    .orElseThrow();

            List<Player> highestPlayers = playedCards.entrySet().stream()
                    .filter(entry -> entry.getValue().compareTo(highestCard) == 0)
                    .map(Map.Entry::getKey)
                    .toList();

            if (highestPlayers.size() == 1) {
                remainingPlayers = highestPlayers;
            } else {
                remainingPlayers = highestPlayers.stream()
                        .filter(Player::hasCards)
                        .toList();
            }
        }

        Player winner = remainingPlayers.size() == 1 ? remainingPlayers.getFirst() : null;

        return new RoundResult(winner, steps);
    }
}
