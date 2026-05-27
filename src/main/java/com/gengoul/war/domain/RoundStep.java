package com.gengoul.war.domain;

import java.util.LinkedHashMap;

/**
 * @param playedCards maps each Player to the Card they played
 */
public record RoundStep(LinkedHashMap<Player, Card> playedCards) {
}
