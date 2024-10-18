/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

/**
 *
 * @author haydenwinterburn
 */
public class NumberCard extends Card {
    private int number;

    // Constructor that accepts colour and number
    public NumberCard(Colour colour, int number) {
        super(colour, getValueFromNumber(number)); // Call to the Card constructor
        this.number = number;
    }

    // Helper method to map number to Value
    private static Value getValueFromNumber(int number) {
        switch (number) {
            case 0: return Value.ZERO;
            case 1: return Value.ONE;
            case 2: return Value.TWO;
            case 3: return Value.THREE;
            case 4: return Value.FOUR;
            case 5: return Value.FIVE;
            case 6: return Value.SIX;
            case 7: return Value.SEVEN;
            case 8: return Value.EIGHT;
            case 9: return Value.NINE;
            default:
                throw new IllegalArgumentException("Invalid number");
        }
    }

    public int getNumber() {
        return number;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String play() {
        return "Playing a number card: " + getColour() + " " + number;
    }
}
