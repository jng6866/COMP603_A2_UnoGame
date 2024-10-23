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

public class GameRoundsDB {

    public static void addGameRound(int roundNumber, int winnerId) {
        String sql = "INSERT INTO game_rounds (round_number, winner_id) VALUES (?, ?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roundNumber);
            pstmt.setInt(2, winnerId);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getGameRounds() {
        String sql = "SELECT * FROM game_rounds";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Round Number: " + rs.getInt("round_number") + ", Winner ID: " + rs.getInt("winner_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
