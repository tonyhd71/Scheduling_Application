/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class ApptThisWeekController implements Initializable {
//    java.util.Date d1 = new java.util.Date();
//
//    java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
    /**
     * Initializes the controller class.
     */
//    LocalDate now = LocalDate.now();
//    LocalDate next7Days = now.plusDays(8);
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime next7Days = now.plusDays(50);
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();            

    @FXML
    private TableView<Appointment> displayWeeksAppts;

    @FXML
    private TableColumn<Appointment, Integer> apptIdColumn;

    @FXML
    private TableColumn<Appointment, String> appointmentTitle;

    @FXML
    private TableColumn<Appointment, String> appointmentType;

    @FXML
    private Button backBtn;
    @FXML
    private TableColumn<Appointment, String> appointmentStart;

    @FXML
    private TableColumn<Appointment, String> appointmentEnd;
    @FXML
    void clickBackBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reportsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void viewApptsThisWkClick(ActionEvent event) {
        System.out.println("Test");
        try {
             // TODO
             String sqlStatement = "SELECT appointmentId, title, description, start, end FROM appointment WHERE "
//                     + "start BETWEEN " + now + " AND " +  next7Days;
//                     + "start > " + now + " AND start < " + next7Days;
//                     + "start > " + now;
//                WHERE start between now and next7Days
//                     + "start < " + next7Days;
//             "SELECT appointmentId, title, description FROM appointment WHERE MONTH(start) = "+x;
//                 + "start <= NOW() + INTERVAL 7 DAY AND start > NOW()";
                 + "yearweek(start) = yearweek(curdate())";
                     
             Statement stmt = conn.createStatement();
             
             ResultSet result = stmt.executeQuery(sqlStatement);
             while(result.next()) {
                 Appointment appointment = new Appointment(
                         result.getInt("appointmentId"),
                         result.getString("title"),
                         result.getString("description"),
                         result.getString("start"),
                         result.getString("end")     
                 );
                 appointments.add(appointment);
                 displayWeeksAppts.setItems(appointments);
             }    } catch (SQLException ex) {    
             Logger.getLogger(ApptThisWeekController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         apptIdColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentIdProperty().asObject());
                appointmentTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        appointmentType.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        appointmentStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        appointmentEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());
         
    
}
}
