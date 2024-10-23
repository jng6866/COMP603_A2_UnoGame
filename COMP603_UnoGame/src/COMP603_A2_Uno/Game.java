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
 * @author haydenwinterburn
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
            message.setFont(new Font("Arial", Font.BOLD, 48));
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
            message.setFont(new Font("Arial", Font.BOLD, 48));
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
    
    public void setCardColour(Card.Colour colour){
        validColour = colour;
    }
    
    public void submitPlayerCard(String pid, Card card, Card.Colour declaredColour)
        throws InvalidColourSubmissionException, InvalidPlayerTurnException, InvalidValueSubmissionException {
    
    checkPlayerTurn(pid);  // Check if it's the correct player's turn

    ArrayList<Card> pHand = getPlayerHand(pid);

    // Handle wild card by immediately setting the declared colour
    if (card.getColour() == Card.Colour.Wild) {
        validColour = declaredColour;  // Set to the declared color
        validValue = card.getValue();
    }
    // Ensure the card being played is valid
    else if (!validCardPlay(card)) {
        JLabel message = new JLabel("Invalid move. Expected colour: " + validColour +
                ". Given color: " + card.getColour() + ".");
        message.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);

        throw new InvalidColourSubmissionException(message, card.getColour(), validColour);
    }
    else{
        validColour = card.getColour();
        validValue = card.getValue();
    }

    // Remove the card from the player's hand
    pHand.remove(card);

    // Check if the player has won
//    if (hasEmptyhand(this.playerIds[currentPlayer])) {
//        JLabel message = new JLabel(this.playerIds[currentPlayer] + " has won! Thanks for playing!");
//        message.setFont(new Font("Arial", Font.BOLD, 48));
//        JOptionPane.showMessageDialog(null, message);
//        System.exit(0);
//    }
    
    if (hasEmptyhand(pid)) {
//        JLabel message = new JLabel(pid + " has won the game!");
//        message.setFont(new Font("Arial", Font.BOLD, 48));
//        JOptionPane.showMessageDialog(null, message);
        System.out.println("Winner's PID: " + pid);
        new EndScreen(pid).setVisible(true);
        gamestage.dispose();
        System.exit(0);  // End the game if the player has no cards left.
    }
//    // Set the valid color and value for the next turn
//    validColour = card.getColour();
//    validValue = card.getValue();

    stockPile.add(card);  // Add the card to the stockpile

    // Handle direction for next player
    if (!direction) {
        currentPlayer = (currentPlayer + 1) % playerIds.length;
    } else {
        currentPlayer = (currentPlayer - 1) % playerIds.length;
        if (currentPlayer == -1) {
            currentPlayer = playerIds.length - 1;
        }
    }

    // Handle special cards
    if (card.getValue() == Card.Value.DrawTwo) {
        pid = playerIds[currentPlayer];
        getPlayerHand(pid).add(deck.drawCard());
        getPlayerHand(pid).add(deck.drawCard());
        JLabel message = new JLabel(pid + ", draw 2 cards!");
        JOptionPane.showMessageDialog(null, message);
    }

    if (card.getValue() == Card.Value.wildFour) {
        pid = playerIds[currentPlayer];
        for (int i = 0; i < 4; i++) {
            getPlayerHand(pid).add(deck.drawCard());
        }
        JLabel message = new JLabel(pid + ", draw 4 cards!");
        JOptionPane.showMessageDialog(null, message);
    }

    if (card.getValue() == Card.Value.Skip) {
        JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped!");
        message.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
        if (!direction) {
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        } else {
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if (currentPlayer == -1) {
                currentPlayer = playerIds.length - 1;
            }
        }
    }

    if (card.getValue() == Card.Value.Reverse) {
        JLabel message = new JLabel(playerIds[currentPlayer] + " used reverse!");
        message.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
        direction ^= true;  // Reverse the direction
        }
    
    }
}