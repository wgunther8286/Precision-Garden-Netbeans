/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import DBQuery.PlantQuery;
import Model.Plant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/** 
 * FXML Controller Class.
 * Controller for the Appointments_View.fxml.
 * 
 */
public class Plants_Controller implements Initializable 
{
    
    @FXML
    private TableView<Plant> tblPlants;

    

    @FXML
    private TableColumn<Plant, Integer> colPlantId;
    
     @FXML
    private TableColumn<Plant, String> colPlantType;

    @FXML
    private TableColumn<Plant, String> colSubType;
    
    
   @FXML
    private TableColumn<Plant, String> colGoal;
    
        
    @FXML
    private TableColumn<Plant, LocalDate> colStartDate;

    @FXML
    private RadioButton radBtnHarvested;

    @FXML
    private RadioButton radBtnStillGrowing;
    
    @FXML
    private RadioButton radBtnAll;

    @FXML
    private ToggleGroup radioBtnToggle;

    

    
    
    
    static ObservableList<Plant> plants;
    
    /**
     * Button for the Customers page.
     * @param event when clicked, moves user to the customers page.
     * @throws IOException 
     */
    @FXML
    void btnGrowData(ActionEvent event) throws IOException 
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to go to the Grow Data form?");
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
    /**
     * Button to delete the selected plant.
     * @param event when clicked, deletes the plant selected by the user.
     */
    @FXML
    void btnDeletePlant(ActionEvent event) 
    {
        
        Plant selPlant = tblPlants.getSelectionModel().getSelectedItem();
        if (selPlant == null) 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a plant to delete.");
            alert.showAndWait();
        } else if (tblPlants.getSelectionModel().getSelectedItem() != null) 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete Plant: Plant ID:  " + selPlant.getPlantId() + " ? "
                    + "\nPress ok to Delete?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                try {
                    boolean delSuccessful = PlantQuery.deletePlant(tblPlants.getSelectionModel().getSelectedItem().getPlantId());

                    if (delSuccessful) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Successful Delete");
                        alert.setContentText("Deleted Plant ID: " + selPlant.getPlantId() + " Type: " + selPlant.getPlantType());
                        alert.showAndWait();

                        plants = PlantQuery.getPlants();
                        tblPlants.setItems(plants);
                        tblPlants.refresh();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("Could not delete plant.");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * Button for the Update screen.
     * @param event When clicked, moves user to the update appointment view with the selected appointment data.
     */
    @FXML
    void btnUpdate(ActionEvent event) 
    {
        
        Update_Plant_Controller.rxSelPlant(tblPlants.getSelectionModel().getSelectedItem());

        if (tblPlants.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Update_Plant_View.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Update Existing Plant");
                
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Load Screen Error.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("You must select a plant to update.");
            alert.showAndWait();
        }

    }
    /**
     * Button to exit the application
     * @param event When clicked, Displays confirmation screen to ensure user wants to exit the application.
     * @throws IOException 
     */
    @FXML
    void btnExit(ActionEvent event) throws IOException 
    {
        
        Main_Controller.alertWindow(4);
    }
    /**
     * Button for the create appointment screen.
     * @param event When clicked, moves the user to the create appointment view.
     * @throws IOException 
     */
    @FXML
    void btnNewPlant(ActionEvent event) throws IOException 
    {
        
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Create_Plant_View.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Create New Plant");
        stage.show();

    }

    @FXML
    void btnBack(ActionEvent event) throws IOException 
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to return to main form?");
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
    void ViewToggle(ActionEvent event) 
    {
        
        if (radBtnHarvested.isSelected()) 
        {
            try {
                plants = PlantQuery.getPlantsHarvested();
                tblPlants.setItems(plants);
                tblPlants.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (radBtnStillGrowing.isSelected()) {
            try {
                plants = PlantQuery.getPlantsStillGrowing();
                tblPlants.setItems(plants);
                tblPlants.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        } 
        else if (radBtnAll.isSelected())   {
            plants = PlantQuery.getPlants();
            tblPlants.setItems(plants);
            tblPlants.refresh();
        
        }   
    }
    /**
     * Initializes controller class
     * @param url url
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        radBtnHarvested.setToggleGroup(radioBtnToggle);
        radBtnStillGrowing.setToggleGroup(radioBtnToggle);
        radBtnAll.setToggleGroup(radioBtnToggle);
        
        plants = PlantQuery.getPlants();
        tblPlants.setItems(plants);
        colPlantId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        colPlantType.setCellValueFactory(new PropertyValueFactory<>("plantType"));
        colSubType.setCellValueFactory(new PropertyValueFactory<>("plantSubType"));
        colGoal.setCellValueFactory(new PropertyValueFactory<>("goal"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        
    }

    }


    
    

