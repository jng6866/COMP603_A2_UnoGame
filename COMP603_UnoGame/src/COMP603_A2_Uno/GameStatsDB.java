package COMP603_A2_Uno;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mufasa
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameStatsDB {

    public static void addGameStats(int totalGames, int totalTime) {
        String sql = "INSERT INTO game_stats (total_games_played, total_time_played) VALUES (?, ?)";

        try (Connection conn = new DBConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, totalGames);
            pstmt.setInt(2, totalTime);
            pstmt.executeUpdate();
            System.out.println("Game stats added.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getGameStats() {
        String sql = "SELECT * FROM game_stats";

            try (Connection conn = new DBConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                System.out.println("Games Played: " + rs.getInt("total_games_played") + ", Total Time: " + rs.getInt("total_time_played"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getTotalGameTime() {
        String sql = "SELECT SUM(total_time_played) AS total_time FROM game_stats";
        int totalTime = 0;

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                totalTime = rs.getInt("total_time");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalTime;
    }

    public static int getLastGameCardsPlayed() {
        String sql = "SELECT CARDS_PLAYED FROM GAME_STATS ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY";
        int lastCardsPlayed = 0;

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

           if (rs.next()) {
               lastCardsPlayed = rs.getInt("CARDS_PLAYED");
           }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lastCardsPlayed;
}
    
    public static int getLastGameTime() {
        String sql = "SELECT TOTAL_TIME_PLAYED FROM GAME_STATS ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY";
        int lastGameTime = 0;

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastGameTime = rs.getInt("TOTAL_TIME_PLAYED");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return lastGameTime;
    }    
    
    
    
    
}
