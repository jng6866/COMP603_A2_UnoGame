/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

import javax.swing.JLabel;

/**
 *
 * @author haydenwinterburn & mustafakamish
 */
public class InvalidColourSubmissionException extends Exception {

    // Expected card color for a valid play
    private Card.Colour expected;

    // Actual card color submitted
    private Card.Colour actual;

    // Constructor with message, actual, and expected colors
    public InvalidColourSubmissionException(String message, Card.Colour actual, Card.Colour expected) {
        super(message);
        this.actual = actual;
        this.expected = expected;
    }

    // Constructor with JLabel (assumes JLabel is used to display error messages in GUI)
    public InvalidColourSubmissionException(JLabel messageLabel, Card.Colour actual, Card.Colour expected) {
        super("Invalid move. Expected color: " + expected + ", given color: " + actual);
        this.actual = actual;
        this.expected = expected;
        messageLabel.setText(getMessage()); // Update JLabel with exception message
    }

    // Getter for expected color
    public Card.Colour getExpected() {
        return expected;
    }

    // Getter for actual color
    public Card.Colour getActual() {
        return actual;
    }
}
    

