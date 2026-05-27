package com.gengoul.war.ui;

import com.gengoul.war.domain.Card;
import com.gengoul.war.domain.RoundResult;

import java.util.List;

public class ConsoleRenderer {

    public void displayCards(List<Card> cards) {
        for (Card card : cards) {
            System.out.print(card + "  ");
        }
        System.out.println();
    }

    public void displayRound(RoundResult round) {
        // TODO
        System.out.println("Le round en question :" + round);
    }
}
