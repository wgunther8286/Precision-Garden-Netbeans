/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBQuery;

import static Controller.Main_Controller.alertWindow;
import Helper.JDBC;
import Model.*;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.LocalDateTime;



/**
 * Query for Login
 * @author William Gunther
 */
public class LoginQuery {
    
    private static User currentUser;
    /**
     * Ensures that anyone trying to enter the system is an authorized user
     * Uses a lambda to assign the current user
     * @param id User ID
     * @param pwd User Password
     * @return boolean success
     * @throws SQLException catches SQLException
     * @throws java.io.IOException catches IOException
     */
    public static boolean login(int id, String pwd) throws SQLException, IOException 
    {
        boolean success = false;
        ObservableList<User> users = FXCollections.observableArrayList();
        users = UserQuery.getUsers();
        try {
            String qs = "SELECT User_ID,User_Name, Password FROM users WHERE ? = User_ID";
            PreparedStatement ps = JDBC.getConn().prepareStatement(qs);

            ps.setString(1, String.valueOf(id));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String password = rs.getString("Password");
                int userID = rs.getInt("User_ID");

                if (password.equals(pwd)) 
                {
                    
                    users.forEach(User -> {if(User.getUserId() == userID){currentUser = User;}});
                    success = true;
                    System.out.println(password + " " + userID);

                   

                    
                }
            }

            System.out.println(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }


}
