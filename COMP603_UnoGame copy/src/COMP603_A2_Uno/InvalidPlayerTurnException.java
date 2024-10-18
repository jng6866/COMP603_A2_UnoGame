/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

/**
 *
 * @author haydenwinterburn
 */
public class InvalidPlayerTurnException extends Exception {
    
    String playerId;
    
    public InvalidPlayerTurnException(String message, String pid) {
        super(message);
        playerId = pid;
    }
    public String getPid(){
        return playerId;
    }
    
}
