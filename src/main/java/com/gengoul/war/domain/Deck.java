package com.gengoul.war.domain;

import java.util.*;

public class Deck {

    /**
     * Lorsque les cartes sont face cachée, la première carte se trouve en tête de la ArrayDeque
     */
    private final Deque<Card> cards = new ArrayDeque<>();

    /**
     * @return une liste immuable des cartes du deck
     */
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    /**
     * @return la première carte du Deck, null si le deck est vide
     */
    public Card drawCard() {
        return cards.pollFirst();
    }

    public void addOnTop(Card card) {
        cards.addFirst(card);
    }

    public void addToBottom(Card card) {
        cards.addLast(card);
    }

    public void shuffle() {
        List<Card> tmpCards = new ArrayList<>(cards);
        Collections.shuffle(tmpCards);
        cards.clear();
        cards.addAll(tmpCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * @return un paquet classique de 52 cartes
     */
    public static Deck createFullDeck() {
        Deck fullDeck = new Deck();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                fullDeck.cards.add(new Card(suit, rank));
            }
        }
        return fullDeck;
    }
}
