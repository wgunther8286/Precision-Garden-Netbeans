/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import DBQuery.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;


interface LoginLog
{
    public String getFile();
}

/**
 * Controller for the login screen
 * @author William Gunther
 */
public class Login_Controller implements Initializable 
{
    
    @FXML
    private Label locLbl;

    @FXML
    private Label pwLbl;
    
    @FXML
    private Label txtLoginTitle;

    @FXML
    private PasswordField txtPw;

    @FXML
    private TextField txtUserLocation;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label uNameLbl;
    
    @FXML
    private Button btnSignIn;
    
    @FXML
    private Button btnExit;

    @FXML
    private Button btnReset;
    
     /** Lambda Expression for login log.
     */
    LoginLog loginLog = () -> 
    {
        return "login_activity.txt";
    };
    
    Stage stage;
    Parent scene;
    int currentUserID;
    

    @FXML
    void btnExit(ActionEvent event) 
    {
        ResourceBundle rb = ResourceBundle.getBundle("Language/language", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
        {
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, this.rb.getString("exitError"));
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) 
            {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    void btnReset(ActionEvent event) 
    {
        
        txtUserName.setText("");
        txtPw.setText("");

    }

    /**
     * Resource Bundle for Language.
     */
    ResourceBundle rb = ResourceBundle.getBundle("Language/language", Locale.getDefault());
    
    private void loginFile()
    {
        try {
            File newfile = new File(loginLog.getFile());
            if (newfile.createNewFile()) {
                System.out.println("Login log created:" + newfile.getName());
            } else {
                System.out.println("Login log already exists at: " + newfile.getPath());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Checks user name field is filled.
     * @param uname 
     */
    private void checkUName(String uname)
    {
        if (uname.isEmpty()) 
        {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("errorDialog"));
                alert.setContentText(rb.getString("usernameRequired"));
                alert.showAndWait();
            }
        }
    }

    /** Checks Password Text Field is filled
     * @param pword Text Field Value for Username
     */
    private void checkPw(String pword)
    {
        if (pword.isEmpty()) 
        {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("errorDialog"));
                alert.setContentText(rb.getString("passwordRequired"));
                alert.showAndWait();
            }
        }
    }

    /**
     * Button to sign-in to the application.
     * @param event when clicked, moves user to the main view if username and password are correct.
     * @throws SQLException catches SQLException, prints stacktrace.
     * @throws IOException catches IOException, prints stacktrace.
     */
    @FXML
    public void btnSignIn(ActionEvent event) throws SQLException, IOException 
    {
        
        checkUName(txtUserName.getText());
        checkPw(txtPw.getText());
        loginFile();
        
        try {
            boolean signin = UserQuery.checkUnamePword(txtUserName.getText(), txtPw.getText());
            if (signin) {

                loggedIn();

                try {
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(getClass().getResource("/View/Main_View.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.setTitle("Home");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();



                    if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(rb.getString("errorDialog"));
                        alert.setContentText(rb.getString("loadScreenError"));
                        alert.showAndWait();
                    }
                }
            } else {

                loginFail();

                if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("errorDialog"));
                    alert.setContentText(rb.getString("incorrectUsernamePassword"));
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    private void apptAlert()
    {
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt15 = ldt.plusMinutes(15);

        ObservableList<Appointment> futureAppts = FXCollections.observableArrayList();


        try {
            ObservableList<Appointment> appts = ApptQuery.getAppts();

            if (appts != null) 
            {
                for (Appointment appt: appts) 
                {
                    if (appt.getSTime().isAfter(ldt) && appt.getSTime().isBefore(ldt15)) 
                    {
                        futureAppts.add(appt);

                        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(rb.getString("appointmentAlert"));
                            alert.setContentText(rb.getString("lessThanFifteenMin") +
                                    "\n" +
                                    rb.getString("appointmentId") +
                                    " " +
                                    + appt.getApptId() +
                                    "\n" +
                                    rb.getString("date") +
                                    " " +
                                    appt.getSTime() +
                                    "\n" +
                                    rb.getString("time") +
                                    " " +
                                    appt.getSTime().toLocalTime());
                            alert.setResizable(true);
                            alert.showAndWait();
                        }
                    }
                }

                if (futureAppts.size() < 1) 
                {
                    if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(rb.getString("appointmentAlert"));
                        alert.setContentText(rb.getString("noUpcomingAppointments"));
                        alert.setResizable(true);
                        alert.showAndWait();
                    }
                }
            } else {
                if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) 
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(rb.getString("appointmentAlert"));
                    alert.setContentText(rb.getString("noUpcomingAppointments"));
                    alert.setResizable(true);
                    alert.showAndWait();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** Writes user login activity to the login_activity.txt file
     *  
     */
    private void loggedIn()
    {

        //apptAlert();

        try {
            FileWriter fw = new FileWriter(loginLog.getFile(), true);
            SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date d = new Date(System.currentTimeMillis());
            fw.write("Successful Login: Username=" + txtUserName.getText() + " Password=" + txtPw.getText() + " Timestamp: " + sdt.format(d) + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
   /** Writes user login failure to the login_activity.txt file
     *  Catches IOException and prints stacktrace.
     *  
     */
    private void loginFail() 
    {
        try {
            FileWriter fw = new FileWriter(loginLog.getFile(), true);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fw.write("Failed Login: Username=" + txtUserName.getText() + " Password=" + txtPw.getText() + " Timestamp: " + sdf.format(date) + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     /**
     * Initialize Method -- prepares page for use and viewing
     * @param url url
     * @param resourceBundle Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) 
    {

        ResourceBundle rb = ResourceBundle.getBundle("Language/language", Locale.getDefault());
        System.out.println("Locale = " + Locale.getDefault() + "\n");


        locLbl.setText(rb.getString("location"));
        txtUserLocation.setText(rb.getString("country"));
        uNameLbl.setText(rb.getString("username"));
        txtUserName.setPromptText(rb.getString("username"));
        pwLbl.setText(rb.getString("password"));
        txtPw.setPromptText(rb.getString("password"));
        btnSignIn.setText(rb.getString("login"));
        btnExit.setText(rb.getString("Exit"));
        btnReset.setText(rb.getString("Reset"));
        txtLoginTitle.setText(rb.getString("PrecisionGardens"));


    }
    
}
