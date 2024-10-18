/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HaydenWinterburn & MustafaKamish
 */
public class UserManager {

    private static final String FILE_PATH = "resources/users.txt";
    private Map<String, Integer> userScores;

    public UserManager() {
        userScores = new HashMap<>();
        loadScores();
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) { // read each line
                String[] parts = line.split(" ");  
                String playerName = parts[0];  // first part (name)
                int score = Integer.parseInt(parts[1]);  // second part(score)
                userScores.put(playerName, score); // store in map
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateScore(String playerName) {
        userScores.put(playerName, userScores.getOrDefault(playerName, 0) + 1);
        saveScores();
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, Integer> entry : userScores.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue()); // write name and the score of player
                writer.newLine(); // new entry new line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getScore(String playerName) {
        return userScores.getOrDefault(playerName, 0);
    }
}
