/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class addAppointmentController implements Initializable {
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> locationList = FXCollections.observableArrayList();
    ObservableList<String> titleList = FXCollections.observableArrayList();
    ObservableList<String> contactList = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<String> hours2 = FXCollections.observableArrayList();
    ObservableList<String> minutes2 = FXCollections.observableArrayList();
    @FXML
    private Button returnToMainButton;
    private ObservableList<String> apptTypeList = FXCollections.observableArrayList("Monthly","Weekly");    
    @FXML
    private TextField customerAppointmentLocation;

    @FXML
    private TextField customerAppointmentContact;

    private static ObservableList<Appointment> allStartDates = FXCollections.observableArrayList();
    @FXML
    private ComboBox<?> customerAppointmentTime;
Timestamp phoenixTimeStamp = new Timestamp(System.currentTimeMillis()-6300000);
Timestamp londonTimeStamp = new Timestamp(System.currentTimeMillis());
Timestamp newYorkTimeStamp = new Timestamp(System.currentTimeMillis()-480000);
    @FXML
    private TextField appointmentDescriptionInput;   
    @FXML
    private ComboBox hourStartInput;
    @FXML
    private Label apptAddedConfimText;
    @FXML
    private ComboBox minuteStartInput;
    @FXML
    private ComboBox hourEndInput;
    @FXML
    private ComboBox titleChoiceBox;
    @FXML
    private ComboBox locationChoiceBox;
    @FXML
    private ComboBox minuteEndInput;
    @FXML
    private ComboBox contactChoiceBox;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> customerIDTableColumn;
    private static ObservableList<Customer> everyCustomer = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Customer, String> customerNameTableColumn;
     @FXML
    private TableColumn<Customer, String> customerAddressTableColumn;
    @FXML
    private TableColumn<Customer, String> customerPhoneTableColumn;   
    @FXML
    private TableColumn<Customer, String> customerCityTableColumn;
    Timestamp newTimestamp;
    @FXML
    private TableColumn<Customer, String> customerZipTableColumn;
     @FXML
    private TextField appointmentUrlInput;
     @FXML
    private DatePicker customerAppointmentDate;
    String inputError = "Do not leave any fields blank";
    @FXML
    void clickAddAppointment(ActionEvent event) throws SQLException {
     String hour = (String) hourStartInput.getValue();
        String minute = (String) minuteStartInput.getValue();
        String hour2 = (String) hourEndInput.getValue();
        String minute2 = (String) minuteEndInput.getValue();
        String locationn = (String) locationChoiceBox.getValue();
        String contact = (String) contactChoiceBox.getValue();
        String title = (String) titleChoiceBox.getValue(); 
        TimeZone timeZone = TimeZone.getDefault();
        String zoneId = timeZone.getID();
        ZoneId londonTz = ZoneId.of( "Europe/London") ;
        ZoneId nycTz = ZoneId.of( "America/New_York") ;
        ZoneId phxTz = ZoneId.of( "America/Phoenix") ;
        LocalDateTime timeNowLondon = LocalDateTime.now(londonTz);
        LocalDateTime timeNowNyC = LocalDateTime.now(nycTz);
        LocalDateTime timeNowPhoenix = LocalDateTime.now(phxTz);
        LocalDateTime now = LocalDateTime.now();
        
    
        LocalDate selectedDate = customerAppointmentDate.getValue();
        String selectedDateString = String.valueOf(selectedDate);
        String type = appointmentDescriptionInput.getText();
            
                 newTimestamp = londonTimeStamp;                                                                
            try 
            {     
         try {
             DBConnection.makeConnection();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
         }
                        Statement stmt = conn.createStatement();                

                    Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            if (
//                hour.isEmpty() || hour == null ||
                hourStartInput.getSelectionModel().isEmpty() ||
                minuteStartInput.getSelectionModel().isEmpty() ||
//                minute.isEmpty() ||
                hourEndInput.getSelectionModel().isEmpty() ||
//                hour2.isEmpty() ||
                minuteEndInput.getSelectionModel().isEmpty() ||
//                minute2.isEmpty() ||
                locationChoiceBox.getSelectionModel().isEmpty() ||
//                locationn.isEmpty() ||
//                contact.isEmpty() ||
                    contactChoiceBox.getSelectionModel().isEmpty() ||
                type.isEmpty() ||
                selectedDateString.isEmpty() ||
                    titleChoiceBox.getSelectionModel().isEmpty()
//                title.isEmpty()      
                )                        
            {                    
                Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
                 inputErrorAlert.setTitle("Field(s) left empty");
                 inputErrorAlert.setHeaderText("Error");
                 inputErrorAlert.setContentText(inputError);
                 inputErrorAlert.showAndWait();   
                 return;
                //overlap check ends        
//        } 
        } else {
//            LocalDateTime ldt = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonthValue(),            
//            selectedDate.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
           //
            LocalDateTime lDt = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonthValue(),            
            selectedDate.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));                
            ZoneId zid = ZoneId.systemDefault();
            ZonedDateTime zdt = lDt.atZone(zid);
            LocalDateTime ldt = zdt.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

//            LocalDateTime lt2 = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonthValue(),
//            selectedDate.getDayOfMonth(), Integer.parseInt(hour2), Integer.parseInt(minute2)); 
            LocalDateTime lDt2 = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonthValue(),
            selectedDate.getDayOfMonth(), Integer.parseInt(hour2), Integer.parseInt(minute2)); 
            ZoneId zid2 = ZoneId.systemDefault();
            ZonedDateTime zdt2 = lDt2.atZone(zid2);              
            LocalDateTime ldt2 = zdt2.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
              
            String startTimesQuery = "SELECT start, end FROM appointment WHERE MONTH(start)= " + ldt.getMonthValue() + " AND MONTH(end) = " + ldt2.getMonthValue() + " AND DAY(start)= " + ldt.getDayOfMonth() + " AND DAY(end)= " + ldt2.getDayOfMonth();
//            String startTimesQuery = "SELECT start, end FROM appointment WHERE Month(start)= " + ldt + " AND end = " + ldt2; 
               
            
            ResultSet result = stmt.executeQuery(startTimesQuery);
            while(result.next()) {
                LocalDateTime startInput = result.getTimestamp("start").toLocalDateTime();
                LocalDateTime endInput = result.getTimestamp("end").toLocalDateTime();
                    if ((startInput.isBefore(ldt2)) == true && (endInput.isAfter(ldt)) == true) {
                        Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
                        inputErrorAlert.setTitle("This appointment is already taken");
                        inputErrorAlert.setHeaderText("Error");
                        inputErrorAlert.setContentText("That appointment is already booked. Please choose another appointment.");
                        inputErrorAlert.showAndWait();
                        return;
                    }                   
            }
                        int selectedIndex = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
            String SQLStatement1 = "INSERT INTO appointment SET customerId='" + String.valueOf(selectedIndex) + "', title='" + title + "', description='" +
                            appointmentDescriptionInput.getText() + "',  location='" + locationn + "', contact='" + contact + "', url='', "
//                          + "start=CURRENT_TIMESTAMP, end=CURRENT_TIMESTAMP, createDate=CURRENT_TIMESTAMP, createdBy='me', lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='you'"; 
                             + "start='" + Timestamp.valueOf(ldt) + "', end='" + Timestamp.valueOf(ldt2) + "', createDate=CURRENT_TIMESTAMP, createdBy='acontwgu', lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='acontwgu'"; 
            stmt.executeUpdate(SQLStatement1, Statement.RETURN_GENERATED_KEYS); 
                            apptAddedConfimText.setText("Appointment has been added. Press retun to main menu");
                            apptAddedConfimText.setVisible(true);
             try {
                 DBConnection.closeConnection();
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                 Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
                        inputErrorAlert.setTitle("One or more Empty fields detected");
                        inputErrorAlert.setHeaderText("Error");
                        inputErrorAlert.setContentText("Please fill in the empty fields. ");
                        inputErrorAlert.showAndWait();
             }
            System.out.println("Should work");
        }                  
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());                       
        }               
    }
     @FXML
    void returnToMainButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hours.addAll("09", "10", "11",
                "12", "13", "14", "15", "16", "17");
