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
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {
    public static void initializeDatabase() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Failed to establish a connection to the database.");
                return;
            }

            System.out.println("Database connection established successfully.");

            // create tables after checking for previous instances
            try (Statement stmt = conn.createStatement()) {
                // players table
                try {
                    stmt.executeUpdate("CREATE TABLE PLAYERS (" +
                            "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                            "name VARCHAR(50) NOT NULL, " +
                            "score INT DEFAULT 0)");
                } catch (SQLException e) {
                    if (!e.getSQLState().equals("X0Y32")) { // code for existing tables
                        throw e;
                    }
                }

                // game stats
                try {
                    stmt.executeUpdate("CREATE TABLE GAME_STATS (" +
                            "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                            "cards_played INT DEFAULT 0, " +
                            "last_game_time INT DEFAULT 0)");
                } catch (SQLException e) {
                    if (!e.getSQLState().equals("X0Y32")) {    // code for existing tables
                        throw e;
                    }
                }

                // scores table
                try {
                    stmt.executeUpdate("CREATE TABLE SCORES (" +
                            "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                            "player_id INT REFERENCES PLAYERS(id), " +
                            "score INT DEFAULT 0)");
                } catch (SQLException e) {
                    if (!e.getSQLState().equals("X0Y32")) { // code for existing tables
                        throw e;
                    }
                }

                System.out.println("Tables initialized successfully.");
            } catch (SQLException e) {
                System.out.println("Error while initializing tables: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}