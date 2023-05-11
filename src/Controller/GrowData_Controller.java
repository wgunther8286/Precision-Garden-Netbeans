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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author r3j20
 */
public class GrowData_Controller implements Initializable {
    
     @FXML
    private TableColumn<Grow_Data, Integer> colDailyHarvest;

    @FXML
    private TableColumn<Grow_Data, Integer> colGrowDay;

    @FXML
    private TableColumn<Grow_Data, Integer> colGrowId;

    @FXML
    private TableColumn<Grow_Data, Integer> colLight;

    @FXML
    private TableColumn<Grow_Data, Integer> colNutrient1;

    @FXML
    private TableColumn<Grow_Data, Integer> colNutrient2;

    @FXML
    private TableColumn<Grow_Data, Integer> colPlantId;

    @FXML
    private TableColumn<Grow_Data, Double> colWater;
    
    @FXML
    private TableColumn<Grow_Data, String> colSubType;

    @FXML
    private TableColumn<Grow_Data, String> colType;

    @FXML
    private TableView<Grow_Data> tblGrowData;
    
     @FXML
    private ChoiceBox<Integer> cboPlantId;

    @FXML
    private ChoiceBox<String> cboPlantSubType;

    @FXML
    private ChoiceBox<String> cboPlantType;
           
    @FXML
    private TextField txtPlantSearch;
    
    static ObservableList<Grow_Data> growData;
    
    private void fillCboPlantId() throws IOException {
        ObservableList<Integer> cboPlantIdLs = FXCollections.observableArrayList();

        try {
            ObservableList<Plant> plantId = PlantQuery.getPlants();
            if (plantId != null) {
                for (Plant plant: plantId) {
                    cboPlantIdLs.add(plant.getPlantId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cboPlantId.setItems(cboPlantIdLs);
        /*
        int tblPlantId = cboPlantId.getSelectionModel().getSelectedItem();
        ObservableList<Grow_Data> foundGD = FXCollections.observableArrayList();
        ObservableList<Grow_Data> gdList = GrowDataQuery.getAllGrowData();
        
        for (Grow_Data srchPlant : gdList) 
        {
          if(tblPlantId == srchPlant.getPlantId())
                    
          {
              foundGD.add(srchPlant);
          }
        }
        tblGrowData.setItems(foundGD);
        if(foundGD.isEmpty())
        {
        Main_Controller.alertWindow(5);
    }
    */
    }
       
    private void fillCboPlantType() throws IOException {
        ObservableList<String> cboPlantTypeLs = FXCollections.observableArrayList();

        cboPlantTypeLs.addAll("Tomato");

        cboPlantType.setItems(cboPlantTypeLs);
    }
    
    private void fillCboPlantSubType() throws IOException {
        ObservableList<String> cboPlantSubTypeLs = FXCollections.observableArrayList();

        cboPlantSubTypeLs.addAll("Roma", "Cherry", "Globe");

        cboPlantSubType.setItems(cboPlantSubTypeLs);
    }
        
    
    
    @FXML
    void btnPlantSearch(ActionEvent event) throws IOException {
        
        String srchTxt = txtPlantSearch.getText();
        ObservableList<Grow_Data> foundGD = FXCollections.observableArrayList();
        ObservableList<Grow_Data> gdList = GrowDataQuery.getAllGrowData();
        
        for (Grow_Data srchPlant : gdList) 
        {
          if(String.valueOf(srchPlant.getPlantId()).contains(srchTxt) || srchPlant.getPlantType().contains(srchTxt) || srchPlant.getPlantSubType().contains(srchTxt))
                    
          {
              foundGD.add(srchPlant);
          }
        }
        tblGrowData.setItems(foundGD);
        txtPlantSearch.setText("");
        if(foundGD.isEmpty())
        {
        Main_Controller.alertWindow(5);
    }

    }


    @FXML
    void btnBack(ActionEvent event) throws IOException {
        
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
    void btnDelete(ActionEvent event) throws IOException {
        
        Grow_Data selPlant = tblGrowData.getSelectionModel().getSelectedItem();
        if (selPlant == null) 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a grow day to delete.");
            alert.showAndWait();
        } else if (tblGrowData.getSelectionModel().getSelectedItem() != null) 
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete Grow Day: Grow Day ID:  " + selPlant.getGrowId() + " ? "
                    + "\nPress ok to Delete?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                try {
                    boolean delSuccessful = GrowDataQuery.deleteGrowData(tblGrowData.getSelectionModel().getSelectedItem().getGrowId());

                    if (delSuccessful) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Successful Delete");
                        alert.setContentText("Deleted Grow ID: " + selPlant.getGrowId() + " Type: " + selPlant.getPlantType());
                        alert.showAndWait();

                        growData = GrowDataQuery.getAllGrowData();
                        tblGrowData.setItems(growData);
                        tblGrowData.refresh();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setContentText("Could not delete appointment.");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @FXML
    void btnExit(ActionEvent event) throws IOException {
        
        Main_Controller.alertWindow(4);

    }

    @FXML
    void btnNewGrowDay(ActionEvent event) throws IOException {
        Create_GrowDay_Controller.rxSelPlant(tblGrowData.getSelectionModel().getSelectedItem());

        if (tblGrowData.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Create_GrowDay_View.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Create New Grow Day");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Load Screen Error.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("You have not selected a Plant Id.  You will manually select a Plant Id at the next screen." +
                    "\nOr you can hit Cancel and select a pre-existing grow day with plant Id.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Create_GrowDay_View.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Create New Grow Day");
                stage.show();;
                }
            if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/GrowData_View.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Grow Data");
                stage.show();;
                }
            
        }

    }

    @FXML
    void btnPlantData(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to go to the Plant Data form?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) 
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Plants_View.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

    }

    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
        
        Update_GrowDay_Controller.rxSelPlant(tblGrowData.getSelectionModel().getSelectedItem());

        if (tblGrowData.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Update_GrowDay_View.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Update Grow Day");
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
            alert.setContentText("You must select a plant to create a new grow day.");
            alert.showAndWait();
        }
        
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         try {
             fillCboPlantId();
             fillCboPlantType();
             fillCboPlantSubType();
         } catch (IOException ex) {
             Logger.getLogger(GrowData_Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        growData = GrowDataQuery.getAllGrowData();
        tblGrowData.setItems(growData);
        
        colGrowId.setCellValueFactory(new PropertyValueFactory<>("growId"));
        colPlantId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        colGrowDay.setCellValueFactory(new PropertyValueFactory<>("growDay"));
        colDailyHarvest.setCellValueFactory(new PropertyValueFactory<>("dailyHarvest"));
        colWater.setCellValueFactory(new PropertyValueFactory<>("water"));
        colLight.setCellValueFactory(new PropertyValueFactory<>("light"));
        colNutrient1.setCellValueFactory(new PropertyValueFactory<>("nutrient1"));
        colNutrient2.setCellValueFactory(new PropertyValueFactory<>("nutrient2"));
        colType.setCellValueFactory(new PropertyValueFactory<>("plantType"));
        colSubType.setCellValueFactory(new PropertyValueFactory<>("plantSubType"));
        
        
    }
    
}
