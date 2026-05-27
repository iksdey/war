package com.gengoul.war.domain;

import java.util.List;

public record RoundResult(Player winner, List<RoundStep> steps) {
}
