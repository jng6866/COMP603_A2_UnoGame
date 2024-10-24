/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author haydenwinterburn & mustafakamish
 */
public class Game {
    
    private int currentPlayer;
    private String[] playerIds;
    
    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile;
    private Card.Colour validColour;
    private Card.Value  validValue;
    private GameStage gamestage;
    boolean direction;
    
    public Game(String[] pids){
        deck = new Deck();
        deck.shuffle();
        stockPile = new ArrayList<Card>();
        playerIds = pids;
        currentPlayer = 0;
        direction = false;
        playerHand = new ArrayList<ArrayList<Card>>();
        this.gamestage = gamestage;
        
        for(int i = 0; i < pids.length; i++){
            ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }  
    }
    
    
    private int[] getPlayerIDs() {
    int[] playerIDs = new int[playerIds.length];  // Create an array to store player IDs

    for (int i = 0; i < playerIds.length; i++) {
        playerIDs[i] = PlayerDB.getPlayerID(playerIds[i]);  // Get the ID for each player name using PlayerDB
    }

    return playerIDs;  // Return the array of player IDs
}
    
    public void start(Game game){
        Card card = deck.drawCard();
        validColour = card.getColour();
        validValue = card.getValue();
        
        if(card.getValue() == Card.Value.Wild){
            start(game);
        }
        
        if(card.getValue() == Card.Value.wildFour || card.getValue() == Card.Value.DrawTwo){
            start(game);
        }
        
        if(card.getValue() == Card.Value.Skip){
            JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped.");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showConfirmDialog(null, message);
            
            if(direction == false){
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }
            else if(direction == true){
                currentPlayer = (currentPlayer - 1) % playerIds.length;
            
                if(currentPlayer == -1){
                    currentPlayer = playerIds.length - 1;
                }   
            }
        }
        
        if(card.getValue() == Card.Value.Reverse){
            JLabel message = new JLabel(playerIds[currentPlayer] + " reversed the game direcion.");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showConfirmDialog(null, message);
            
            direction ^= true;
            currentPlayer = playerIds.length -1;
        }
        
        stockPile.add(card);
    }
    
    
    
    public Card getTopCard(){
        return new Card(validColour, validValue);
    }
    
    public ImageIcon getTopCardImage(){
        return new ImageIcon(validColour + "_" + validValue + ".png");
    }
    
    public boolean isGameOver(){
        for(String player : this.playerIds){
            if(hasEmptyhand(player)){
                return true;
            }
        }
        return false;
    }
    
