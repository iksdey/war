package com.gengoul.war.domain;

import java.util.*;

public class Deck {

    /**
     * Lorsque les cartes sont face cachée, la première carte se trouve en tête de la Deque
     */
    private final Deque<Card> cards = new ArrayDeque<>();

    // Expose une liste immuable des cartes
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    // Tirer une carte sur le deck
    // TODO retourner Optional ? Throw une exception si pas de carte ? tester isEmpty avant ?
    public Card drawCard() {
        return cards.pollFirst();
    }

    // TODO gérer si la carte est déjà dans le Deck ? Pareil pour les autres méthodes ?
    public void addOnTop() {

    }

    public void addAtBottom() {

    }

    // Mélanger le deck
    public void shuffle() {
        List<Card> tmpCards = new ArrayList<>(cards);
        Collections.shuffle(tmpCards);
        cards.clear();
        cards.addAll(tmpCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Crée un paquet classique de 52 cartes
    public static Deck createFullDeck() {
        Deck fullDeck = new Deck();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                // TODO ? method ou accès direct à cards ?
//                fullDeck.addCard(new Card(suit, rank));
                fullDeck.cards.add(new Card(suit, rank));
            }
        }
        return fullDeck;
    }
}
