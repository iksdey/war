package com.gengoul.war.domain;

import java.util.LinkedHashMap;

/**
 * @param playedCards associe chaque joueur à la carte qu'il a jouée
 */
public record RoundStep(LinkedHashMap<Player, Card> playedCards) {
}
