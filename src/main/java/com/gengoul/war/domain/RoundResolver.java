package com.gengoul.war.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoundResolver {

    /**
     * @return le résultat du tour, son attribut player étant null s'il n'y a pas de gagnant
     */
    public RoundResult resolveRound(List<Player> players) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("At least two players are required to play a round");
        }
        if (players.stream().anyMatch(player -> !player.hasCards())) {
            throw new IllegalStateException("Every player must have at least one card");
        }

        List<Player> contenders = new ArrayList<>(players);
        List<RoundStep> steps = new ArrayList<>();
        List<Card> pot = new ArrayList<>();

        // Boucle principale d'un tour
        while (contenders.size() > 1) { // tant qu'il reste plus d'un joueur à l'issue de la bataille

            LinkedHashMap<Player, Card> playedCards = new LinkedHashMap<>();
            for (Player player : contenders) {
                Card card = player.drawCard();
                playedCards.put(player, card);
                pot.add(card);
            }
            steps.add(new RoundStep(playedCards));

            Card highestCard = findHighestCard(playedCards);

            // Joueurs ayant joué la carte ayant la plus grande valeur
            List<Player> tiedLeaders = findTiedLeaders(playedCards, highestCard);

            if (tiedLeaders.size() == 1) {
                contenders = tiedLeaders;
            } else {
                contenders = filterRemainingContenders(tiedLeaders);
            }
        }

        Player winner = contenders.size() == 1 ? contenders.getFirst() : null;
        if (winner != null) {
            winner.winCards(pot);
        }

        return new RoundResult(winner, steps);
    }

    private Card findHighestCard(Map<Player, Card> playedCards) {
        return playedCards.values().stream()
                .max(Card::compareTo)
                .orElseThrow();
    }

    private List<Player> findTiedLeaders(Map<Player, Card> playedCards, Card highestCard) {
        return playedCards.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(highestCard) == 0)
                .map(Map.Entry::getKey)
                .toList();
    }

    private List<Player> filterRemainingContenders(List<Player> tiedLeaders) {
        return tiedLeaders.stream()
                .filter(Player::hasCards)
                .toList();
    }
}
