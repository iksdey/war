package com.gengoul.war.domain;

import java.util.*;

public class Deck {

    /**
     * Lorsque les cartes sont face cachée, la première carte se trouve en tête de la ArrayDeque
     */
    private final Deque<Card> cards = new ArrayDeque<>();

    // TODO coms style javadoc ?
    // Expose une liste immuable des cartes, TODO dans l'ordre de tirage (est-ce vrai ?)
    // TODO vraiment utile sachant que le Renderer peut utiliser les autres méthodes ?
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    // TODO size method ?

    /**
     * @return la première carte du Deck, null si le deck est vide
     */
    public Card drawCard() {
        return cards.pollFirst();
    }

    // TODO gérer si la carte est déjà dans le Deck ? Pareil pour les autres méthodes ?
    public void addOnTop(Card card) {
        cards.addFirst(card);
    }

    public void addToBottom(Card card) {
        cards.addLast(card);
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
                // TODO ? methode existante ou accès direct à cards ?
//                fullDeck.addCard(new Card(suit, rank));
                // TODO add ou une méthode de Deque ?
                fullDeck.cards.add(new Card(suit, rank));
            }
        }
        return fullDeck;
    }
}
