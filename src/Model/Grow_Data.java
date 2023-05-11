/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author r3j20
 */
public class Grow_Data 
{
    private int growId;
    private double water;
    private int nutrient1;
    private int nutrient2;
    private int light;
    private int plantId;
    private int growDay;
    private int dailyHarvest;
    private String plantType;
    private String plantSubType;
    
        public Grow_Data 
                (
                int growId,
                double water,
                int nutrient1,
                int nutrient2,
                int light,
                int plantId,
                int growDay,
                int dailyHarvest
                                
                ) {
                    
                    this.growId = growId;
                    this.water = water;
                    this.nutrient1 = nutrient1;
                    this.nutrient2 = nutrient2;
                    this.light = light;
                    this.plantId = plantId;
                    this.growDay = growDay;
                    this.dailyHarvest = dailyHarvest;
                    
                }
                
                public Grow_Data 
                (
                int growId,
                double water,
                int nutrient1,
                int nutrient2,
                int light,
                int plantId,
                int growDay,
                int dailyHarvest,
                String plantType,
                String plantSubType

    
                                                                                                
    
                                                                
                ) {
                    
                    this.growId = growId;
                    this.water = water;
                    this.nutrient1 = nutrient1;
                    this.nutrient2 = nutrient2;
                    this.light = light;
                    this.plantId = plantId;
                    this.growDay = growDay;
                    this.dailyHarvest = dailyHarvest;
                    this.plantType = plantType;
                    this.plantSubType = plantSubType;
                    
                }

    public int getDailyHarvest() {
        return dailyHarvest;
    }

    public void setDailyHarvest(int dailyHarvest) {
        this.dailyHarvest = dailyHarvest;
    }

    public Grow_Data(int dailyHarvest) {
        this.dailyHarvest = dailyHarvest;
    }

    public int getGrowId() {
        return growId;
    }

    public void setGrowId(int growId) {
        this.growId = growId;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public int getNutrient1() {
        return nutrient1;
    }

    public void setNutrient1(int nutrient1) {
        this.nutrient1 = nutrient1;
    }

    public int getNutrient2() {
        return nutrient2;
    }

    public void setNutrient2(int nutrient2) {
        this.nutrient2 = nutrient2;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getGrowDay() {
        return growDay;
    }

    public void setGrowDay(int growDay) {
        this.growDay = growDay;
    }
    
    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantSubType() {
        return plantSubType;
    }

    public void setPlantSubType(String plantSubType) {
        this.plantSubType = plantSubType;
    }
                                
    
    
}
