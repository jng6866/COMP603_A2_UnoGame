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

    DBConnection dbManager;
    Connection conn;
    Statement statement;

    public DBSetup() {
        dbManager = new DBConnection();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        DBSetup dbSetup = new DBSetup();

        try {

            dbSetup.statement.addBatch("CREATE TABLE players ("
                    + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                    + "name VARCHAR(100))");

            dbSetup.statement.addBatch("CREATE TABLE scores ("
                    + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                    + "player_id INT, "
                    + "score INT, "
                    + "FOREIGN KEY (player_id) REFERENCES players(id))");


            dbSetup.statement.executeBatch();
            System.out.println("Tables created successfully!");

        } catch (SQLException ex) {
            if (ex.getSQLState().equals("X0Y32")) { //catch existing table

                System.out.println("Table exists");
            } else {
                System.out.println(ex.getMessage()); //print error message
            }
        } finally {

            dbSetup.closeConnection(); //close connection
        }
    }

    public void closeConnection() {
        if (dbManager != null) {
            dbManager.closeConnections();
        }
    }
}