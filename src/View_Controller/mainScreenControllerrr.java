/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
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
public class mainScreenControllerrr implements Initializable {
    String inputError ="The username or password you entered is incorrect";
    @FXML
    private TextField userNameInput;
    @FXML    
    private PasswordField passWordInput;
    @FXML 
    private Button loginButton;
//        private User currentUser;
        User user = new User();
    @FXML   
//    public  boolean clickedLogin(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    void   clickedLogin(ActionEvent event) throws IOException {
        String userName = userNameInput.getText();   // Collecting the input
        String passWord = passWordInput.getText(); // Collecting the input
        
        if(userName.length()==0 || passWord.length()==0) {
            System.out.println(inputError);
        }
        else {
            
            User validUser = validateLogin(userName,passWord); 
            if (validUser == null) {
            System.out.println(inputError);
                return;
            }
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            populateReminderList();
//            reminder();
//            mainApp.showMenu(validUser);
//            mainApp.showAppointmentScreen(validUser);
//            //logs successful logins
//            LOGGER.log(Level.INFO, "{0} logged in", 
//                        
        }
    } 

    User validateLogin(String username, String password) {
    
    try {
//        DBConnection.makeConnection();
//        Statement stmt = conn.createStatement();                                                                           
        PreparedStatement pSm = DBConnection.getConn().prepareStatement("SELECT * FROM user WHERE userName=? AND password=?");
        pSm.setString(1, username); 
        pSm.setString(2, password);
        ResultSet results = pSm.executeQuery();
        if(results.next()) {
                                          
//                currentUser = new User();
//                currentUser.setUsername(results.getString("userName"));
            user.setUsername(results.getString("userName"));
                user.setPassword(results.getString("password"));
                user.setUserID(results.getInt("userId"));
//                Stage stage = new Stage();
//                    Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
//                    Scene scene = new Scene(root);
//                     stage.setScene(scene);
//                        stage.show();
                      
        } else {
            return null;
        }                                    
    } catch (SQLException ex) {
            ex.printStackTrace();
    }
        return user;   
    }            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
    
/**@FXML

    void clickedLogin(ActionEvent event) throws IOException {

}
* **/
