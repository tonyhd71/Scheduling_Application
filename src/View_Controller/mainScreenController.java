/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import static java.time.Clock.system;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class mainScreenController implements Initializable {
    private static final int Fifteen_MINUTES = 15 * 60 * 1000;
    TimeZone timeZone = TimeZone.getDefault();
    String zoneId = timeZone.getID();
    
    LocalDateTime now = LocalDateTime.now();
    ZoneId zid = ZoneId.systemDefault();
    ZonedDateTime zdt = now.atZone(zid);
    LocalDateTime ldt = zdt.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

    Instant instant = Instant.now();
//            Timestamp in15Mins = new Timestamp(System.currentTimeMillis()+900000);
//            Timestamp curentTimestamp = new Timestamp(System.currentTimeMillis());
    @FXML
    private Label languageErrorMessage;
    String inputError ="The username or password you entered is incorrect";
        String inputErrorSpanish ="El nombre de usuario o la contraseña son incorrectos";
    ResourceBundle rb;
    @FXML
    private TextField userNameInput;
    long minutes = 15;
    @FXML    
    private PasswordField passWordInput;
    @FXML
    private Label userNameLbl;
    @FXML
    private Label passWordLbl;
    @FXML 
    private Button loginButton;
        private User currentUser;    
    @FXML   
    public  boolean clickedLogin(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        System.out.println("current time is " + ldt + " in 15 minutes it will be " + ldt.plusMinutes(minutes));
        try { 
        DBConnection.makeConnection();
          Statement stmt = conn.createStatement();
//          String sqlStatement = "INSERT INTO user SET userName='" + userNameInput.getText() + "', password='" + passWordInput.getText()  + "', active=1, createBy='" + userNameInput.getText()+ "',  createDate=CURRENT_TIMESTAMP, lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy=CURRENT_TIMESTAMP";
                     String sqlStatement = "SELECT * FROM user WHERE userName='" + userNameInput.getText() + "' AND password='" + passWordInput.getText() + "'";
//          String sqlStatement = "SELECT * FROM user WHERE userName=? AND password=?";
//                        String sqlStatement2 = "SELECT start, createdBy FROM appointment WHERE createdBy='" + userNameInput.getText() + "'";
                     
                    
//                     String sqlStatement2 = "SELECT start, createdBy FROM appointment WHERE start BETWEEN '" + curentTimestamp + "' AND '" + in15Mins + "' AND " + "createdBy='" + userNameInput.getText() + "'";
                     String sqlStatement2 = "SELECT start, createdBy FROM appointment WHERE start BETWEEN '" + ldt + "' AND '" + ldt.plusMinutes(minutes) + "' AND " + "createdBy='" + userNameInput.getText() + "'";
                      
                     ResultSet results2 = stmt.executeQuery(sqlStatement2);
                        if(results2.next()) {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("You have an appointment in less than 15 minutes");
                            alert.showAndWait();
                        } 
                       //appt in 15 ends                        
          ResultSet results = stmt.executeQuery(sqlStatement);
            if(results.next()) {
                currentUser = new User();
                currentUser.setUsername(results.getString("userName"));
                FileWriter fw;
                fw = new FileWriter("userLogins.txt", true);        
                fw.write(String.format("User Name: " + currentUser.getUsername() + " "));
                Instant instant = Instant.now() ;
                String output = instant.toString() ;
                fw.write("Log in Date: " + output);
                fw.write(System.lineSeparator()); //new line               
                fw.close();
                Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                return true;
            } else {
                languageErrorMessage.setVisible(true);
                return false;
            }
        } catch (SQLException ex) {
                        Logger.getLogger(mainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
        }       
    }        
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Locale locale = Locale.getDefault();
//        Locale.setDefault(new Locale("es", "ES"));
        rb = ResourceBundle.getBundle("language_files/rb", locale);
        languageErrorMessage.setText(rb.getString("errortext"));   
        languageErrorMessage.setVisible(false);           
        loginButton.setText(rb.getString("login"));
        userNameLbl.setText(rb.getString("username"));
        passWordLbl.setText(rb.getString("password"));
        
    }    
    
}
