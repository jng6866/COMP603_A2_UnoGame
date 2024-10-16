/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author haydenwinterburn
 */
public class Deck {
    
    private Card[] cards;
    private int cardsInDeck;
    
    public Deck(){
        cards = new Card[108]; 
        reset();
    }
    
        //Used to reset the deck
        public void reset(){
           Card.Colour[] colours = Card.Colour.values();
           cardsInDeck = 0;

           for(int i = 0; i < colours.length-1; i++){

               Card.Colour colour = colours[i];
               //adds one zero card
               cards[cardsInDeck++] = new Card(colour, Card.Value.getValues(0));

               //add two cards (1-9)
               for(int j = 1; j < 10; j++){
                   cards[cardsInDeck++] = new Card(colour, Card.Value.getValues(j));
                   cards[cardsInDeck++] = new Card(colour, Card.Value.getValues(j));
               }
               //adds two card (DrawTwo or Skip or Reverse)
               Card.Value[] values = new Card.Value[]{Card.Value.DrawTwo, Card.Value.Skip, Card.Value.Reverse};
               for(Card.Value value : values){
                   cards[cardsInDeck++] = new Card(colour, value);
                   cards[cardsInDeck++] = new Card(colour, value);
               }
           }
          //add one wild card (wild or wildFour)
          Card.Value[] values = new Card.Value[]{Card.Value.Wild, Card.Value.wildFour};
          for(Card.Value value : values){
              for(int i = 0; i < 4; i++){
                  cards[cardsInDeck++] = new Card(Card.Colour.Wild, value);
                }
            }
        }
        
        
        //Replace deck with ArrayList of cards (stockpile)
        public void replaceDeckWith(ArrayList<Card> cards){
            this.cards = cards.toArray(new Card[cards.size()]);
            this.cardsInDeck = this.cards.length;
        }
        
        //Check if deck is empty
        public boolean isEmpty(){
            return cardsInDeck == 0;
        }
        //Shuffle the deck:
        //Will get a random index of array past current index
        //Swaps random element with the present element
        public void shuffle(){
            int len = cards.length;
            Random random = new Random();
            
            for(int i = 0; i < cards.length; i++){
                int randomValue = i + random.nextInt(len - i);
                Card randomCard = cards[randomValue];
                cards[randomValue] = cards[i];
                cards[i] = randomCard;
            }
        }
        
        public Card drawCard() throws IllegalArgumentException{
            if (isEmpty()){
                throw new IllegalArgumentException ("Cannot draw card. No cards in deck.");
            }
        return cards[--cardsInDeck];
        }
        
        public ImageIcon drawCardImages() throws IllegalArgumentException{
            if (isEmpty()){
                throw new IllegalArgumentException ("Cannot draw card. Deck is empty.");
            }
        return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
        }
        
        public Card[] drawCard(int d){
            if (d < 0){
                throw new IllegalArgumentException ("Attempt to draw " + d + " cards failed. Must draw positive cards.");
            }
            
            if(d > cardsInDeck){
                 throw new IllegalArgumentException ("Attempt to draw " + d + " cards failed. There is only " + cardsInDeck + " cards in the deck.");
            }
            
            Card[] Return = new Card[d];
            
            for (int i = 0; i < d; i++){
                Return[i] = cards[--cardsInDeck];
            }
            return Return;
        }
}
