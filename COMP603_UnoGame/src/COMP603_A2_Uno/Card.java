/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

/**
 * @author: haydenwinterburn mustafakamish
 */
public class Card {
    // Represents a Card with a specific Colour and Value for the Uno game.
    // Immutable to ensure each Card's properties do not change after creation.
    // Includes enums for Colour and Value types.
    
    // Enum for card colours with a method to retrieve colours by index
    public enum Colour {
        Red, Blue, Green, Yellow, Wild;

        // Retrieves a Colour by index, ensuring valid bounds
        public static Colour getColour(int index) {
            if (index < 0 || index >= Colour.values().length) {
                throw new IllegalArgumentException("Invalid index for Colour");
            }
            return Colour.values()[index];
        }
    }

    // Enum for card values with a method to retrieve values by index
    public enum Value {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, Wild, wildFour;

        // Retrieves a Value by index, ensuring valid bounds
        public static Value getValue(int index) {
            if (index < 0 || index >= Value.values().length) {
                throw new IllegalArgumentException("Invalid index for Value");
            }
            return Value.values()[index];
        }
    }

    // Colour and Value are immutable once set for each card
    private final Colour colour;
    private final Value value;

    // Creates a card with specified Colour and Value
    public Card(final Colour colour, final Value value) {
        this.colour = colour;
        this.value = value;
    }

    // Gets the Colour of the card
    public Colour getColour() {
        return colour;
    }

    // Gets the Value of the card
    public Value getValue() {
        return value;
    }

    // String representation of the card in "Colour_Value" format
    @Override
    public String toString() {
        return colour + "_" + value;
    }
}