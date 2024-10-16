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
public class InvalidValueSubmissionException extends Exception {
    private Card.Value expected;
    private Card.Value actual;
    
    public InvalidValueSubmissionException(String message, Card.Value actual, Card.Value expected){
        this.actual = actual;
        this.expected = expected;
    }

    InvalidValueSubmissionException(JLabel message2, Card.Value value, Card.Value validValue) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
