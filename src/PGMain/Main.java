/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PGMain;

import Helper.JDBC;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;



/**
 * Main class for the C195 Application
 * @author William Gunther
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login_View.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Initial Screen Error.");
            alert.showAndWait();
        }
}

    /** Main 
     * @param args the command line arguments
     * @throws java.sql.SQLException catches SQLException
     */
    public static void main(String[] args) throws SQLException {
       JDBC.startConn();
       
       launch();
       JDBC.closeConn();
    }
    
        
    
}


    
    

