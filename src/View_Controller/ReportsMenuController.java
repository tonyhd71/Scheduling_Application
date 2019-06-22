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

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class ReportsMenuController implements Initializable {
    
    @FXML
    private Button apptByMonthButton;

    @FXML
    private Button schedForEachButton;

    @FXML
    private Button thirdReportButton;

    @FXML
    private Button returnBackButton;
    @FXML
    void clickApptByMonth(ActionEvent event) throws IOException {       
        Parent root = FXMLLoader.load(getClass().getResource("apptByMonth.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void customersByLocClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customersByLocation.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void returnToMenuOptions(ActionEvent event) throws IOException {        
        Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
     @FXML
    void schedForEachClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scheduleOfCons.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void apptsThisWkBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("apptThisWeek.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
