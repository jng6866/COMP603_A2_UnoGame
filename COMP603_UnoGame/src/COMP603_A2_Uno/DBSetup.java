package COMP603_A2_Uno;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;


/**
 *
 * @author mufasa
 */
public class DBSetup {
    public static void initializeDatabase() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();

            statement.executeUpdate("CREATE TABLE players ("
                + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "name VARCHAR(100))");

            statement.executeUpdate("CREATE TABLE scores ("
                + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "player_id INT, "
                + "score INT, "
                + "FOREIGN KEY (player_id) REFERENCES players(id))");

            statement.executeUpdate("CREATE TABLE game_rounds ("
                + "round_number INT, "
                + "winner_id INT, "
                + "FOREIGN KEY (winner_id) REFERENCES players(id))");

            statement.executeUpdate("CREATE TABLE game_stats ("
                + "total_games_played INT, "
                + "total_time_played INT)");

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Tables already exist.");
            } else {
                e.printStackTrace();
            }
        }
    }
}