/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haydenwinterburn
 */
public class DiscardPile {
    private List<Card> pile;

    public DiscardPile() {
        this.pile = new ArrayList<>(); // Inilialize pile as blank list
    }

    // Method to add a card to the discard pile
    public void addCard(Card card) {
        pile.add(card);
    }

    // Get the top card of the discard pile
    public Card getTopCard() {
        if (pile.isEmpty()) {
            throw new IllegalStateException("The discard pile is empty!");
        }
        return pile.get(pile.size() - 1); //Return the top card of discard pile
    }

    
    public List<Card> reset() {
        List<Card> cards = new ArrayList<>(pile);
        pile.clear();
        return cards;
    }

    // Check if the discard pile is empty
    public boolean isEmpty() {
        return pile.isEmpty();
    }
}