//        return to above
        apptAddedConfimText.setVisible(false);
//        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
//                "12", "13", "14", "15", "16", "17","18", "19", "20", "21", "22", "23");
        
        minutes.addAll("00", "15", "30", "45");
        hourStartInput.setItems(hours);
        minuteStartInput.setItems(minutes);
        hours2.addAll("09", "10", "11",
                "12", "13", "14", "15", "16", "17");        
//        hours2.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
//                "12", "13", "14", "15", "16", "17","18", "19", "20", "21", "22", "23");
        minutes2.addAll("00", "15", "30", "45");
        hourEndInput.setItems(hours2);
        minuteEndInput.setItems(minutes2);
        titleList.addAll("Consulting", "Meeting", "Documenting");
        contactList.addAll("Stacey Miller", "Chad Evans", "Brad Woods");
        locationList.addAll("New York", "London", "Phoenix");
        titleChoiceBox.setItems(titleList);
        contactChoiceBox.setItems(contactList);
        locationChoiceBox.setItems(locationList);       
        try {
            customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
            customerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));            
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement();
            //String sqlStatement ="SELECT * FROM customer_tbl";
            String sqlStatement = "SELECT customer.customerId, customer.customerName, address.address, address.phone, address.postalCode, city.city"
                + " FROM customer INNER JOIN address ON customer.addressId = address.addressId "
                + "INNER JOIN city ON address.cityId = city.cityId";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                //Customer customer = null;
                Customer customer = new Customer(
                        result.getInt("CustomerID"),
                        result.getString("CustomerName"),
                        result.getString("Address"),
                        result.getString("Phone"),
                        result.getString("City"),
                        result.getString("postalCode")
                );
                everyCustomer.add(customer);
                customerTableView.setItems(everyCustomer);
            }   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
      
    
    /**
     * Initializes the controller class.
     */
       
    

