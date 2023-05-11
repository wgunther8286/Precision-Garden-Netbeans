/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBQuery;

import Helper.JDBC;
import Model.Grow_Data;
import Model.Plant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author r3j20
 */
public class GrowDataQuery {
    
    public static ObservableList<Grow_Data> getGrowData()
    {
       ObservableList<Grow_Data> allGrowData = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM grow_data;";
            PreparedStatement ps = JDBC.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                Grow_Data gd = new Grow_Data(
                        rs.getInt("grow_id"),
                        rs.getDouble("water"),
                        rs.getInt("nutrient1"),
                        rs.getInt("nutrient2"),
                        rs.getInt("light"),
                        rs.getInt("plant_id"),
                        rs.getInt("grow_day"),
                        rs.getInt("daily_harvest")
                        );
                        
                        
                
                       
                allGrowData.add(gd);
            }
            return allGrowData;
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    } 
    
    public static ObservableList<Grow_Data> getAllGrowData()
    {
       ObservableList<Grow_Data> allGrowData = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM grow_data AS g INNER JOIN plant_data AS p ON g.plant_id=p.plant_id;";
            PreparedStatement ps = JDBC.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                Grow_Data gd = new Grow_Data(
                        rs.getInt("grow_id"),
                        rs.getDouble("water"),
                        rs.getInt("nutrient1"),
                        rs.getInt("nutrient2"),
                        rs.getInt("light"),
                        rs.getInt("plant_id"),
                        rs.getInt("grow_day"),
                        rs.getInt("daily_harvest"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType")
                        );
                        
                        
                
                       
                allGrowData.add(gd);
            }
            return allGrowData;
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    } 
    
    public static ObservableList<Grow_Data> getGrowDayByPlantId(int plantId) throws SQLException
    {
       ObservableList<Grow_Data> growDay = FXCollections.observableArrayList();
       String sql = "SELECT * FROM grow_data AS g INNER JOIN plant_data AS p ON g.plant_id=p.plant_id WHERE g.plant_id=?";
       PreparedStatement ps = JDBC.getConn().prepareStatement(sql);
       
       ps.setInt(1, plantId);
       
        try {
            

            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) 
            {
                Grow_Data gd = new Grow_Data(
                        rs.getInt("grow_id"),
                        rs.getDouble("water"),
                        rs.getInt("nutrient1"),
                        rs.getInt("nutrient2"),
                        rs.getInt("light"),
                        rs.getInt("plant_id"),
                        rs.getInt("grow_day"),
                        rs.getInt("daily_harvest"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType")
                        );
                        
                        
                
                       
                growDay.add(gd);
            }
            return growDay;
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    } 
    
    public static boolean deleteGrowData(int growId) throws SQLException 
    {
        String iStatement = "DELETE FROM grow_data WHERE grow_Id=?";

        PSQuery.setPS(JDBC.getConn(), iStatement);
        PreparedStatement ps = PSQuery.getPS();

        ps.setInt(1, growId);

        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No change");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean createGrowDay (double water, int nut1, int nut2, int light, int plantId, int growDay, int dailyHarv) throws SQLException {
        
        String iStatement = "INSERT INTO grow_data(water, nutrient1, nutrient2, light, plant_id, grow_day, daily_harvest) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PSQuery.setPS(JDBC.getConn(), iStatement);
        PreparedStatement ps = PSQuery.getPS();
        
        ps.setDouble(1, water);
        ps.setInt(2, nut1);
        ps.setInt(3, nut2);
        ps.setInt(4, light);
        ps.setInt(5, plantId);
        ps.setInt(6, growDay);
        ps.setInt(7, dailyHarv);
       
        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No change");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        }
    
        public static boolean updateGrowDay (double water, int nut1, int nut2, int light, int plantId, int growDay, int dailyHarv, int growId) throws SQLException {
        
        String iStatement = "UPDATE grow_data SET water=?, nutrient1=?, nutrient2=?, light=?, plant_id=?, grow_day=?, daily_harvest=? WHERE grow_id=?";
        
        PSQuery.setPS(JDBC.getConn(), iStatement);
        PreparedStatement ps = PSQuery.getPS();
        
        ps.setDouble(1, water);
        ps.setInt(2, nut1);
        ps.setInt(3, nut2);
        ps.setInt(4, light);
        ps.setInt(5, plantId);
        ps.setInt(6, growDay);
        ps.setInt(7, dailyHarv);
        ps.setInt(8, growId);
       
        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No change");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        }
        
        public static Grow_Data getGrowDataByGrowId(int growId) throws SQLException
    {
       
       String sql = "SELECT * FROM grow_data AS g INNER JOIN plant_data AS p ON g.plant_id=p.plant_id WHERE g.grow_id=?;";
       
       PreparedStatement ps = JDBC.getConn().prepareStatement(sql);
       
       ps.setInt(1, growId);
       
        try {
            

            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) 
            {
                Grow_Data gd = new Grow_Data(
                        rs.getInt("grow_id"),
                        rs.getDouble("water"),
                        rs.getInt("nutrient1"),
                        rs.getInt("nutrient2"),
                        rs.getInt("light"),
                        rs.getInt("plant_id"),
                        rs.getInt("grow_day"),
                        rs.getInt("daily_harvest"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType")
                        );
                        
                        return gd;
                
                       
                
            }
            
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    } 
    }
    

