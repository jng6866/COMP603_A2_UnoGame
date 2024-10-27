/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;


/**
 * @author: haydenwinterburn mustafakamish
 */

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Deck {

    // Constant for the standard UNO deck size
    private static final int DECK_SIZE = 108;

    // Array to hold the cards in the deck
    private Card[] cards;

    // Counter for the number of cards currently in the deck
    private int cardsInDeck;

    // Constructor initialises a new deck with all standard UNO cards
    public Deck() {
        cards = new Card[DECK_SIZE];
        reset();
    }

    // Resets the deck with all standard UNO cards
    // Adds all numbered cards, action cards, and wild cards to the deck
    public void reset() {
        Card.Colour[] colours = Card.Colour.values();
        cardsInDeck = 0;

        // Loop through all colours except wild (last entry)
        for (int i = 0; i < colours.length - 1; i++) {
            addNumberedCards(colours[i]);
            addActionCards(colours[i]);
        }
        addWildCards();  // Adds wild and wild draw four cards
    }

    // Adds numbered cards (0-9) for the given colour
    // Each colour gets one "0" card and two of each card from "1" to "9"
    private void addNumberedCards(Card.Colour colour) {
        cards[cardsInDeck++] = new Card(colour, Card.Value.getValue(0)); // Single zero card
        for (int j = 1; j < 10; j++) {
            cards[cardsInDeck++] = new Card(colour, Card.Value.getValue(j)); // Two of each (1-9)
            cards[cardsInDeck++] = new Card(colour, Card.Value.getValue(j));
        }
    }

    // Adds action cards (DrawTwo, Skip, Reverse) for the specified colour
    // Each action card appears twice for each colour
    private void addActionCards(Card.Colour colour) {
        Card.Value[] actionValues = {Card.Value.DrawTwo, Card.Value.Skip, Card.Value.Reverse};
        for (Card.Value value : actionValues) {
            cards[cardsInDeck++] = new Card(colour, value);
            cards[cardsInDeck++] = new Card(colour, value);
        }
    }

    // Adds wild cards (Wild and Wild Four) to the deck
    // Adds four of each wild card to the deck
    private void addWildCards() {
        Card.Value[] wildValues = {Card.Value.Wild, Card.Value.wildFour};
        for (Card.Value value : wildValues) {
            for (int i = 0; i < 4; i++) {
                cards[cardsInDeck++] = new Card(Card.Colour.Wild, value);
            }
        }
    }

    // Replaces the deck's current cards with a stockpile
    // Reinitialises the deck using the provided list of cards
    // Stockpile: the ArrayList of cards to set as the current deck
    public void replaceDeckWith(ArrayList<Card> stockpile) {
        this.cards = stockpile.toArray(new Card[0]);
        this.cardsInDeck = this.cards.length;
    }

    // Checks if the deck is empty
    // @return true if no cards are left in the deck
    public boolean isEmpty() {
        return cardsInDeck == 0;
    }

    // Shuffles the deck using Fisher-Yates algorithm
    // Randomises the order of the cards array
    public void shuffle() {
        int len = cards.length;
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int randomIndex = i + random.nextInt(len - i);
            Card temp = cards[randomIndex];
            cards[randomIndex] = cards[i];
            cards[i] = temp;
        }
    }

    // Draws a single card from the deck
    // Returns null if the deck is empty, otherwise returns the top card
    // Return Card if available, null if deck is empty
    public Card drawCard() {
        try {
            if (isEmpty()) {
                throw new IllegalArgumentException("Cannot draw card. No cards in deck.");
            }
            return cards[--cardsInDeck];
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null; // Returning null as a fallback
        }
    }

    // Draws the image of a card from the deck
    // Returns null if the deck is empty, otherwise returns the image of the top card
    // Return ImageIcon if available, null if deck is empty
    public ImageIcon drawCardImages() {
        try {
            if (isEmpty()) {
                throw new IllegalArgumentException("Cannot draw card. Deck is empty.");
            }
            return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null; // Returning null as a fallback
        }
    }

    // Draws a specified number of cards from the deck
    // Returns an empty array if insufficient cards or a negative number is requested
    // NumberOfCards: the number of cards to draw from the deck
    // Return an array of drawn cards, or an empty array if the deck has insufficient cards
    public Card[] drawCard(int numberOfCards) {
        try {
            if (numberOfCards < 0) {
                throw new IllegalArgumentException("Attempt to draw " + numberOfCards + " cards failed. Must draw a positive number.");
            }
            if (numberOfCards > cardsInDeck) {
                throw new IllegalArgumentException("Attempt to draw " + numberOfCards + " cards failed. Only " + cardsInDeck + " cards left in the deck.");
            }

            Card[] drawnCards = new Card[numberOfCards];
            for (int i = 0; i < numberOfCards; i++) {
                drawnCards[i] = cards[--cardsInDeck];
            }
            return drawnCards;

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return new Card[0]; // Returning an empty array as a fallback
        }
    }
}