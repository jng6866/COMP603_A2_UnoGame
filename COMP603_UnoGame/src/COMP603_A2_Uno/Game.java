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
public class Game implements GameInterface{
    
    private int currentPlayer;
    private String[] playerIds;
    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile;
    private Card.Colour validColour;
    private Card.Value  validValue;
    private GameStage gamestage;
    boolean direction;
    
    private long gameStartTime;
    private int cardsPlayed = 0;
    
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
        
        gameStartTime = System.currentTimeMillis();
        cardsPlayed = 0;
    }
    
    private int[] getPlayerIDs() {
    int[] playerIDs = new int[playerIds.length]; 

    for (int i = 0; i < playerIds.length; i++) {
        playerIDs[i] = PlayerDB.getPlayerID(playerIds[i]);  
        }
        return playerIDs;  
    }
   
    @Override
    public void startGame() {
        Card card = deck.drawCard();
        validColour = card.getColour();
        validValue = card.getValue();

        // Restart if the initial card is a Wild card
        if (card.getValue() == Card.Value.Wild) {
            startGame();
            return;
        }

        // Restart if the initial card is a DrawTwo or WildFour
        if (card.getValue() == Card.Value.wildFour || card.getValue() == Card.Value.DrawTwo) {
            startGame();
            return;
        }

        // Handle Skip card effect
        if (card.getValue() == Card.Value.Skip) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped.");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showConfirmDialog(null, message);

            // Move to the next player based on the game's direction
            if (!direction) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            } else {
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;
            }
        }

        // Handle Reverse card effect
        if (card.getValue() == Card.Value.Reverse) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " reversed the game direction.");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showConfirmDialog(null, message);

            direction = !direction; // Toggle direction
            currentPlayer = playerIds.length - 1; // Reset to the last player in the list
        }

        // Add the drawn card to the stock pile as the initial card
        stockPile.add(card);
    }
        
    @Override
    public String getCurrentPlayer(){
        return this.playerIds[this.currentPlayer];
    }
    
    @Override
    public ArrayList<Card> getPlayerHand(String pid){
        int index = Arrays.asList(playerIds).indexOf(pid);
        
        return playerHand.get(index);
    }

    @Override
    public int getPlayerHandSize(String pid){
        return getPlayerHand(pid).size();
    }
    
    @Override
    public Card getTopCard(){
        return new Card(validColour, validValue);
    }
    
    @Override
    public int getGameDuration() {
        long currentTime = System.currentTimeMillis();
        return (int) ((currentTime - gameStartTime) / 1000); 
    }
    @Override
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException{
        if(this.playerIds[this.currentPlayer] != pid){
            throw new InvalidPlayerTurnException("It is not " + pid + "'s turn", pid);
        }
    }
    
    @Override
    public boolean isGameOver() {
        for (String player : playerIds) {
            if (hasEmptyHand(player)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public ImageIcon getTopCardImage(){
        return new ImageIcon(validColour + "_" + validValue + ".png");
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

    public Card getPlayerCard(String pid, int choice){
        ArrayList<Card> hand = getPlayerHand(pid);
        return hand.get(choice);
    }
    
    public boolean validCardPlay(Card card){
        return card.getColour() == validColour || card.getValue() == validValue;
    }
    
    public int getCardsPlayed() {
        return cardsPlayed;
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
        cardsPlayed++;
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
        
        // ========================= DETERMINE WINNER ==========================
        if (isGameOver()) {

            System.out.println("Winner's PID: " + pid); // Debugging statement
            
            String winnerName = pid;
            int[] playerIDs = getPlayerIDs();
            int winnerId = PlayerDB.getPlayerID(winnerName);
            int totalTimePlayed = getGameDuration(); 
            
            GameStatsDB.addGameStats(cardsPlayed, totalTimePlayed);
            PlayerDB.addScore(winnerId, 1); 
            
            new EndScreen(winnerName, playerIDs).setVisible(true);
            gamestage.dispose(); //Closes the gamestage window
            System.exit(0); //Finishing the game
        }

            stockPile.add(card);  // Add the card to the stockpile


        // ======================= SPECIAL CARD HANDLING =======================
        
        // ============================== REVERSE ==============================
        
        if (card.getValue() == Card.Value.Reverse) {
            //Log current player before reverse (Debugging)
            System.out.println("Before Reverse: Current Player: " + playerIds[currentPlayer]);
            
            //Display message for the player who played reverse card
            String reversePlayer = playerIds[currentPlayer];
            JLabel message = new JLabel(reversePlayer + " used reverse!");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showMessageDialog(null, message);

            direction = !direction;  // Reverse the direction

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
        // =============================== DRAWTWO =============================
         
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
        
        // =============================== DRAWFOUR ============================
        
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
          
        // =============================== SKIP ================================
        
        if (card.getValue() == Card.Value.Skip) {
            // Log the current player before skipping (Debugging)
            System.out.println("Before Skip: Current Player: " + playerIds[currentPlayer]);

            // Skip the next player by advancing once before showing the message
            if (!direction) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;  // Moves to next player (Clockwise)
            } else {
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;  // Moves to previous player to be skipped (Anti-Clockwise)
            }

            // Display message for who is being skipped
            JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped!");
            message.setFont(new Font("Arial", Font.BOLD, 36));
            JOptionPane.showMessageDialog(null, message);

            // Log the skipped player (Debugging)
            System.out.println("Skipped Player: " + playerIds[currentPlayer]);

            // Advance to next player after the skip
            if (!direction) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;  // Moves to next player after one skip (Clockwise)
            } else {
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;  // Moves to previous player after one skip (Anti-Clockwise)
            }
            
            // Log the player after skipping (Debugging)
            System.out.println("After Skip: Current Player: " + playerIds[currentPlayer]);
            return;
        }

        // ==================== GENERAL PLAYER ADVANCEMENT ===================== 
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