package COMP603_A2_Uno;

import java.sql.Connection;
import java.sql.SQLException;

public class DBSetup {
    public static void initializeDatabase() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Failed to establish a connection to the database.");
                return;
            }

            System.out.println("Database connection established successfully.");

            // Now you can proceed with inserting, updating, or querying the database

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}