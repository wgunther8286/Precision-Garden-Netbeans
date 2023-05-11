/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import DBQuery.GrowDataQuery;
import DBQuery.PlantQuery;
import DBQuery.UserQuery;
import Model.Grow_Data;
import Model.Plant;
import Model.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author r3j20
 */
public class Reports_Controller implements Initializable 
{
    
    @FXML
    private ComboBox<Integer> cboContact;

    @FXML
    private ComboBox<String> cboCountry;

    
    @FXML
    private RadioButton rbApptsMonth;

    @FXML
    private RadioButton rbApptsType;

    @FXML
    private RadioButton rbContactSche;

    @FXML
    private ComboBox<Integer> cboUserId;

    
    
    @FXML
    private void btnExit(ActionEvent event) throws IOException 
    {
        
        Main_Controller.alertWindow(4);
        
    }
    
    /**
     * Generates a report in the form of an Information Alert.
     * Report is based on which radio button is selected, type or month.
     * Report will list all appointments by type or month based on the respective radio button selection.
     * @param event 
     */
    @FXML
    void btnMonthTypeReport(ActionEvent event) 
    {
        
        ObservableList<String> globe = FXCollections.observableArrayList();
        ObservableList<String> roma = FXCollections.observableArrayList();
        ObservableList<String> cherry = FXCollections.observableArrayList();
        
        

        try {
            ObservableList<Plant> plants = PlantQuery.getPlants();

            if (plants != null) 
            {
                for (Plant plant : plants) 
                {
                    String subType = plant.getPlantSubType();
                    //LocalDate date = plant.getSDate();

                    if (subType.equals("Cherry")) 
                    {
                        cherry.add(subType);
                    }

                    if (subType.equals("Globe")) 
                    {
                        globe.add(subType);
                    }

                    if (subType.equals("Roma")) 
                    {
                        roma.add(subType);
                    }
                    
                    

                    
                    
                }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Plant Count by Sub-Type:");
                    alert.setContentText("Plants by Sub-Type: " +
                    "\nRoma: " + roma.size() +
                    "\nCherry: " + cherry.size() +
                    "\nGlobe: " + globe.size()
                    );
                    alert.setResizable(true);
                    alert.showAndWait();
                    
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    


        

        
    }

    

    @FXML
    private void btnBack(ActionEvent event) throws IOException 
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to go back?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) 
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Main_View.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();

    }
    }
    
    @FXML
    void btnReport2(ActionEvent event) 
    {
        
        Integer plantId = cboContact.getSelectionModel().getSelectedItem();
        try {
            ObservableList<Grow_Data> gd = GrowDataQuery.getGrowDayByPlantId(plantId);
            
            
            
            

            if (gd != null) 
            {
                for (Grow_Data growData: gd) 
                {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Plant Data and Grow Day");
                    alert.setContentText("Plant Grow Data" +
                            "\nPlant ID: " + growData.getPlantId() +
                            "\nType: " + growData.getPlantType() +
                            "\nSub-Type: " + growData.getPlantSubType() +
                            "\nDay of Growth: " + growData.getGrowDay() +
                            "\nWater: " + growData.getWater() +
                            "\nNutrient 1 and 2: " + growData.getNutrient1() + " " + growData.getNutrient2() +
                            "\nLight: " + growData.getLight()
                            );
                    if (growData.getGrowDay() == 0) {
                        
                        
                        alert.setTitle("Plant Data and Grow Day");
                        alert.setContentText("Plant does not have any grow day data");
                        
                    }

                    alert.setResizable(true);
                    alert.showAndWait();
                }
                 if (gd == null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Plant Data and Grow Day");
                        alert.setContentText("Plant does not have any grow day data");
                        }
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** Fill Contact ID cbo with Contact ID List
     */
    private void fillCboConId() {
        ObservableList<Integer> cboConIdLs = FXCollections.observableArrayList();

        try {
            ObservableList<Plant> plants = PlantQuery.getPlants();
            if (plants != null) {
                for (Plant plant: plants) {
                    if (!cboConIdLs.contains(plant.getPlantId())) {
                        cboConIdLs.add(plant.getPlantId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cboContact.setItems(cboConIdLs);
    }


    
    

    @FXML
    private void btnSignOut(ActionEvent event) throws IOException 
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to Sign Out?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) 
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Login_View.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    
    /** Fill User ID cbo with User ID List
     */
    private void fillCboUserId() {
        ObservableList<Integer> cboUserIdLs = FXCollections.observableArrayList();

        try {
            ObservableList<User> userId = UserQuery.getUsers();
            if (userId != null) {
                for (User user: userId) {
                    if (!cboUserIdLs.contains(user.getUserId())) {
                        cboUserIdLs.add(user.getUserId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cboUserId.setItems(cboUserIdLs);
    }
    
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        fillCboConId();
        
       
    }    
    
}
