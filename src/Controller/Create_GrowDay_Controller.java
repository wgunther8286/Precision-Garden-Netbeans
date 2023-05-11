/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DBQuery.GrowDataQuery;
import DBQuery.PlantQuery;
import Model.Grow_Data;
import Model.Plant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author r3j20
 */
public class Create_GrowDay_Controller implements Initializable
{
    
    @FXML
    private ComboBox<Integer> cboPlantId;
    /*
    @FXML
    private ComboBox<Integer> cboGrowDay;
    */
    @FXML
    private Label lblDailyHarv;

    @FXML
    private Label lblGrowDay;

    @FXML
    private Label lblGrowId;

    @FXML
    private Label lblLight;

    @FXML
    private Label lblNut1;

    @FXML
    private Label lblNut2;

    @FXML
    private Label lblPlantId;

    @FXML
    private Label lblWater;

    @FXML
    private ComboBox<Integer> cboDailyHarv;

    @FXML
    private ComboBox<Integer> cboLight;

    @FXML
    private ComboBox<Integer> cboNewGrowDay;

    @FXML
    private ComboBox<Integer> cboNut1;

    @FXML
    private ComboBox<Integer> cboNut2;

   
    @FXML
    private ComboBox<Double> cboWater;
    
    private static Grow_Data selPlant;
    
    public static void rxSelPlant(Grow_Data plant) 
     {
        selPlant = plant;
    }


    
    private void fillCboPlantId() {
        ObservableList<Integer> cboPlantIdLs = FXCollections.observableArrayList();

        ObservableList<Plant> plantId = PlantQuery.getPlants();
        if (selPlant != null){
         if (plantId != null) {
            for (Plant plants: plantId) {
                cboPlantIdLs.add(selPlant.getPlantId());
            }
        }

        cboPlantId.setItems(cboPlantIdLs);
    }
        else if (selPlant == null) {
            if (plantId != null) {
                for (Plant plants: plantId) {
                    cboPlantIdLs.add(plants.getPlantId());
                }
            }
        cboPlantId.setItems(cboPlantIdLs);
        }
    }
    /*
    private void fillCboGrowDay() throws SQLException {
        ObservableList<Integer> cboGrowDayLs = FXCollections.observableArrayList();
          
        ObservableList<Grow_Data> growDay = GrowDataQuery.getAllGrowData();
                  
        if (growDay != null) {
            for (Grow_Data growData: growDay) {
                cboGrowDayLs.add(growData.getGrowDay());
            }
        }

        cboGrowDay.setItems(cboGrowDayLs);
    }
    */
    private void fillCboWater() {
        ObservableList<Double> cboWaterLs = FXCollections.observableArrayList();

        cboWaterLs.addAll(0.0, 1.0, 1.25, 1.50, 1.75, 2.0, 2.5, 3.0, 3.5, 4.0, 5.0);

        cboWater.setItems(cboWaterLs);
    }
    
    private void fillCboLight() {
        ObservableList<Integer> cboLightLs = FXCollections.observableArrayList();

        cboLightLs.addAll(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);

        cboLight.setItems(cboLightLs);
    }
    
    private void fillCboNut1() {
        ObservableList<Integer> cboNut1Ls = FXCollections.observableArrayList();

        cboNut1Ls.addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        cboNut1.setItems(cboNut1Ls);
    }
    
    private void fillCboNut2() {
        ObservableList<Integer> cboNut2Ls = FXCollections.observableArrayList();

        cboNut2Ls.addAll(0,1, 2, 3, 4, 5, 6, 7, 8, 9);

        cboNut2.setItems(cboNut2Ls);
    }
    
    private void fillCboNewGrowDay() {
        ObservableList<Integer> cboNewGrowDayLs = FXCollections.observableArrayList();

        cboNewGrowDayLs.addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50);

        cboNewGrowDay.setItems(cboNewGrowDayLs);
    }
    
    private void fillCboDailyHarv() {
        ObservableList<Integer> cboDailyHarvLs = FXCollections.observableArrayList();

        cboDailyHarvLs.addAll(0,1,2,3,4,5);

        cboDailyHarv.setItems(cboDailyHarvLs);
    }
    
   
    
    

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to go back?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) 
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/GrowData_View.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Grow Data");
            stage.show();

    }

    }

    @FXML
    void btnExit(ActionEvent event) throws IOException {
        
        Main_Controller.alertWindow(4);

    }

    @FXML
    void btnPlantData(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to go to the Plant Data Screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) 
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Plants_View.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Plant Data");
            stage.show();

    }

    }

    @FXML
    void btnSave(ActionEvent event) throws IOException, SQLException {
        
        boolean filled = checkNewGrowDay();
        
        if (filled)
            try {
                boolean isFilled = GrowDataQuery.createGrowDay(
                        cboWater.getSelectionModel().getSelectedItem(),
                        cboNut1.getSelectionModel().getSelectedItem(),
                        cboNut2.getSelectionModel().getSelectedItem(), 
                        cboLight.getSelectionModel().getSelectedItem(), 
                        cboPlantId.getSelectionModel().getSelectedItem(), 
                        cboNewGrowDay.getSelectionModel().getSelectedItem(),
                        cboDailyHarv.getSelectionModel().getSelectedItem());
                
                if (isFilled) 
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New Grow Day Created");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && (result.get() ==  ButtonType.OK)) 
                    {
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/GrowData_View.fxml"));
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setTitle("Grow Data");
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Main_Controller.alertWindow(14);
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Grow Data not saved");
                    Optional<ButtonType> result = alert.showAndWait();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            

    
    
    private boolean checkNewGrowDay() throws IOException {
        
        if (cboWater.getSelectionModel().getSelectedItem() == null)
        {
            Main_Controller.alertWindow(13);
            return false;
        }
        
        if (cboLight.getSelectionModel().getSelectedItem() == null)
        {
            Main_Controller.alertWindow(18);
            return false;
        }
        if (cboPlantId.getSelectionModel().getSelectedItem() == null)
        {
            Main_Controller.alertWindow(11);
            return false;
        }
        if (cboNut1.getSelectionModel().getSelectedItem() == null) 
        {
            Main_Controller.alertWindow(10);
            return false;
        }
        if (cboNut2.getSelectionModel().getSelectedItem() == null) 
        {
            Main_Controller.alertWindow(6);
            return false;
        }
        if (cboNewGrowDay.getSelectionModel().getSelectedItem() == null) 
        {
            Main_Controller.alertWindow(20);
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCboWater();
        fillCboPlantId();
        fillCboLight();
        fillCboNut1();
        fillCboNut2();
        fillCboNewGrowDay();
        fillCboDailyHarv();
        /*
        if(cboPlantId.getSelectionModel().getSelectedItem() != null) {
            try {
                fillCboGrowDay();
            } catch (SQLException ex) {
                Logger.getLogger(Create_GrowDay_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        */
    
    }
}
    

