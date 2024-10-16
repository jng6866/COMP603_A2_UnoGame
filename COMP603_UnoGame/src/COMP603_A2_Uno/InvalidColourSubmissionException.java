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

    InvalidColourSubmissionException(JLabel message, Card.Colour colour, Card.Colour validColour) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
