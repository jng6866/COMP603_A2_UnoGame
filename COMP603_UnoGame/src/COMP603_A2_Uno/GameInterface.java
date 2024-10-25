/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

import java.util.ArrayList;

/**
 *
 * @author haydenwinterburn & mustafakamish
 */
public interface GameInterface {
    
    void startGame(); // Starts or reinitialises the game
    String getCurrentPlayer(); // Retrieves the ID or name of the current player
    ArrayList<Card> getPlayerHand(String playerId); // Retrieves the current hand of a specific player
    int getPlayerHandSize(String playerId); // Returns the size of a player's hand
    Card getTopCard(); // Gets the top card on the stockpile
    int getGameDuration(); // Retrieves the game duration in seconds
    void checkPlayerTurn(String playerId) throws InvalidPlayerTurnException; // Verifies if it is the specified player's turn; throws an exception if not
    boolean isGameOver(); // Checks if the game is over by verifying players' hands 
}
