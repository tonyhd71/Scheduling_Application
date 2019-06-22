/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;
import java.text.SimpleDateFormat;

import static View_Controller.DBConnection.conn;
import static com.sun.glass.ui.Cursor.setVisible;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class AppointmentMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static ObservableList<Customer> everyCustomer = FXCollections.observableArrayList();
    @FXML
    private TableView<Customer> cusRecordTable;

    @FXML
    private TableColumn<Customer, Integer> customerIDTableColumn;

    @FXML
    private TableColumn<Customer, String> customerNameTableColumn;

    @FXML
    private TableColumn<Customer, String> customerAddressTableColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneTableColumn;

    @FXML
    private TableColumn<Customer, String> customerCityTableColumn;

    @FXML
    private TableColumn<Customer, String> customerZipTableColumn;
    @FXML
    private Label deletedAppointmentConfirm;
        private ObservableList<Appointment> appointments = FXCollections.observableArrayList();    
ObservableList<String> hours = FXCollections.observableArrayList();
        ObservableList<String> locationList = FXCollections.observableArrayList();
    ObservableList<String> titleList = FXCollections.observableArrayList();
    ObservableList<String> contactList = FXCollections.observableArrayList();

    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<String> hours2 = FXCollections.observableArrayList();
    ObservableList<String> minutes2 = FXCollections.observableArrayList();        
    @FXML
    private TableView<Appointment> appointmentMainTable;
     @FXML
    private TableColumn<Appointment, String> appointmentStart;

    @FXML
    private TableColumn<Appointment, String> appointmentEnd;
    @FXML
    private TableColumn<Appointment, Integer> apptIdColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomer;
    @FXML
    private TableColumn<Appointment, String> appointmentName;

    @FXML
    private TableColumn<Appointment, String> appointmentTitle;

    @FXML
    private TableColumn<Appointment, String> appointmentType;

    @FXML
    private TableColumn<Appointment, String> appointmentLocation;

    @FXML
    private TableColumn<Appointment, String> appointmentContact;
    @FXML
    private TextField modifyDescInput;
    @FXML
    private VBox modifyInputsVbox;

    @FXML
    private ComboBox modifyTitleChoiceBox;


    @FXML
    private ComboBox modifyLocationChoiceBox;
    
    @FXML
    private ComboBox modifyContactChoiceBox;

    @FXML
    private DatePicker modifyAppointmentDate;

    @FXML
    private ComboBox modifyHourStartInput;
    @FXML
    private Label apptModifiedLbl;
    @FXML
    private ComboBox modifyMinuteStartInput;
        String inputError = "Do not leave any fields blank";
    @FXML
    private ComboBox modifyHourEndInput;
    Timestamp newTimestamp;
    Timestamp phoenixTimeStamp = new Timestamp(System.currentTimeMillis()-25200000);
    Timestamp londonTimeStamp = new Timestamp(System.currentTimeMillis());
    Timestamp newYorkTimeStamp = new Timestamp(System.currentTimeMillis()-18000000);
    @FXML
    private ComboBox modifyMinuteEndInput;
//    @FXML
//    void rtrnBtnClick(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
//        Scene scene = new Scene(root);
//       Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//       window.show();
//    }
    @FXML
    void modifyApptSubmitClick(ActionEvent event) {               
        apptModifiedLbl.setVisible(true);
        String type = (String) modifyDescInput.getText();
        String hour = (String) modifyHourStartInput.getValue();
        String minute = (String) modifyMinuteStartInput.getValue();
        String hour2 = (String) modifyHourEndInput.getValue();
        String minute2 = (String) modifyMinuteEndInput.getValue();
        String locationn = (String) modifyLocationChoiceBox.getValue();
        String contact = (String) modifyContactChoiceBox.getValue();
        String title = (String) modifyTitleChoiceBox.getValue();
        LocalDate selectedDate = modifyAppointmentDate.getValue();
        String apptDate = (String) modifyAppointmentDate.getValue().toString();
        int selectedIndex = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentId();
        LocalDateTime lDt = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonthValue(),
        selectedDate.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime zdt = lDt.atZone(zid);
        LocalDateTime ldt = zdt.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
//
        LocalDateTime lDt2 = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonthValue(),
            selectedDate.getDayOfMonth(), Integer.parseInt(hour2), Integer.parseInt(minute2)); 
            ZoneId zid2 = ZoneId.systemDefault();
            ZonedDateTime zdt2 = lDt2.atZone(zid2);              
            LocalDateTime ldt2 = zdt2.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();   
        
