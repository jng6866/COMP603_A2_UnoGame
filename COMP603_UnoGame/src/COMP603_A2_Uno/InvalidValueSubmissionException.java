/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

import javax.swing.JLabel;

/**
 * Exception for invalid card value submissions in the Uno game.
 * Indicates when the actual card value does not match the expected value.
 * @author haydenwinterburn & mustafakamish
 */
public class InvalidValueSubmissionException extends Exception {
    // Expected card value for a valid play
    private Card.Value expected;
    
    // Actual card value submitted
    private Card.Value actual;

    /**
     * Constructor with message, actual, and expected card values.
     */
    public InvalidValueSubmissionException(String message, Card.Value actual, Card.Value expected) {
        super(message);
        this.actual = actual;
        this.expected = expected;
    }

    /**
     * Unsupported constructor with JLabel parameter.
     */
    InvalidValueSubmissionException(JLabel message2, Card.Value value, Card.Value validValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
