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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    // url for embeded db
    private static final String URL = "jdbc:derby:GameDB;create=true";

    private static final String USER_NAME = ""; //default
    private static final String PASSWORD = ""; //default
    Connection conn;
    
    //constructor for connections
    public DBConnection() {
        establishConnection();
    }

    
    // method for testing connection
    public static void main(String[] args) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        System.out.println(dbConnection.getConnection());
    }

    
    //establishes connection with driver
    public static Connection getConnection() throws SQLException {
    try {
        //db driver
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        System.out.println("Driver loaded successfully!");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException("Driver not found");
    }
    return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
}

    //establish if not already
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}