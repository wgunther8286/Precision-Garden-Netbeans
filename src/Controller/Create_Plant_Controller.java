/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DBQuery.PlantQuery;
import Model.Plant;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author r3j20
 */
public class Create_Plant_Controller implements Initializable {
    
    @FXML
    private ComboBox<String> cboGoal;

    @FXML
    private ComboBox<String> cboPlantSubType;

    @FXML
    private ComboBox<String> cboPlantType;
    
    @FXML
    private ComboBox<String> cboSTime;


    @FXML
    private DatePicker dpSDate;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblCustId;

    @FXML
    private Label lblLoc1;

    @FXML
    private Label lblSDate;

    @FXML
    private Label lblType;

    @FXML
    private TextField txtPlantId;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to return to Main Plants form?");
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
    void btnExit(ActionEvent event) throws IOException {
        
        Main_Controller.alertWindow(4);

    }

    @FXML
    void btnGrowData(ActionEvent event) throws IOException {
        
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

    }

    @FXML
    void btnSave(ActionEvent event) throws IOException {
        
        boolean filled = checkNewPlant();
        
        if (filled)
        {
            try {
                boolean isFilled = PlantQuery.createPlant(
                        cboPlantType.getSelectionModel().getSelectedItem(), 
                        cboPlantSubType.getSelectionModel().getSelectedItem(), 
                        cboGoal.getSelectionModel().getSelectedItem(), 
                        LocalDateTime.of(dpSDate.getValue(), LocalTime.parse(cboSTime.getSelectionModel().getSelectedItem())));
            
        if (isFilled) 
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New Plant Created");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && (result.get() ==  ButtonType.OK)) 
                    {
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/Plants_View.fxml"));
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setTitle("Plant Data");
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Main_Controller.alertWindow(14);
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Plant not saved");
                    Optional<ButtonType> result = alert.showAndWait();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    
    private boolean checkNewPlant() throws IOException {
        
        if (cboGoal.getSelectionModel().isEmpty())
        {
            Main_Controller.alertWindow(15);
            return false;
        }
        
        if (cboPlantSubType.getSelectionModel().isEmpty())
        {
            Main_Controller.alertWindow(16);
            return false;
        }
        if (cboPlantType.getSelectionModel().isEmpty())
        {
            Main_Controller.alertWindow(17);
            return false;
        }
        if (dpSDate.getValue() == null) 
        {
            Main_Controller.alertWindow(19);
            return false;
        }
        return true;
    }
    
    private void fillcboTime() {
        ObservableList<String> newTime = FXCollections.observableArrayList();
        LocalTime sTime = LocalTime.of(7, 0);
        LocalTime eTime = LocalTime.of(23, 0);

        newTime.add(sTime.toString());
        while (sTime.isBefore(eTime)) {
            sTime = sTime.plusMinutes(15);
            newTime.add(sTime.toString());
        }

        cboSTime.setItems(newTime);
            }
    
    private void fillCboPlantType() throws IOException {
        ObservableList<String> cboPlantTypeLs = FXCollections.observableArrayList();

        cboPlantTypeLs.addAll("Tomato", "Spinach");

        cboPlantType.setItems(cboPlantTypeLs);
    }
    
    private void fillCboPlantSubType() throws IOException {
        ObservableList<String> cboPlantSubTypeLs = FXCollections.observableArrayList();

        cboPlantSubTypeLs.addAll("Roma", "Cherry", "Globe", "Smooth", "Savory");

        cboPlantSubType.setItems(cboPlantSubTypeLs);
    }
    
    private void fillCboGoal() throws IOException {
        ObservableList<String> cboGoalLs = FXCollections.observableArrayList();

        cboGoalLs.addAll("Harvest", "Water", "Energy");

        cboGoal.setItems(cboGoalLs);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         try {
             fillCboGoal();
             fillCboPlantType();
             fillCboPlantSubType();
             fillcboTime();
         } catch (IOException ex) {
             Logger.getLogger(GrowData_Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}

