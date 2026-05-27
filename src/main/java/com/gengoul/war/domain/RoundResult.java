package com.gengoul.war.domain;

import java.util.List;

/**
 * @param winner null in case of a tie
 * @param steps steps in the order they were played
 */
public record RoundResult(Player winner, List<RoundStep> steps) {
}
