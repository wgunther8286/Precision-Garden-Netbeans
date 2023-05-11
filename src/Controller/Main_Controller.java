/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author r3j20
 */
public class Main_Controller implements Initializable 
{
    
    
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    void btnPlants(ActionEvent event) throws IOException 
    {
        
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Plants_View.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Plant Main");
        stage.show();

    }

    @FXML
    void btnGrowData(ActionEvent event) throws IOException 
    {
        
        Parent parent = FXMLLoader.load(getClass().getResource("/View/GrowData_View.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Grow Data Main");
        stage.show();

    }

    @FXML
    void btnExit(ActionEvent event) throws IOException 
    {
        
        alertWindow(4);
        
    }

    @FXML
    void btnReports(ActionEvent event) throws IOException 
    {
        
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Reports_View.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Reports");
        stage.show();

    }

    @FXML
    void btnSignOut(ActionEvent event) throws IOException 
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want sign out?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) 
        {
            returnToLogin(event);

        }
    }
    
     /**Return to login screen.  
      * When called, returns to the main form. 
      */
    private void returnToLogin(ActionEvent event) throws IOException 
    {

        Parent parent = FXMLLoader.load(getClass().getResource("/View/Login_View.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /** Alert window.List of different alert types that runs through out the program
    * @param alertType numerous alerts.
     * @throws java.io.IOException catches IOException.
    */
    public static void alertWindow(int alertType) throws IOException 
    {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        switch (alertType) 
        {
            case 1:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ALERT");
                alert.setHeaderText("Upcoming Appointment");
                alert.setContentText("Upcoming Appointment Within 15 Minutes.");
                alert.show();
                break;
            case 2:
                alert.setTitle("ALERT");
                alert.setHeaderText("No Upcoming Appointment");
                alert.setContentText("No Upcoming Appointment Within 15 Minutes.");
                alert.show();
                break;
            case 3:
                alert.setTitle("ERROR");
                alert.setHeaderText("Login Unsuccessful");
                alert.setContentText("Login Unsuccessful 1");
                alert.show();
                break;
            case 4:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("Are you sure you want exit");
                Optional<ButtonType> exitResult = alert.showAndWait();

                if (exitResult.isPresent() && exitResult.get() == ButtonType.OK) {
                System.exit(0);
                }
                break;
            case 5:
                alert.setTitle("ERROR");
                alert.setHeaderText("Search Field Empty");
                alert.setHeaderText("Search Field Must Be Filled.  If you searched by Plant Id, the Plant Id does not have a grow day yet.");
                alert.show();
                break;
            case 6:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Nutrients 2 Required");
                alert.show();
                break;    
            case 7:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Postal Code Required");
                alert.show();
                break;
            case 8:
                alert.setTitle("SAVE");
                alert.setHeaderText("Save");
                alert.setContentText("Click OK to Save.");
                alert.show();
                break;
            case 9:
                alert.setTitle("CANCEL");
                alert.setHeaderText("Cancel");
                alert.setContentText("Are you sure you want to cancel?");
                alert.show();
                break;
            case 10:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Nutrients 1 Required");
                alert.show();
                break;
            case 11:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Plant Id Required");
                alert.show();
                break;
            case 12:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ALERT");
                alert.setHeaderText("Are you sure you want to go back?");
                alert.setContentText("Press Yes to go back");
                Optional<ButtonType> result = alert.showAndWait();
                alert.show();
                break;
            case 13:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Water Required");
                alert.show();
                break;
            case 14:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Loading Screen");
                alert.setContentText("Error Loading Screen");
                alert.show();
                break;
            case 15:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setHeaderText("Goal Required");
                alert.show();
                break;
            case 16:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Plant Sub-Type Required");
                alert.show();
                break;
            case 17:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Plant Type Required");
                alert.show();
                break;
            case 18:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Light Required");
                alert.show();
                break;
            case 19:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Start Date Required");
                alert.show();
                break;
            case 20:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Grow Day Required");
                alert.show();
                break;
            case 21:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Customer ID Required");
                alert.show();
                break;    
            case 22:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("User ID Required");
                alert.show();
                break;
            case 23:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("End Date and Time must be after Start Date and Time");
                alert.show();
                break;    
            case 24:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Start Date and Time must be before End Date and Time.");
                alert.show();
                break;
            case 25:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Appointments must start and end on the same date.");
                alert.show();
                break;    
            case 26:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Appointments must not overlap with existing appointments.");
                alert.show();
                break;
            case 27:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Appointments must be between 8AM - 10PM EST.");
                alert.show();
                break;      
            case 28:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Data");
                alert.setContentText("Type Required");
                alert.show();
                break;       
        }
        }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
