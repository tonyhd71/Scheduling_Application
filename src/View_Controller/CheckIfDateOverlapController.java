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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class CheckIfDateOverlapController implements Initializable {
    
            
   
    /**
     * Initializes the controller class.
     */
      @FXML
    private Button checkIfDateOverlapBtn;

    @FXML
    private Button retunToBtn;

    @FXML
    void checkIfDateOverlapClick(ActionEvent event) {
        try {
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement(); 
//            for () {
//                
//            }
            String countRows = "SELECT COUNT(*) FROM appointment WHERE customerId = ?";        
            String ifOverlapsQuery = "SELECT appointment.appointmentId, appointment.start, appointment.end,temp_table.appointmentId, temp_table.start, temp_table.end FROM appointment AS appointment INNER JOIN appointment AS temp_table ON (appointment.appointmentId <> temp_table.appointmentId AND temp_table.end  > appointment.start AND appointment.end > temp_table.start";

            
            ResultSet result = stmt.executeQuery(ifOverlapsQuery);
            int rows = 0;
            result.last();
            rows = result.getRow();
            result.beforeFirst();

            System.out.println("Your query have " + rows + " rows.");
       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckIfDateOverlapController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckIfDateOverlapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void retunToBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    void testingFunction () {
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
