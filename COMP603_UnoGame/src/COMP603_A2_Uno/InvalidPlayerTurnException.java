/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

/**
 *
 * @author haydenwinterburn & mustafakamish
 */
public class InvalidPlayerTurnException extends Exception {
    
    // ID of the player who attempted an invalid turn
    String playerId;
    
    // Constructor with message and player ID
    public InvalidPlayerTurnException(String message, String pid) {
        super(message);
        playerId = pid;
    }
    
    // Getter for player ID
    public String getPid() {
        return playerId;
    }
    
}
