package com.gengoul.war.domain;

import java.util.LinkedHashMap;

// map with ordered keys
public record RoundStep(LinkedHashMap<Player, Card> playedCards) {
}
