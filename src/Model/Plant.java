/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author r3j20
 */
public class Plant {
      
    private int plantId;
    private String plantType;
    private String plantSubType;
    private int harvested;
    private String goal;
    private LocalDateTime startDate;

    

    
    
    public Plant (
                   
            int plantId,
            String plantType,
            String plantSubType,
            int harvested
            
           ) {
    
           this.plantId = plantId;
           this.plantType = plantType;
           this.plantSubType = plantSubType;
           this.harvested = harvested;
}
    
    
    public Plant (
                   
            int plantId,
            String plantType,
            String plantSubType,
            int harvested,
            String goal,
            LocalDateTime startDate
            
           ) {
    
           this.plantId = plantId;
           this.plantType = plantType;
           this.plantSubType = plantSubType;
           this.harvested = harvested;
           this.goal = goal;
           this.startDate = startDate;
}

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
    
    
       
    
        
     /**
      * getter for plantId
      * @return plantId
      */   
    public int getPlantId() {
        return plantId;
    }
    
    /**
     * Setter for plantId
     * @param plantId 
     */
    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    /**
     * Getter for plantType
     * @return pllantType
     */
    public String getPlantType() {
        return plantType;
    }
    
    /**
     * Setter for plantType
     * @param plantType 
     */
    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }
    
    /**
     * Getter for plantSubType
     * @return plantSubType
     */
    public String getPlantSubType() {
        return plantSubType;
    }
    
    /**
     * Setter for plantSubType
     * @param plantSubType 
     */
    public void setPlantSubType(String plantSubType) {
        this.plantSubType = plantSubType;
    }

    /**
     * Gets whether the plant has been harvested
     * @return harvested 
     */
    public int isHarvested() {
        return harvested;
    }
    /**
     * Setter for harvested
     * @param harvested 
     */
    public void setHarvested(int harvested) {
        this.harvested = harvested;
    }

    

    /**
     * Getter for totalHarvest
     * @return totalHarvest
     */
    
}

           
    

