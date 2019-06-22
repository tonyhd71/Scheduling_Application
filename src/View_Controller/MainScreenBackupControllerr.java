/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class MainScreenBackupControllerr implements Initializable {
    String inputError ="The username or password you entered is incorrect";
    @FXML
    private TextField userNameInput;
    @FXML    
    private PasswordField passWordInput;
    @FXML 
    private Button loginButton;
        private User currentUser;
         @FXML   
    public  boolean clickedLogin(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
       try { 
        DBConnection.makeConnection();
          Statement stmt = conn.createStatement();
//          String sqlStatement = "INSERT INTO user SET userName='" + userNameInput.getText() + "', password='" + passWordInput.getText()  + "', active=1, createBy='" + userNameInput.getText()+ "',  createDate=CURRENT_TIMESTAMP, lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy=CURRENT_TIMESTAMP";
                        String sqlStatement = "SELECT * FROM user WHERE userName='" + userNameInput.getText() + "' AND password='" + passWordInput.getText() + "'";
//            String sqlStatement = "SELECT * FROM user";
//          String sqlStatement = "SELECT * FROM user WHERE userName=? AND password=?";
          ResultSet results = stmt.executeQuery(sqlStatement);
            if(results.next()) {
                
//                currentUser = new User();
//                currentUser.setUsername(results.getString("userName"));
                currentUser = new User();
                currentUser.setUsername(results.getString("userName"));
//                user.setPassword(results.getString("password"));
//                user.setUserID(results.getInt("userID"));
                Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
                Scene scene = new Scene(root);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
                return true;
            } else {
                          //System.out.println("false");                      
                          Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
                inputErrorAlert.setTitle("Error Adding Part");
                inputErrorAlert.setHeaderText("Error");
                inputErrorAlert.setContentText(inputError);
                inputErrorAlert.showAndWait();      
                return false;
            }         
        } catch (SQLException ex) {
                        Logger.getLogger(mainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
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
