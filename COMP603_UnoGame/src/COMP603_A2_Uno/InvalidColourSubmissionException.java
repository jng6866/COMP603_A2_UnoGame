/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

import javax.swing.JLabel;

/**
 *
 * @author haydenwinterburn
 */
public class InvalidColourSubmissionException extends Exception{
    private Card.Colour expected;
    private Card.Colour actual;
    
    public InvalidColourSubmissionException(String message, Card.Colour actual, Card.Colour expected){
        this.actual = actual;
        this.expected = expected;
    }

    // Constructor with JLabel (assuming the label is used to display error messages in GUI)
    public InvalidColourSubmissionException(JLabel messageLabel, Card.Colour actual, Card.Colour expected) {
        super("Invalid move. Expected color: " + expected + ", given color: " + actual);
        this.actual = actual;
        this.expected = expected;
        messageLabel.setText(getMessage()); // Update JLabel with the exception message
    }

    // Getter methods for accessing the colors involved in the exception
    public Card.Colour getExpected() {
        return expected;
    }

    public Card.Colour getActual() {
        return actual;
    }
}
    

