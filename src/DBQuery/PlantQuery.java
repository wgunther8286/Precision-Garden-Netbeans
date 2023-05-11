/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBQuery;

import Helper.JDBC;
import Model.Plant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author r3j20
 */
public class PlantQuery 
{
    
    public static ObservableList<Plant> getPlants()
    {
       ObservableList<Plant> allPlants = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM plant_data;";
            PreparedStatement ps = JDBC.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                Plant p = new Plant(
                        rs.getInt("plant_id"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType"),
                        rs.getInt("harvested"),
                        rs.getString("goal"),
                        rs.getTimestamp("start_date").toLocalDateTime()
                        );
                        
                        
                
                       
                allPlants.add(p);
            }
            return allPlants;
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    } 
    
    public static boolean deletePlant(int plantId) throws SQLException 
    {
        String iStatement = "DELETE from plant_data WHERE plant_id = ?";

        PSQuery.setPS(JDBC.getConn(), iStatement);
        PreparedStatement ps = PSQuery.getPS();

        ps.setInt(1, plantId);

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
    
    public static Plant getPlantByPlantID(int plantId) throws SQLException 
    {
        //ObservableList<Plant> plants = FXCollections.observableArrayList();

        String qStatement = "SELECT * FROM plant_data WHERE plant_id = ?;";

        PSQuery.setPS(JDBC.getConn(), qStatement);
        PreparedStatement ps = PSQuery.getPS();

        ps.setInt(1, plantId);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            
            while (rs.next()) 
            {
                Plant newPlant = new Plant(
                        rs.getInt("plant_id"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType"),
                        rs.getInt("harvested"),
                        rs.getString("goal"),
                        rs.getTimestamp("start_date").toLocalDateTime()
                        );
                     
                //plants.add(newPlant);
            
                return newPlant;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    
    
    public static ObservableList<Plant> getPlantsHarvested() throws SQLException 
    {
        ObservableList<Plant> plants = FXCollections.observableArrayList();

        int isHarv = 1;

        String qStatement = "SELECT * FROM plant_data WHERE harvested = ?;";

        PreparedStatement ps = JDBC.getConn().prepareStatement(qStatement);
   
        ps.setInt(1, isHarv);
        

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) 
            {
                Plant newPlant = new Plant(
                        rs.getInt("plant_id"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType"),
                        rs.getInt("harvested"),
                        rs.getString("goal"),
                        rs.getTimestamp("start_date").toLocalDateTime()
                    );
                        
                plants.add(newPlant);
            }
            return plants;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public static ObservableList<Plant> getPlantsStillGrowing() throws SQLException 
    {
        ObservableList<Plant> plants = FXCollections.observableArrayList();

        int isNotHarvested = 0;

        String qStatement = "SELECT * FROM plant_data  WHERE harvested = ?;";

        PreparedStatement ps = JDBC.getConn().prepareStatement(qStatement);
   
        ps.setInt(1, isNotHarvested);
        

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) 
            {
                Plant newPlant = new Plant(
                        rs.getInt("plant_id"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType"),
                        rs.getInt("harvested"),
                        rs.getString("goal"),
                        rs.getTimestamp("start_date").toLocalDateTime()
                    );
                        
                plants.add(newPlant);
            }
            return plants;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    //For future use
    /*
    public static ObservableList<Plant> getMaxHarvestByPlantId() throws SQLException 
    {
        ObservableList<Plant> plants = FXCollections.observableArrayList();

        int isNotHarvested = 0;

        String qStatement = "SELECT * FROM plant_data  WHERE harvested = ?;";

        PreparedStatement ps = JDBC.getConn().prepareStatement(qStatement);
   
        ps.setInt(1, isNotHarvested);
        

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) 
            {
                Plant newPlant = new Plant(
                        rs.getInt("plant_id"),
                        rs.getString("plant_type"),
                        rs.getString("plant_subType"),
                        rs.getInt("harvested"),
                        rs.getString("goal"),
                        rs.getTimestamp("start_date").toLocalDateTime()
                    );
                        
                plants.add(newPlant);
            }
            return plants;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
*/
    
    public static boolean createPlant(String plantType, String plantSubType, String goal, LocalDateTime startDate) throws SQLException 
    {

        String iStatement = "INSERT INTO plant_data(plant_type, plant_subType, goal, start_date) VALUES (?, ?, ?, ?)";

        PSQuery.setPS(JDBC.getConn(), iStatement);
        PreparedStatement ps = PSQuery.getPS();

        ps.setString(1, plantType);
        ps.setString(2, plantSubType);
        ps.setString(3, goal);
        ps.setTimestamp(4, Timestamp.valueOf(startDate));
        

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
    
    public static boolean updatePlant(String plantType, String plantSubType, String goal, LocalDateTime startDate, int plantId) throws SQLException 
    {

        String iStatement = "UPDATE plant_data SET plant_type=?, plant_subType=?, goal=?, start_date=? WHERE plant_id=?";

        PSQuery.setPS(JDBC.getConn(), iStatement);
        PreparedStatement ps = PSQuery.getPS();

        ps.setString(1, plantType);
        ps.setString(2, plantSubType);
        ps.setString(3, goal);
        ps.setTimestamp(4, Timestamp.valueOf(startDate));
        ps.setInt(5, plantId);
        
        

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
    
}
