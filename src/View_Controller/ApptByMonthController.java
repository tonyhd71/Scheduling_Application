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
import java.time.LocalDateTime;
import java.time.Month;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class ApptByMonthController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<Appointment, String> appointmentStart;

    @FXML
    private TableColumn<Appointment, String> appointmentEnd;
    @FXML
    private Label numApptsLabel;
      @FXML
    private TableView apptByMonthTbl;
      int x;
    @FXML
    private TableColumn monthColmn;

    @FXML
    private TableColumn titleColmn;

    @FXML
    private TableColumn typeColmn;
        private ObservableList<Appointment> appointments = FXCollections.observableArrayList();            
 @FXML
    private TableColumn<Appointment, Integer> apptIdColumn;

    @FXML
    private TableColumn<Appointment, String> appointmentTitle;

    @FXML
    private TableColumn<Appointment, String> appointmentType;
    @FXML
    private Button returnToRepMenuBtn;
     @FXML
    private TableView<Appointment> displayMonthAppts;
    @FXML
    private ComboBox pickMonthBtn;
    @FXML
    private Label monthLabel;
    private int getMonthNumber(String monthName) {
    return Month.valueOf(monthName.toUpperCase()).getValue();
}

  LocalDateTime c;

        private ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"
                );

    @FXML
    void clickReturnToRepMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reportsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
      void clickedMonth(ActionEvent event) {
          try {             
              DBConnection.makeConnection();
              Statement stmt = conn.createStatement();        
              for (int x = 0; x < 13; x++)                                            {   
                    String selectedMonth = (String) pickMonthBtn.getValue();              
                monthLabel.setText(selectedMonth);
                       if (selectedMonth == "January") {
                  x = 1;
                  
                }  else if (selectedMonth == "February") {
                     x = 2;          
                } else if (selectedMonth == "March") {
                    x = 3;      
                } else if (selectedMonth == "April") {
                    x = 4;      
                } else if (selectedMonth == "May") {
                    x = 5;      
                } else if (selectedMonth == "June") {
                    x = 6;      
                } else if (selectedMonth == "July") {
                    x = 7;      
                } else if (selectedMonth == "August") {
                    x = 8;      
                } else if (selectedMonth == "September") {
                    x = 9;      
                } else if (selectedMonth == "October") {
                    x = 10;      
                } else if (selectedMonth == "November") {
                    x = 11;      
                } else if (selectedMonth == "December") {
                    x = 12;      
                } else {
                    x=1;
                } 
                 String sqlStatement = "SELECT appointmentId, title, description, start, end FROM appointment WHERE MONTH(start) = "+x;
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
                displayMonthAppts.setItems(appointments); 
                        numApptsLabel.setText("There are " + appointments.size() + " appointment(s) this month");
                        numApptsLabel.setVisible(true);  
              }                    
        break;
        }                                                                                  
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(ApptByMonthController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(ApptByMonthController.class.getName()).log(Level.SEVERE, null, ex);
          }                         
      }     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numApptsLabel.setVisible(false);
        pickMonthBtn.setItems(months);
        apptIdColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentIdProperty().asObject());
        appointmentTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        appointmentType.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        appointmentStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        appointmentEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());             
    }    
    
}
