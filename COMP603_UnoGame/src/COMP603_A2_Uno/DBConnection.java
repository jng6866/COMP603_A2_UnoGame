package COMP603_A2_Uno;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnection {


    private static final String URL = "jdbc:derby:GameDB;create=true";

    private static final String USER_NAME = "test";
    private static final String PASSWORD = "test"; 
    Connection conn;

    public DBConnection() {
        establishConnection();
    }

    public static void main(String[] args) throws SQLException {
        DBConnection dbConnection = new DBConnection();
        System.out.println(dbConnection.getConnection());
    }
    
    

    public static Connection getConnection() throws SQLException {
    try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        System.out.println("Driver loaded successfully!");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException("Driver not found");
    }

    return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
}


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

    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}