//        ZoneId londonTz = ZoneId.of( "Europe/London") ;
//        ZoneId nycTz = ZoneId.of( "America/New_York") ;
//        ZoneId phxTz = ZoneId.of( "America/Phoenix") ;
//        ZonedDateTime zdt = ldt.atZone(nycTz) ;
//        ZonedDateTime londonTimeZoneStart = ZonedDateTime.of(ldt, londonTz);
//        ZonedDateTime londonTimeZoneEnd = ZonedDateTime.of(ldt2, londonTz);
//        ZonedDateTime nycTimeZoneStart = ZonedDateTime.of(ldt, nycTz);
//        ZonedDateTime nycTimeZoneEnd = ZonedDateTime.of(ldt2, nycTz);
//        ZonedDateTime phoenixTzStart = ZonedDateTime.of(ldt, phxTz);
//        ZonedDateTime phoenixTzEnd = ZonedDateTime.of(ldt2, phxTz);
//        Instant instant = Instant.now();
//        LocalDateTime nowInLondon = LocalDateTime.now(londonTz);
//        LocalDateTime nowInNyC = LocalDateTime.now(nycTz);
//        LocalDateTime nowInPhoenix = LocalDateTime.now(phxTz);        
    try {
             
        if (hour.isEmpty() || modifyDescInput.getText().isEmpty() ||  apptDate.isEmpty() ||
                minute.isEmpty() || hour2.isEmpty() || minute2.isEmpty() || 
                locationn.isEmpty() || contact.isEmpty() || title.isEmpty()) 
        {            
            Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
            inputErrorAlert.setTitle("Field(s) left empty");
            inputErrorAlert.setHeaderText("Error");
            inputErrorAlert.setContentText(inputError);
            inputErrorAlert.showAndWait();   
            return;
           
        } else {
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement(); 
            //
            
            //
            String sqlStatement = "UPDATE appointment SET title='" + title +"', description=' " + modifyDescInput.getText() + "', location='" + locationn + "', contact='" + contact + "', start='" + Timestamp.valueOf(ldt) + "', end='" + Timestamp.valueOf(ldt2) + "', createDate=CURRENT_TIMESTAMP, createdBy='acontwgu', lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='acontwgu'" + " WHERE appointmentId=" + selectedIndex;
            stmt.executeUpdate(sqlStatement);
           appointmentMainTable.refresh();
            
//            appointmentMainTable.getItems().clear();
//            appointmentMainTable.getItems().addAll(appointments);

            
            apptModifiedLbl.setText("Appointment has been modified");
//repopulate begins  
            appointmentMainTable.getItems().clear();
try {
            DBConnection.makeConnection(); 
            Statement stmtModify = conn.createStatement();
            String sqlStatementModify  = "SELECT appointment.appointmentId, customer.customerId, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.start, appointment.end FROM appointment INNER JOIN customer ON appointment.customerId = customer.customerId";
            ResultSet result = stmtModify.executeQuery(sqlStatementModify);             
        while(result.next()) {

//                int hourToModify = result.getTime("start").toLocalTime().getHour();
            String modifiedStart = result.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(zid).toString();
            String modifiedEnd = result.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(zid2).toString();
            Appointment appointmentModify = new Appointment(
                    result.getInt("appointmentId"),
                    result.getInt("customerId"),
                    result.getString("title"),
                    result.getString("description"),
                    result.getString("location"),
                    result.getString("contact"), 
                    modifiedStart,
                    modifiedEnd
//                    result.getString("start"), 
////                    String.valueOf(hourToModify),
//                    result.getString("end")                           
            );
//            appointments.add(appointmentModify);
//            appointmentMainTable.setItems(appointments);
             appointmentMainTable.getItems().addAll(appointmentModify);

        }             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }            
//            
            DBConnection.closeConnection();              
        }                 
    } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
            Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
            inputErrorAlert.setTitle("Field(s) left empty");
            inputErrorAlert.setHeaderText("Error");
            inputErrorAlert.setContentText(inputError);
            inputErrorAlert.showAndWait(); 
    } catch (SQLException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @FXML
    void viewCustRec(ActionEvent event) throws IOException {
                    cusRecordVbox.setVisible(true);
                    cusRecordTable.getItems().clear();

        try {

            DBConnection.makeConnection();
            Statement stmt = conn.createStatement();
            int selectedIndex = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentCustomerId();
            String sqlStatement ="SELECT customer.customerId, customer.customerName, address.address, city.city, address.postalCode, address.phone"
                + " FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON address.cityId = city.cityId WHERE customer.customerId='" + selectedIndex + "'";
            ResultSet result = stmt.executeQuery(sqlStatement); 
            while (result.next()) {
                                  

                Customer customer = new Customer(
                        result.getInt("CustomerID"),
                        result.getString("CustomerName"),
                        result.getString("Address"),                       
                        result.getString("City"),
                        result.getString("postalCode"),
                        result.getString("Phone")
                );   
//                everyCustomer.add(customer);
//                cusRecordTable.setItems(everyCustomer);
                             cusRecordTable.getItems().addAll(customer);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML   
    public void addAppt(ActionEvent event) throws IOException {  
        Parent root = FXMLLoader.load(getClass().getResource("addAppointment.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();        
    }   
     @FXML
    private VBox cusRecordVbox;
    @FXML   
    public void modifyAppt(ActionEvent event) throws IOException, ClassNotFoundException, ParseException {
        
                                modifyInputsVbox.setVisible(true);
            String selectedTitle = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentTitle();
            String selectedDescription = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentDescription();
            String selectedLocation = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentLocation();
            String selectedContact = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentContact();
            String selectedStart = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentStart();
            String selectedEnd = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentEnd();
        try {
        DBConnection.makeConnection(); 
            Statement stmt = conn.createStatement();           
            String getlDt = "SELECT appointment.appointmentId, customer.customerId, appointment.start FROM appointment INNER JOIN customer ON appointment.customerId = customer.customerId";
            ResultSet result = stmt.executeQuery(getlDt); 
            while(result.next()) {
//            String getlDtString = result.getTimestamp("start").toLocalDateTime().toString();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"); 
//            LocalDateTime hourStartModifyy = LocalDateTime.parse(getlDtString, formatter);
//            int modifyHour = hourStartModifyy.getHour();
//            String modifyHourStr = String.valueOf(modifyHour);
//             LocalDate startDate = LocalDate.parse(selectedStart);
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX'['VV']'");
                final LocalDate parsedDate = LocalDate.parse(selectedStart, formatter);
                final String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//                System.out.println(formattedDate);
                modifyAppointmentDate.setValue(parsedDate);
                //              
                final DateTimeFormatter formatterGetTimeStart = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX'['VV']'");
                final LocalDateTime lDtGetTimeStart = LocalDateTime.parse(selectedStart, formatterGetTimeStart);
                
                modifyHourStartInput.setValue(lDtGetTimeStart.getHour());
                
                final DateTimeFormatter formatterGetTimeEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX'['VV']'");
                final LocalDateTime lDtGetTimeEnd = LocalDateTime.parse(selectedEnd, formatterGetTimeEnd);
                modifyHourEndInput.setValue(lDtGetTimeEnd.getHour());
                
                final DateTimeFormatter formatterGetMinuteStart = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX'['VV']'");
                final LocalDateTime lDtGetMinuteStart = LocalDateTime.parse(selectedStart, formatterGetMinuteStart);
                modifyMinuteStartInput.setValue(lDtGetMinuteStart.getMinute());
                
                final DateTimeFormatter formatterGetMinuteEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX'['VV']'");
                final LocalDateTime lDtGetMinuteEnd= LocalDateTime.parse(selectedEnd, formatterGetMinuteEnd);
                modifyMinuteEndInput.setValue(lDtGetMinuteEnd.getMinute());
                //
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
            LocalDate now = LocalDate.now();
            LocalDate tomorrow = now.plusDays(1);

            modifyTitleChoiceBox.setValue(String.valueOf(selectedTitle));          
            modifyDescInput.setText(selectedDescription);
            modifyLocationChoiceBox.setValue(String.valueOf(selectedLocation));
            modifyContactChoiceBox.setValue(String.valueOf(selectedContact));
//            modifyHourStartInput.setValue("00");
            appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentStart();            
//            modifyMinuteStartInput.setValue(String.valueOf());
//            modifyAppointmentDate.setValue(tomorrow);
            
            
//try {
//            DBConnection.makeConnection(); 
//            Statement stmtModify = conn.createStatement();
//            String sqlStatementModify  = "SELECT appointment.appointmentId, customer.customerId, appointment.start, appointment.end FROM appointment INNER JOIN customer ON appointment.customerId = customer.customerId";
//            ResultSet result = stmtModify.executeQuery(sqlStatementModify); 
//            while(result.next()) {
//                int hourToModify = result.getTime("start").toLocalTime().getHour();
//                String hourToModStr = String.valueOf(hourToModify);
//                modifyHourStartInput.setValue(hourToModStr);
//                int hour2ToModify = result.getTime("end").toLocalTime().getHour();
//                String hour2ToModStr = String.valueOf(hour2ToModify);
//                modifyHourEndInput.setValue(hour2ToModStr);
//                int minuteToModify = result.getTime("start").toLocalTime().getMinute();
//                String minuteToModStr = String.valueOf(minuteToModify);
//                modifyMinuteStartInput.setValue(minuteToModStr);
//                int minute2ToModify = result.getTime("end").toLocalTime().getMinute();
//                String minute2ToModStr = String.valueOf(minute2ToModify);
//                modifyMinuteEndInput.setValue(minute2ToModStr);                
//                LocalDate dateToModify = result.getTimestamp("start").toLocalDateTime().toLocalDate();
//                modifyAppointmentDate.setValue(dateToModify);
//            }
////                    result.getString("start"), 
////            appointments.add(appointmentModify);
////            appointmentMainTable.setItems(appointments);
//
//                    
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
//        }                    
            //
        
            
    }
    @FXML
    void deleteAppt(ActionEvent event) throws ClassNotFoundException, SQLException {             
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement();
            int selectedIndex = appointmentMainTable.getSelectionModel().getSelectedItem().getAppointmentId();
            int deleteThisIndex = appointmentMainTable.getSelectionModel().getSelectedIndex();
            String sqlStatement = "DELETE FROM appointment WHERE appointmentId = " + String.valueOf(selectedIndex);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setContentText("Are you sure you want to delete this appointment?");
//I used a lambda expression here to use 6 lines of code instead of 9. this is what my code would
//look like using the traditional approach :
//        Optional<ButtonType> result = alert.showAndWait();
// if (result.isPresent() && result.get() == ButtonType.OK) {
//     try {
//                    stmt.executeUpdate(sqlStatement);
//                    deletedAppointmentConfirm.setText("Appointment " + selectedIndex + " has been succesfully deleted");
//                } catch (SQLException ex) {
//                    Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
//                }
// }
            alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
            try {
                stmt.executeUpdate(sqlStatement);   
                appointmentMainTable.getItems().remove(deleteThisIndex);                
                deletedAppointmentConfirm.setText("Appointment " + selectedIndex + " has been succesfully deleted");
            } catch (SQLException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);}});                
            
            DBConnection.closeConnection();             
    }
    @FXML
    void returnToMainClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       apptModifiedLbl.setVisible(false);
        cusRecordVbox.setVisible(false);
        modifyInputsVbox.setVisible(false);
        hours.addAll("09", "10", "11",
        "12", "13", "14", "15", "16", "17");
        minutes.addAll("00", "15", "30", "45");
        modifyHourStartInput.setItems(hours);
        modifyMinuteStartInput.setItems(minutes);
        hours2.addAll("09", "10", "11",
                "12", "13", "14", "15", "16", "17");        
//        hours2.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
//                "12", "13", "14", "15", "16", "17","18", "19", "20", "21", "22", "23");
        minutes2.addAll("00", "15", "30", "45");
        modifyHourEndInput.setItems(hours2);
        modifyMinuteEndInput.setItems(minutes2);
        titleList.addAll("Consulting", "Meeting", "Documenting");
        contactList.addAll("Stacey Miller", "Chad Evans", "Brad Woods");
        locationList.addAll("New York", "London", "Phoenix");
        modifyTitleChoiceBox.setItems(titleList);
        modifyContactChoiceBox.setItems(contactList);
        modifyLocationChoiceBox.setItems(locationList); 
        apptIdColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentIdProperty().asObject());
        appointmentCustomer.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        appointmentTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        appointmentType.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        appointmentLocation.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        appointmentContact.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
        appointmentStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        appointmentEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());               
        //bottom table
        customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
            customerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            customerAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
            customerCityTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerCity"));
            customerZipTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerZip"));
            customerPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
            
            //bottom table ends
        try {
            DBConnection.makeConnection(); 
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT appointment.appointmentId, customer.customerId, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.start, appointment.end FROM appointment INNER JOIN customer ON appointment.customerId = customer.customerId";
            ResultSet result = stmt.executeQuery(sqlStatement);             
        while(result.next()) {
                    ZoneId zid = ZoneId.systemDefault();

//            String start = result.getTimestamp("start").toLocalDateTime().atZone(zid).toString();
            String start = result.getTimestamp("start").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(zid).toString();
            String end = result.getTimestamp("end").toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(zid).toString();
           
                    Appointment appointment = new Appointment(
                    result.getInt("appointmentId"),
                    result.getInt("customerId"),
                    result.getString("title"),
                    result.getString("description"),
                    result.getString("location"),
                    result.getString("contact"), 
//                    result.getString("start"),  
                    start,
                    end                          
            );
            appointments.add(appointment);
            appointmentMainTable.setItems(appointments);
        }             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
