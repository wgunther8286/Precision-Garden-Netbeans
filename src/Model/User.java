/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Model for the User class.
 * @author William Gunther
 */
public class User 
{
    private int userId;
    private String username;
    private String password;

    /** 
     * Constructor for User class.
     * @param userId Int value of User ID
     * @param username String value of Username
     * @param password String value of Password
     */
    public User (int userId, String username, String password) 
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /** Gets User ID
     * @return userId Integer value of Division ID*/
    public int getUserId() {
        return userId;
    }

    /** Sets User ID
     * @param userId Integer value of User ID*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Gets Username
     * @return username String value of username*/
    public String getUsername() {
        return username;
    }

    /** Sets Username
     * @param username String value of username*/
    public void setUsername(String username) {
        this.username = username;
    }

    /** Gets Password
     * @return password String value of password*/
    public String getPassword() {
        return password;
    }

    /** Sets Password
     * @param password String value of password*/
    public void setPassword(String password) {
        this.password = password;
    }
}

    

