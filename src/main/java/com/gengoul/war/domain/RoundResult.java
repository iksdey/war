package com.gengoul.war.domain;

import java.util.List;

// TODO record
public class RoundResult {

    private final String winner;
    private final List<BattleStep> steps;

    public RoundResult(String winner, List<BattleStep> steps) {
        this.winner = winner;
        this.steps = steps;
    }
}
