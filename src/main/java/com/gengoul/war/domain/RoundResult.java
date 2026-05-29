package com.gengoul.war.domain;

import java.util.List;

/**
 * @param winner null en cas d'égalité
 * @param steps les étapes dans l'ordre dans lequel elles ont été jouées
 */
public record RoundResult(Player winner, List<RoundStep> steps) {
}
