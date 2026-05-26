package com.gengoul.war.domain;

import java.util.List;

public record RoundResult(String winner, List<BattleStep> steps) {
}
