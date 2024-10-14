/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HaydenWinterburn & MustafaKamish
 */
public class Player {
    private String name;
    private List<Card> hand;
    
    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the hand
     */
    public List<Card> getHand() {
        return hand;
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public Card playCard(int i){
        return hand.remove(i);
    }
    @Override
    public String toString(){
        String displayHand = name + "'s hand:\n";
        for(int i = 0; i < hand.size(); i++){
            displayHand += "[" + i + "] " + hand.get(i) + "\n";
        }
        
        return displayHand;
    }
}
