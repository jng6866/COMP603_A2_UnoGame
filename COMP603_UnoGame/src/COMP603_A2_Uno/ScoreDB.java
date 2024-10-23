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

public class ScoreDB {

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


    public static void getScores() {
        String sql = "SELECT * FROM scores";
        
        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Score ID: " + rs.getInt("id") + ", Player ID: " + rs.getInt("player_id") + ", Score: " + rs.getInt("score"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}