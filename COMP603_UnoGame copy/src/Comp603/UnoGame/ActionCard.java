/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

/**
 *
 * @author haydenwinterburn
 */
// ActionCard class
public class ActionCard extends Card {
   // Enum represents action types
    public enum ActionType { SKIP, REVERSE, DRAW_TWO }
    
    private ActionType action;

    // Constructor initializingg colour and action 
    public ActionCard(Colour colour, ActionType action) {
        super(colour, getValueFromAction(action));
        this.action = action;
    }
    
    //map actiontype value
    private static Value getValueFromAction(ActionType action) {
        switch (action) {
            case SKIP:
                return Value.SKIP;
            case REVERSE:
                return Value.REVERSE;
            case DRAW_TWO:
                return Value.DRAW_TWO;
            default:
                throw new IllegalArgumentException("Invalid action type");
        }
    }
    //action type getter
    public ActionType getAction() {
        return action;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String play() {
        return "Playing an action card: " + getColour() + " " + action;
    }
}
