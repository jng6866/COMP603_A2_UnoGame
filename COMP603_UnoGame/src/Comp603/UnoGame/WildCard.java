/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

/**
 *
 * @author haydenwinterburn & MustafaKamish
 */
public class WildCard extends Card {
    // Enum to define Wilcard value type.
    public enum WildType { WILD, DRAW_FOUR }
    
    //variable instance: stores type of wild card.
    private WildType wildType;

    // Constructor that accepts wild type
    public WildCard(WildType wildType) {
        //calls the superclass (Card) constructor.
        //colour set to WILD and the value from wild type.
        super(Colour.WILD, getValueFromWildType(wildType)); 
        //Set wildtype for this instance of wildcard.
        this.wildType = wildType;
    }

    //Helper method to map WildType to Value
    private static Value getValueFromWildType(WildType wildType) {
        
        //Switch between the different wild types.
        //matches with the corresponding value in the game.
        switch (wildType) {
            case WILD:
                return Value.WILD;
            case DRAW_FOUR:
                return Value.DRAW_FOUR;
            default:
                throw new IllegalArgumentException("Invalid wild card type.");
                //throws exception error if not a valid wild type.
        }
    }
    //get method: retrieves wild type.
    public WildType getWildType() {
        return wildType;
    }
    //Override the toString() method from the parent class 'Card' 
     @Override
    public String toString() {
        return super.toString();
    }
    //Override the play() method from the parent class 'Card'
    @Override
    public String play() {
        return "Playing a wild card: " + wildType;
    }
}
