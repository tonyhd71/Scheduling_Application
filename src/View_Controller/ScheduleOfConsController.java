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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class ScheduleOfConsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TableView<Appointment> appointmentMainTable;
        private ObservableList<Appointment> appointments = FXCollections.observableArrayList();            

    @FXML
    private TableColumn<Appointment, Integer> apptIdColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomer;

    @FXML
    private TableColumn<Appointment, String> appointmentTitle;

    @FXML
    private TableColumn<Appointment, String> appointmentType;

    @FXML
    private TableColumn<Appointment, String> appointmentLocation;
    @FXML
    private Label loggedUsrLbl;
    @FXML
    private TableColumn<Appointment, String> appointmentContact;
        private User currentUser;
        
    @FXML
    void retToMainClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reportsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apptIdColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentIdProperty().asObject());
             appointmentCustomer.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
             appointmentTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
             appointmentType.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
             appointmentLocation.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
             appointmentContact.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
             
         try {
             
             DBConnection.makeConnection();
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT appointmentId, customerId, title, description, location, createdBy, contact FROM appointment WHERE createdBy='acontwgu'";
                 ResultSet result = stmt.executeQuery(sqlStatement);             
        while(result.next()) {
            Appointment appointment = new Appointment(
                    result.getInt("appointmentId"),
                    result.getInt("customerId"),
                    result.getString("title"),
                    result.getString("description"),
                    result.getString("location"),
                    result.getString("contact")        
            );
            appointments.add(appointment);
            appointmentMainTable.setItems(appointments);
            loggedUsrLbl.setText("Created By " + result.getString("createdBy"));
         }
         }catch (ClassNotFoundException ex) {
             Logger.getLogger(ScheduleOfConsController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ScheduleOfConsController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}

    
    

