/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

/**
 *
 * @author haydenwinterburn
 */
public abstract class Card {
    // Enums for card color and value.
    public enum Colour { RED, YELLOW, GREEN, BLUE, WILD }
    public enum Value { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_TWO, SKIP, REVERSE, WILD, DRAW_FOUR }

    // Private attributes for color and value.
    private Colour colour;
    private Value value;

    // Constructor that accepts colour and value.
    public Card(Colour colour, Value value) {
        this.colour = colour;
        this.value = value;
    }

    // Getter to access the colour attribute.
    public Colour getColour() {
        return colour;
    }

    // Getter ro access the value attribute.
    public Value getValue() {
        return value;
    }
    //Overriding from the object Class
    //returns Colour and Value of the card separated by space.
    @Override
    public String toString() {
        return colour + " " + value;
    }
    // Abstract method to be implemented by subclasses.
    public abstract String play();
}
