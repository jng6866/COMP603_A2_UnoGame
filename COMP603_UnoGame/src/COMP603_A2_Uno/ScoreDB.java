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
}