    private boolean hasEmptyhand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }
    
    public String getCurrentPlayer(){
        return this.playerIds[this.currentPlayer];
    }
    
    public String getPreviousPlayer(int i){
        int index = this.currentPlayer - i;
        
        if(index == -1){
            index = playerIds.length - 1;
        }
        
        return this.playerIds[index];
    }
    
    public String[] getPlayers(){
        return playerIds;
    }
    
    public ArrayList<Card> getPlayerHand(String pid){
        int index = Arrays.asList(playerIds).indexOf(pid);
        
        return playerHand.get(index);
    }
    
    public int getPlayerHandSize(String pid){
        return getPlayerHand(pid).size();
    }
    
    public Card getPlayerCard(String pid, int choice){
        ArrayList<Card> hand = getPlayerHand(pid);
        return hand.get(choice);
    }
    
    public boolean validCardPlay(Card card){
        return card.getColour() == validColour || card.getValue() == validValue;
    }
    
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException{
        if(this.playerIds[this.currentPlayer] != pid){
            throw new InvalidPlayerTurnException("It is not " + pid + "'s turn", pid);
        }
    }
    
    public void submitDraw (String pid) throws InvalidPlayerTurnException{
        checkPlayerTurn(pid);
        
        if(deck.isEmpty()){
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }
        
        getPlayerHand(pid).add(deck.drawCard());
        
        if(direction == false){
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        }
        else if(direction == true){
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if(currentPlayer == -1){
                currentPlayer = playerIds.length - 1;
            }
        }    
    }
    // Set card colour if it is valid
    public void setCardColour(Card.Colour colour){
        validColour = colour;
    }
    
    // Will run this method when a card is played
    // Will verify if it is valid
    // If the card is an action card, will peform the function of the action type
    public void submitPlayerCard(String pid, Card card, Card.Colour declaredColour)
        throws InvalidColourSubmissionException, InvalidPlayerTurnException, InvalidValueSubmissionException {
    
        checkPlayerTurn(pid);  // Check if it's the correct player's turn

        ArrayList<Card> pHand = getPlayerHand(pid);

        // Handle wild card by immediately setting the declared colour
        if (card.getColour() == Card.Colour.Wild) {
            // Set the valid color and value for the next turn
            validColour = declaredColour;
            validValue = card.getValue();  
        }
        // Ensure the card being played is valid
        else if (!validCardPlay(card)) {
            
            //Displays message if card is an invalid move
            JLabel message = new JLabel("Invalid move. Expected colour: " + validColour +
                    ". Given color: " + card.getColour() + ".");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showMessageDialog(null, message);

            throw new InvalidColourSubmissionException(message, card.getColour(), validColour);
        }
        else{
            // Set the valid color and value for the next turn
            validColour = card.getColour();
            validValue = card.getValue();
        }

        // Remove the card from the player's hand
        pHand.remove(card);

        if (hasEmptyhand(pid)) {
    //        JLabel message = new JLabel(pid + " has won the game!");
    //        message.setFont(new Font("Arial", Font.BOLD, 48));
    //        JOptionPane.showMessageDialog(null, message);
            System.out.println("Winner's PID: " + pid);
            String winnerName = pid;
            int[] playerIDs = getPlayerIDs();
            int winnerId = PlayerDB.getPlayerID(winnerName);

            PlayerDB.addScore(winnerId, 1); 
            new EndScreen(winnerName, playerIDs).setVisible(true);
            gamestage.dispose();
            System.exit(0); 
        }

            stockPile.add(card);  // Add the card to the stockpile


        // ==================== SPECIAL CARD HANDLING ====================

        if (card.getValue() == Card.Value.Reverse) {
            //Log current player before reverse (Debugging)
            System.out.println("Before Reverse: Current Player: " + playerIds[currentPlayer]);
            
            //Display message for the player who played reverse card
            String reversePlayer = playerIds[currentPlayer];
            JLabel message = new JLabel(reversePlayer + " used reverse!");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showMessageDialog(null, message);

            direction ^= true;  // Reverse the direction

            // Adjust currentPlayer based on the new direction
            if (direction) {
                // If the direction is now reversed, move counterclockwise
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;
            } else {
                // If the direction is now normal, move clockwise
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }
            //Log the current player after reverse (Debugging)
            System.out.println("After Reverse: Current Player: " + playerIds[currentPlayer]);
            return;
        }

        if (card.getValue() == Card.Value.DrawTwo) {
            // Log the current player before handling Draw Two (Debugging)
            System.out.println("Before Draw Two: Current Player: " + playerIds[currentPlayer]);
            
            // Move to the next player who will draw the two cards
            if (!direction) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;  // Clockwise direction
            } else {
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;  // Anti clockwise direction
            }
            
            pid = playerIds[currentPlayer];
            
            // Log the player who will draw two cards (Debugging)
            System.out.println("Player who will draw two cards: " + playerIds[currentPlayer]);
            
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            
            //Display message for the player who played draw two card
            JLabel message = new JLabel(pid + ", draw 2 cards!");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showMessageDialog(null, message);
            
            // Log the current player after Draw Two is handled (Debugging)
            System.out.println("After Draw Two: Current Player: " + playerIds[currentPlayer]);
            
            return;
        }

        if (card.getValue() == Card.Value.wildFour) {
            // Log the current player before handling Wild Draw Four (Debugging)
            System.out.println("Before Wild Draw Four: Current Player: " + playerIds[currentPlayer]);

            // Move to the next player who will draw the four cards
            if (!direction) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;  // Clockwise direction
                System.out.println("Moving to next player in clockwise direction: " + playerIds[currentPlayer]);
            } else {
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;  // Anti clockwise direction
                System.out.println("Moving to previous player in counterclockwise direction: " + playerIds[currentPlayer]);
            }
            
            pid = playerIds[currentPlayer];
            
            // Log the player who will draw four cards (Debugging)
            System.out.println("Player who will draw four cards: " + playerIds[currentPlayer]);
            
            for (int i = 0; i < 4; i++) {
                getPlayerHand(pid).add(deck.drawCard());
            }
            JLabel message = new JLabel(pid + ", draw 4 cards!");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showMessageDialog(null, message);
            
            // Log the current player after Wild Draw Four is handled (Debugging)
            System.out.println("After Wild Draw Four: Current Player: " + playerIds[currentPlayer]);
            
            return;
        }

        if (card.getValue() == Card.Value.Skip) {
            // Log the current player before skipping (Debugging)
            System.out.println("Before Skip: Current Player: " + playerIds[currentPlayer]);
            
            JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped!");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showMessageDialog(null, message);
            
            // Skip the next player by advancing twice
            if (!direction) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;  // Clockwise direction
                System.out.println("Skipped Player: " + playerIds[currentPlayer]);
                currentPlayer = (currentPlayer + 1) % playerIds.length;  // Move to the next player after skip
            } else {
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;  // Counterclockwise direction
                System.out.println("Skipped Player: " + playerIds[currentPlayer]);
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;  // Move to the previous player after skip
            }
            // Log the player after skipping
            System.out.println("After Skip: Current Player: " + playerIds[currentPlayer]);
            return;
        }
        
        // ==================== GENERAL PLAYER ADVANCEMENT ====================    
        // Handles the general direction for next player advancing
        if (!direction) {
        currentPlayer = (currentPlayer + 1) % playerIds.length;  // Clockwise move
        } else {
            currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;  // Anti clockwise move
        }
        
        // Log the current player if non action card is played (Debugging)
        System.out.println("Next Player After Regular Play: " + playerIds[currentPlayer]);
    }
}