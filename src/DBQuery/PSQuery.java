/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBQuery;

import java.sql.*;

/**
 * Query for Prepared Statements
 * @author William Gunther
 */
public class PSQuery {
    
private static PreparedStatement pStatement;

    /** Sets the Prepared Statement pStatement object
     * @param conn Database Connection
     * @param sqlStat SQL Statement string
     * @throws SQLException Catches SQLException.
     */
    public static void setPS(Connection conn, String sqlStat) throws SQLException {
        pStatement = conn.prepareStatement(sqlStat);
    }

    /** Returns the Prepared Statement
     * @return pStatement
     */
    public static PreparedStatement getPS() {
        return pStatement;
    }
    
}
