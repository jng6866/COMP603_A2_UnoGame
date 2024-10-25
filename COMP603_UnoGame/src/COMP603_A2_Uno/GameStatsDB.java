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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameStatsDB {

    public static void addGameStats(int cardsPlayed, int lastGameTime) {
        String sql = "INSERT INTO GAME_STATS (cards_played, last_game_time) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cardsPlayed);
            pstmt.setInt(2, lastGameTime);
            pstmt.executeUpdate();
            System.out.println("Game stats added: Cards Played = " + cardsPlayed + ", Last Game Time = " + lastGameTime);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getTotalGameTime() {
        String sql = "SELECT SUM(last_game_time) AS total_time FROM GAME_STATS";
        int totalTime = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                totalTime = rs.getInt("total_time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalTime;
    }

    public static int getTotalGamesPlayed() {
        String sql = "SELECT COUNT(*) AS total_games FROM GAME_STATS";
        int totalGames = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                totalGames = rs.getInt("total_games");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalGames;
    }

    public static int getLastGameCardsPlayed() {
        String sql = "SELECT cards_played FROM GAME_STATS ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
        int lastCardsPlayed = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastCardsPlayed = rs.getInt("cards_played");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastCardsPlayed;
    }

    public static int getLastGameTime() {
        String sql = "SELECT last_game_time FROM GAME_STATS ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
        int lastGameTime = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                lastGameTime = rs.getInt("last_game_time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastGameTime;
    }
}