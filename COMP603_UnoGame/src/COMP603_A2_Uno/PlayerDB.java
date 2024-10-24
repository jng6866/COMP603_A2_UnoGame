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

public class PlayerDB {

    public static void addPlayer(String playerName) {
        String sql = "INSERT INTO players (name) VALUES (?)";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
            System.out.println("Player added: " + playerName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getPlayers() {
        String sql = "SELECT * FROM players";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Player ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
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
    
}
