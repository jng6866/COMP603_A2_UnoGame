/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Comp603.UnoGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HaydenWinterburn & MustafaKamish
 */
public class UnoGameMain{

    public static void main(String[] args) {
        // Created BufferedReader to read the inputs from the console (System.in).
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Prompts the first player to enter their username.
            System.out.println("Enter username for Player 1: ");
            String p1Name = read.readLine().toLowerCase(); //reads input for player1 username, converts the characters to lowercase.
            // Prompts the second player to enter their username.
            System.out.println("Enter username for Player 2: ");
            String p2Name = read.readLine().toLowerCase(); //reads input of player2 username, , converts the characters to lowercase.
            
            //Creates a new instance of Game with the player1 and player2 usernames provided.
            Game game = new Game(p1Name, p2Name);
            
            
            
            UserManager userManager = new UserManager(); // Manages wins in users.txt
            StatsManager statsManager = new StatsManager(); // Manages full stats in player-specific files
            
            // Adjust the way winner and loser names are retrieved based on Game class functionality
            String winnerName = p1Name; // Assume player 1 wins for demonstration, adjust based on game logic
            String loserName = p2Name;  // Assume player 2 loses for demonstration, adjust based on game logic

            // Update wins in UserManager
            userManager.updateScore(winnerName);  // Using the existing updateScore method in UserManager
            // Update full stats in StatsManager
            statsManager.updateStats(winnerName, true);  // Update stats for the winner
            statsManager.updateStats(loserName, false);  // Update stats for the loser
            // Print stats for both players
            System.out.println("Player Stats:");
            System.out.println(statsManager.getStats(winnerName)); // Display winner stats
            System.out.println(statsManager.getStats(loserName));  // Display loser stats
  
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage()); //print error message for errors from reading input.
        }
    }
}
