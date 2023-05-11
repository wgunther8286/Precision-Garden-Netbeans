/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.sql.*;


/**
 * JDBC is the class used to connect to the MySql DB
 * @author William Gunther
 */
public abstract class JDBC {
    
        
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "poimnb789!!"; // Password
    //private static String password = "Passw0rd!"; // Password
    
    /**
     * Connection
     */
    public static Connection conn;  
    
    /**
     * Establishes connection with DB.
     * @return conn
     */
    public static Connection getConn() {
        return conn;
    }
    
    /**
     * Starts connection
     * @return conn
     * @throws SQLException catches SQL Exception.
     */
    public static Connection startConn() throws SQLException
    {
        try {
            Class.forName(driver); 
            conn = DriverManager.getConnection(jdbcUrl, userName, password); 
            System.out.println("Connection successful!");
        }
        catch(SQLException e)
        {
            
            e.printStackTrace();
        } catch(ClassNotFoundException e)
        {
            
            e.printStackTrace();
        }
        return conn;
    }
    
    /**
     * Closes connection with DB.
     */
    public static void closeConn() {
        try {
            conn.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}

