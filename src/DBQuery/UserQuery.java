/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBQuery;
import java.sql.*;
import Helper.*;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Query for Users
 * @author William Gunther
 */

/**This class handles all the User queries.*/
public class UserQuery {
    
    private static User currUser;
    
    /**
     * Gets the current user
     * @return currUser
     */
    public static User getCurrentUser() 
    {
        return currUser;
    }
     /** Checks for valid username and pWord
     * @param username Username
     * @param password Password
     * @return Boolean Returns true if valid and false if not valid
     * @throws SQLException Catches SQLException.
     */
    public static boolean checkUnamePword(String username, String password) throws SQLException 
    {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";

        PreparedStatement ps = JDBC.getConn().prepareStatement(sql);
         
        ps.setString(1, username);
        ps.setString(2, password);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            return (rs.next());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /** Gets all Users 
     * @return ObservableList Returns list of Users
     * @throws SQLException Catches SQLException.
     */
    public static ObservableList<User> getUsers() throws SQLException 
    {
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.getConn().prepareStatement(sql);
                    

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String pWord = rs.getString("Password");
                User u = new User(id,name,pWord);

                users.add(u);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}
    

