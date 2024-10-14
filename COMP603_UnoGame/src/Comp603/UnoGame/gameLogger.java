/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Comp603.UnoGame;

/**
 *
 * @author mustafakamish
 */

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime; // gets time
import java.time.format.DateTimeFormatter; // setting the date and time as string

public class gameLogger {

    private static final String LOG_PATH = "resources/game_log.txt"; //log file location

    // log game results
    public void logResult(String winnerName, String loserName) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // gets the current date and time and sets the formst
        String logEntry = "PLayed " + timeStamp + " - Winner: " + winnerName + ", Loser: " + loserName; //create log entry in format: time, winner, and loser
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true))) {
            writer.write(logEntry);   // write to file
            writer.newLine(); // new line for formatting
        } catch (IOException e) {
           
        }
    }

    // red log method
    public void readLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_PATH))) {
            String line; 
            while ((line = reader.readLine()) != null) { // read and print all lines
                System.out.println(line); // prints to console
            }
        } catch (IOException e) {

        }
    }
}