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
 * @author: haydenwinterburn & mustafakamish
 */
public class Game implements GameInterface {
    
    // Main class representing the core game logic and mechanics for Uno.
    // Handles player turns, card actions, and overall game progression.
 
    private int currentPlayer; // Index of the current player taking their turn
    private String[] playerIds; // Array holding the unique identifiers of players in the game
    private Deck deck; // The primary deck of cards used for the game
    private ArrayList<ArrayList<Card>> playerHand; // List of hands, each representing the cards a player holds
    private ArrayList<Card> stockPile; // Pile to hold all cards that have been played
    private Card.Colour validColour; // The current colour in play, which players must match
    private Card.Value validValue; // The current value in play, which players must match    
    private GameStage gamestage; // The main stage or window for the game
    private PopUp popUp; // Pop-up notifications or additional dialogue windows
    private boolean direction; // Boolean flag to track play direction (false for clockwise, true for anti-clockwise)
    private long gameStartTime; // Timestamp marking the start of the game
    private int cardsPlayed = 0; // Tracks the total number of cards played in the session

    // Constructor - Initialises deck, hands, and game properties
    public Game(String[] pids) {
        deck = new Deck();
        deck.shuffle();
        stockPile = new ArrayList<>();
        playerIds = pids;
        currentPlayer = 0;
        direction = false;
        playerHand = new ArrayList<>();
        this.gamestage = gamestage;
        this.popUp = popUp;

        // Populate each player's hand with 7 cards from the deck
        for (String pid : pids) {
            ArrayList<Card> hand = new ArrayList<>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }

        gameStartTime = System.currentTimeMillis(); // Record game start time
        cardsPlayed = 0; // Reset cards played count
    }

    // Starts the game, drawing an initial card and managing game setup rules
    @Override
    public void startGame() {
        Card card = deck.drawCard();
        validColour = card.getColour();
        validValue = card.getValue();

        // Restart if the drawn card is a special card (Wild, DrawTwo, wildFour)
        if (card.getValue() == Card.Value.Wild || card.getValue() == Card.Value.wildFour || card.getValue() == Card.Value.DrawTwo) {
            startGame();
            return;
        }

        // Manage initial Skip and Reverse card effects
        if (card.getValue() == Card.Value.Skip) {
            showMessage(playerIds[currentPlayer] + " was skipped.");
            advancePlayer(); // Move to the next player
        } else if (card.getValue() == Card.Value.Reverse) {
            showMessage(playerIds[currentPlayer] + " reversed the game direction.");
            direction = !direction; // Toggle play direction
            currentPlayer = playerIds.length - 1; // Set to the last player
        }

        stockPile.add(card); // Add initial card to stockpile
    }

    // Retrieves the current player's ID
    @Override
    public String getCurrentPlayer() {
        return this.playerIds[this.currentPlayer];
    }

