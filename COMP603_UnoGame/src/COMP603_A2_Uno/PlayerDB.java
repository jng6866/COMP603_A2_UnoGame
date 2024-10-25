/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

/**
 *
 * @author mufasa
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDB {
    
    
    //adds players to db
    public static void addPlayer(String playerName) {
    String sql = "INSERT INTO players (name) VALUES (?)";
    try (Connection conn = new DBConnection().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, playerName); //set player name
        pstmt.executeUpdate(); //execute
        System.out.println("Player added: " + playerName);

    } catch (SQLException e) {
        //check for duplicate error
        if (e.getSQLState().equals("23505")) {
            System.out.println("Player already exists: " + playerName);
        } else {
            e.printStackTrace(); // print trace
        }
    }
}
    
    public static int getPlayerID(String playerName) {
        int playerID = -1;
        String sql = "SELECT id FROM players WHERE name = ?";
        try (Connection conn = new DBConnection().getConnection();  
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, playerName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            playerID = rs.getInt("id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return playerID;
    }
    
    public static String getPlayerName(int playerId) {
        String playerName = "Unknown Player";
        String sql = "SELECT name FROM players WHERE id = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                playerName = rs.getString("name");
            }

    } catch (Exception e) {
        e.printStackTrace();
    }

        return playerName;
    }
    
    
    public static void addScore(int playerId, int score) {
        String sql = "INSERT INTO scores (player_id, score) VALUES (?, ?)";
       
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, playerId);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            System.out.println("Score added for player ID: " + playerId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // retrieve total score by using sum of games won
    public static int getPlayerTotalScore(int playerId) {
        int totalScore = 0;
        String sql = "SELECT SUM(score) AS total_score FROM scores WHERE player_id = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalScore = rs.getInt("total_score");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalScore;
    }
    
    // retrieves top 5 players in decending order
    public static List<String> getTop5Players() {
        String sql = 
            "SELECT p.name AS player_name, SUM(s.score) AS total_wins " +
            "FROM players p " +
            "JOIN scores s ON p.id = s.player_id " +
            "GROUP BY p.name " +
            "ORDER BY total_wins DESC " +
            "FETCH FIRST 5 ROWS ONLY";
    
        List<String> topPlayers = new ArrayList<>();
    
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
        
           while (rs.next()) {
               String playerName = rs.getString("player_name");
                int wins = rs.getInt("total_wins");
                topPlayers.add(playerName + " - " + wins + " wins"); // print player name then win 
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return topPlayers;
    }    
}
