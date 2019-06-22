/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//import static View_Controller.CheckIfDateOverlapController.testingFunction;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class MenuOptionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button manageCustomersBtn;
    @FXML
    private Button manageApptBtn;
    @FXML
    private Button reportBtn;
    
    @FXML
    void clickViewReports(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reportsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
     @FXML   
    public void clickManageCustomers(ActionEvent event) throws IOException {      
        Parent root = FXMLLoader.load(getClass().getResource("customerMain.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();        
    }           
    @FXML   
    public void clickManageAppt(ActionEvent event) throws IOException {                
        Parent root = FXMLLoader.load(getClass().getResource("appointmentMain.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();   
    }  
    @FXML
    void ifDateOverlaplClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("checkIfDateOverlap.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
