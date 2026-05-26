package com.gengoul.war.ui;

import com.gengoul.war.domain.Card;
import com.gengoul.war.domain.RoundResult;

import java.util.List;

public class ConsoleRenderer {

    /* TODO
    *   - afficher un Deck
    *   - afficher un tour
    *   - afficher une bataille
    * */

    public void displayCards(List<Card> cards) {
        for (Card card : cards) {
            System.out.print(card + "  ");
        }
        System.out.println();
    }

    public void displayRound(RoundResult round) {
        System.out.println("Le round en question :" + round);
    }
}
