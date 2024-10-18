/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Comp603.UnoGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StatsManager {


    public int[] loadStats(String playerName) {  // pull from players personal file
        String filePath = "resources/players/player_" + playerName + ".txt"; //files created if not already
        int[] stats = {0, 0}; // stats (total plays , total wins)
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(" ");
                stats[0] = Integer.parseInt(parts[0]); // games played
                stats[1] = Integer.parseInt(parts[1]); // wins
            }
        } catch (IOException e) {
            
        }
        return stats;
    }

    // update stats from a player
    public void updateStats(String playerName, boolean isWinner) {
        int[] stats = loadStats(playerName); // Load current stats
        stats[0]++; // plus one games played
        if (isWinner) {
            stats[1]++; // plus one if the player wins
        }
        saveStats(playerName, stats); // update the stats
    }

    // seaves the stats for that specific player t their file
    public void saveStats(String playerName, int[] stats) {
        String filePath = "resources/players/player_" + playerName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(stats[0] + " " + stats[1]); // games played and wins write
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public String getStats(String playerName) {
        int[] stats = loadStats(playerName);
        return playerName + " Total Games Played: " + stats[0] + " Total Games Won:" + stats[1]; // games played and wins printed to the consle
    }
}