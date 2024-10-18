/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

/**
 *
 * @author haydenwinterburn
 */
public class Card {
    enum Colour{
        Red, Blue, Green, Yellow, Wild;
        
        private static final Colour[] colours = Colour.values();

        /**
         * @return the colours
         */
        public static Colour getColours(int i) {
            return Colour.colours[i];
        }   
    }
    enum Value{
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, Wild, wildFour;
        
        private static final Value[] values = Value.values();

        /**
         * @return the values
         */
        public static Value getValues(int i) {
            return Value.values[i];
        }
    }

    /**
     *
     */
    // Ensure Colour and Value are not changed later
    private final Colour colour;
    private final Value value;
    
    public Card(final Colour colour, final Value value){
        this.colour = colour;
        this.value = value;
    }

    /**
     * @return the colour
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * @return the value
     */
    public Value getValue() {
        return value;
    } 
    
    //Overriding from the object Class
    //returns Colour and Value of the card separated by space.
    @Override
    public String toString() {
        return colour + "_" + value;
    }
}