    // Retrieves a specific player's hand based on their ID
    @Override
    public ArrayList<Card> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid); // Find player index
        return playerHand.get(index);
    }

    // Returns the number of cards in a specific player's hand
    @Override
    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size();
    }

    // Returns the top card on the pile in play
    @Override
    public Card getTopCard() {
        return new Card(validColour, validValue); // Create a new card representing the top card
    }

    // Calculates and returns the game duration in seconds
    @Override
    public int getGameDuration() {
        long currentTime = System.currentTimeMillis();
        return (int) ((currentTime - gameStartTime) / 1000); // Convert milliseconds to seconds
    }

    // Ensures the specified player is playing their turn, throws an error otherwise
    @Override
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        if (!this.playerIds[this.currentPlayer].equals(pid)) {
            throw new InvalidPlayerTurnException("It is not " + pid + "'s turn", pid);
        }
    }
    
    // Determines if the game is over by checking for an empty hand
    @Override
    public boolean isGameOver() {
        return Arrays.stream(playerIds).anyMatch(this::hasEmptyHand);
    }

    // Checks if a specific player's hand is empty
    private boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    // Retrieves the icon/image of the top card
    public ImageIcon getTopCardImage() {
        return new ImageIcon(validColour + "_" + validValue + ".png");
    }
    
    // Updates the colour in play, used when a Wild card is played
    public void setCardColour(Card.Colour colour) {
        validColour = colour;
    }

    // Handles draw action for a player and advances the turn
    public void submitDraw(String pid) throws InvalidPlayerTurnException {
        checkPlayerTurn(pid); // Confirm it's the player's turn

        // Shuffle stockpile into deck if deck is empty
        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());
        advancePlayer(); // Move to the next player's turn
    }

    // Processes a submitted card, managing effects and turn progression
    public void submitPlayerCard(String pid, Card card, Card.Colour declaredColour)
            throws InvalidColourSubmissionException, InvalidPlayerTurnException, InvalidValueSubmissionException {

        cardsPlayed++; // Increment the count of played cards
        checkPlayerTurn(pid); // Ensure it's the player's turn

        ArrayList<Card> pHand = getPlayerHand(pid);
        if (card.getColour() == Card.Colour.Wild) {
            validColour = declaredColour;
            validValue = card.getValue();
        } else if (!validCardPlay(card)) {
            showMessage("Invalid move. Expected colour: " + validColour + ". Given colour: " + card.getColour() + ".");
            throw new InvalidColourSubmissionException("Invalid colour", card.getColour(), validColour);
        } else {
            validColour = card.getColour();
            validValue = card.getValue();
        }

        pHand.remove(card);

        // End game if any player is out of cards
        if (isGameOver()) {
            endGame(pid);
        }

        stockPile.add(card);
        handleSpecialCards(card); // Handle effects of special cards (Skip, Reverse, etc.)
    }

    // Checks if a submitted card is valid based on current play rules
    private boolean validCardPlay(Card card) {
        return card.getColour() == validColour || card.getValue() == validValue;
    }

    // Applies effects for special cards (Skip, DrawTwo, Reverse, Wild)
    private void handleSpecialCards(Card card) {
        switch (card.getValue()) {
            case Reverse:
                showMessage(playerIds[currentPlayer] + " used reverse!");
                direction = !direction; // Toggle play direction
                advancePlayer();
                break;
            case DrawTwo:
                advancePlayer();
                String pid = playerIds[currentPlayer];
                getPlayerHand(pid).add(deck.drawCard());
                getPlayerHand(pid).add(deck.drawCard());
                showMessage(pid + ", draw 2 cards!");
                advancePlayer();
                break;
            case wildFour:
                advancePlayer();
                pid = playerIds[currentPlayer];
                for (int i = 0; i < 4; i++) {
                    getPlayerHand(pid).add(deck.drawCard());
                }
                showMessage(pid + ", draw 4 cards!");
                advancePlayer();
                break;
            case Skip:
                int skippedPlayerIndex = direction
                        ? (currentPlayer - 1 + playerIds.length) % playerIds.length  // Anti-clockwise
                        : (currentPlayer + 1) % playerIds.length;  // Clockwise
                showMessage(playerIds[skippedPlayerIndex] + " was skipped!");
                advancePlayer(); // Skip the player and advance
                advancePlayer(); // Move to the next turn after skipped
                break;
            default:
                advancePlayer();
                break;
        }
    }
    // Ends the game, displays winner information, and disposes of game windows
    private void endGame(String pid) {
        System.out.println("Winner's PID: " + pid);
        String winnerName = pid;
        int winnerId = PlayerDB.getPlayerID(winnerName);
        int totalTimePlayed = getGameDuration();
        // Convert playerIds from String[] to int[]
        int[] playerIDs = Arrays.stream(playerIds)
                            .mapToInt(PlayerDB::getPlayerID) // Convert each String ID to an int ID
                            .toArray();
        GameStatsDB.addGameStats(cardsPlayed, totalTimePlayed);
        PlayerDB.addScore(winnerId, 1);
        
        // Show the end screen with winner and player details
        new EndScreen(winnerName, playerIDs).setVisible(true);

        // Dispose of popUp and gamestage if they exist
        if (popUp != null) {
            popUp.dispose();
        }
        if (gamestage != null) {
            gamestage.dispose();
        }
    }

    // Advances to the next player based on the current direction
    private void advancePlayer() {
        currentPlayer = direction
                ? (currentPlayer - 1 + playerIds.length) % playerIds.length  // Anti-clockwise move
                : (currentPlayer + 1) % playerIds.length;                    // Clockwise move
    }

    // Utility method to display a popup message in a standard format
    private void showMessage(String messageText) {
        JLabel message = new JLabel(messageText);
        message.setFont(new Font("Arial", Font.BOLD, 36));
        JOptionPane.showMessageDialog(null, message);
    }
}

