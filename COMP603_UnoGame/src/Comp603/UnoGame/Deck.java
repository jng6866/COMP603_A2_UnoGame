/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author haydenwinterburn
 */
public class Deck {
    private List<Card> cards; 
    
    public Deck(){
        this.cards = createDeck();
        Collections.shuffle(cards);
    }

    private List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Card.Colour col : Card.Colour.values()) {
            if (col != Card.Colour.WILD) {
                for (int i = 0; i <= 9; i++) {
                    deck.add(new NumberCard(col, i)); // Number cards
                }
                deck.add(new ActionCard(col, ActionCard.ActionType.SKIP)); // Skip cards
                deck.add(new ActionCard(col, ActionCard.ActionType.REVERSE)); // Reverse cards
                deck.add(new ActionCard(col, ActionCard.ActionType.DRAW_TWO)); // Draw Two cards
            } else {
                deck.add(new WildCard(WildCard.WildType.WILD)); // Wild cards
                deck.add(new WildCard(WildCard.WildType.DRAW_FOUR)); // Draw Four cards
            }
        }
        return deck;
    }
      // Method to draw a card from the deck
    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty!");
        }
        return cards.remove(cards.size() - 1);
    }
    // Method to check if the deck is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Method to get the remaining number of cards in the deck
    public int getRemainingCards() {
        return cards.size();
    }
}
