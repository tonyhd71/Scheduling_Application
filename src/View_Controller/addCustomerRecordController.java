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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class addCustomerRecordController implements Initializable {

    /**
     * Initializes the controller class.
     */
//            private static User currentUser;
    private User currentUser;
    
//    User user = new User();
        ZoneId londonTz = ZoneId.of( "Europe/London") ;
        ZoneId nycTz = ZoneId.of( "America/New_York") ;
        ZoneId phxTz = ZoneId.of( "America/Phoenix") ;
        ZoneId ChiTz = ZoneId.of( "America/Chicago") ;
        
        LocalDateTime nowInLondon = LocalDateTime.now(londonTz);
        LocalDateTime nowInNyC = LocalDateTime.now(nycTz);
        LocalDateTime nowInPhoenix = LocalDateTime.now(phxTz);
        LocalDateTime nowInChicago = LocalDateTime.now(ChiTz);
    Timestamp now;
        private static ObservableList<Customer> everyCustomer = FXCollections.observableArrayList();
                    //int selectedIndex = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
         @FXML
    private ComboBox countryInput;
    @FXML
    private ComboBox cityComboBox;
    private ObservableList<String> cities = FXCollections.observableArrayList("New York","Phoenix","London");
     private ObservableList<String> countries = FXCollections.observableArrayList("US","UK");
   
    String inputError ="No empty fields allowed";
      @FXML
    private TextField customerNameInput;

    @FXML
    private TextField custAddressInput;
        @FXML
    private TextField custAddressInput2;
        @FXML
    private TextField customerCityInput;

    @FXML
    private TextField customerZipInput;
ResultSet rs = null;
    @FXML
    private TextField customerPhoneInput;
    @FXML
    private Button addCustomerButton;
       @FXML
    private Label addedCustomerConfirm;
                    int addressId = everyCustomer.size() + 1;

    @FXML
    public boolean addCustomerHandler(ActionEvent event) throws ClassNotFoundException, SQLException {
        String customerName = customerNameInput.getText();
        String customerAddress = custAddressInput.getText();
        String customerAddress2 = custAddressInput2.getText();        
        String customerZip = customerZipInput.getText();
        String customerPhone = customerPhoneInput.getText();       
        try {
            DBConnection.makeConnection();          
            Statement stmt = conn.createStatement();
            int customerCity = cityComboBox.getSelectionModel().getSelectedIndex() +1;                       
            String sqlStatement = "INSERT INTO address SET address='" + customerAddress + "', address2='" + customerAddress2 + "', cityId='" + customerCity + "', postalCode='" + customerZip + "', phone='" + customerPhone + "', createDate=CURRENT_TIMESTAMP, createdBy='acontwgu', lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='acontwgu'";                                                           
        if (customerName.isEmpty() || customerAddress.isEmpty() || customerZip.length() > 5 ||customerZip.length() < 5 || customerCity < 0 || customerZip.isEmpty() || customerPhone.length() < 7 || customerPhone.length() > 11 || customerPhone.isEmpty()) {                
                Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
                        inputErrorAlert.setTitle("Field(s) left empty");
                        inputErrorAlert.setHeaderText("Error");
                        inputErrorAlert.setContentText(inputError);
                        inputErrorAlert.showAndWait(); 
        } else {
                addedCustomerConfirm.setVisible(true);
                int updateOne = stmt.executeUpdate(sqlStatement, Statement.RETURN_GENERATED_KEYS);            
            if(updateOne == 1) {
                //int addressId = everyCustomer.size() + 1;                
                int addressId = -1;
                ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                 addressId = rs.getInt(1);
            } else {
                System.out.println("error");
            }            
// String queryTwo = "INSERT INTO customer SET customerName='" + customerName + "',addressId='" + addressId +"', active=1, createDate=CURRENT_TIMESTAMP, createdBy='me', lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='you'";
// String queryTwo = "INSERT INTO customer SET customerName='" + customerName + "', addressId=" + addressId + ", active=1, createDate=CURRENT_TIMESTAMP, createdBy=" + currentUser.getUsername() + ", lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='you'";                
                String queryTwo = "INSERT INTO customer SET customerName='" + customerName + "', addressId=" + addressId + ", active=1, createDate=CURRENT_TIMESTAMP, createdBy='acontwgu', lastUpdate=CURRENT_TIMESTAMP, lastUpdateBy='acontwgu'";               
                int updateTwo = stmt.executeUpdate(queryTwo);               
                if(updateTwo == 1) {
                    return true;
                }
            }
        }         
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
            return false;                
    }
    @FXML
    void addCustomerBackButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customerMain.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cityComboBox.setItems(cities);
        addedCustomerConfirm.setText("New Customer Entry Confirmed. Press the back button to return to Customer main menu");
        addedCustomerConfirm.setVisible(false);  
    }    
    
}
