/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

/**
 *
 * @author haydenwinterburn & mustafakamish
 */
public class Main {

    // The main entry point of the application
    public static void main(String[] args) throws Exception {
        
        // Attempt to set the cross-platform look and feel for a consistent UI appearance
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { 
            e.printStackTrace(); // Output the exception stack trace to assist with debugging
        }

        // Initialise and display the main Menu window for the game
        new Menu().setVisible(true);
    }
}
