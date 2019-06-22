/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class CustomerMainController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label confirmCustModLbl;
    String inputError = "Do not leave any fields blank";
    private ObservableList<String> cities = FXCollections.observableArrayList("New York","Phoenix","London");
    @FXML
    private ComboBox modifyCityComboBox;
    @FXML
    private TextField modifyCustomerNameInput;
    @FXML
    private TextField modifyCustomerAddressInput;
    @FXML
    private TextField modifyCustomerAddressInput2;
    @FXML
    private TextField modifyCustomerPhoneInput;
    @FXML
    private TextField modifyCustomerCityInput;
    @FXML
    private TextField modifyCustomerZipInput;
    @FXML
    private Button updateCustomerButton; 
    @FXML
    private VBox modifyInfoVbox;
    @FXML
    private Button clickModifyButton;
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private Button clickAddCustomerButton;
    @FXML
    private TableColumn<Customer, Integer> customerIDTableColumn;
    @FXML
    private TableColumn<Customer, String> customerNameTableColumn;
    @FXML
    private TableColumn<Customer, String> customerAddressTableColumn;
    @FXML
    private TableColumn<Customer, String> customerAddress2TableColumn;
    @FXML
    private TableColumn<Customer, String> customerPhoneTableColumn;
    @FXML
    private TableColumn<Customer, String> customerCityTableColumn;
    @FXML
    private TableColumn<Customer, String> customerZipTableColumn;
    Customer selected;
    private User currentUser;
        ZoneId ChiTz = ZoneId.of( "America/Chicago") ;
        LocalDateTime nowInChicago = LocalDateTime.now(ChiTz);
    private static ObservableList<Customer> everyCustomer = FXCollections.observableArrayList();

    @FXML   
    public void clickAddCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addCustomerRecord.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();   
    }  
    @FXML   
    public void clickModifyCustomer(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
                modifyInfoVbox.setVisible(true);   
                String selectedName = customerTableView.getSelectionModel().getSelectedItem().getCustomerName();
                String selectedAddress = customerTableView.getSelectionModel().getSelectedItem().getCustomerAddress();
                String selectedAddress2 = customerTableView.getSelectionModel().getSelectedItem().getCustomerAddress2();
                String selectedPhone = customerTableView.getSelectionModel().getSelectedItem().getCustomerPhone();               
                String selectedCity = customerTableView.getSelectionModel().getSelectedItem().getCustomerCity();
                String selectedZip = customerTableView.getSelectionModel().getSelectedItem().getCustomerZip();                                               
                modifyCustomerAddressInput.setText(selectedAddress);   
                modifyCustomerAddressInput2.setText(selectedAddress2);                               
                modifyCustomerNameInput.setText(selectedName);
                modifyCustomerPhoneInput.setText(selectedPhone);
                modifyCityComboBox.setValue(String.valueOf(selectedCity));
                
                modifyCustomerZipInput.setText(selectedZip);
    }  
    @FXML
    void clickUpdateCustomer(ActionEvent event) throws ClassNotFoundException {
        try {
            
                DBConnection.makeConnection();
            
//          int selectedIndexAddress = customerTableView.getSelectionModel().getSelectedItem().getAddressId();
            Statement stmt = conn.createStatement();  
            
            int selectedIndex = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
            String selectedName = customerTableView.getSelectionModel().getSelectedItem().getCustomerName();           
            String selectedAddress = customerTableView.getSelectionModel().getSelectedItem().getCustomerAddress();
            String selectedAddress2 = customerTableView.getSelectionModel().getSelectedItem().getCustomerAddress2();      
            String selectedCity = customerTableView.getSelectionModel().getSelectedItem().getCustomerCity();
            String selectedZip = customerTableView.getSelectionModel().getSelectedItem().getCustomerZip();           
            String selectedPhone = customerTableView.getSelectionModel().getSelectedItem().getCustomerPhone();
            String selectedModifyName = modifyCustomerNameInput.getText();
            String selectedAddressModify = modifyCustomerAddressInput.getText();
            String selectedModifyCity = modifyCityComboBox.getValue().toString();
//            int selectedCityInt = (int) modifyCityComboBox.getValue();
//                int selectedCityInt = (int) modifyCityComboBox.getValue();
            
            String selectedModifyZip = modifyCustomerZipInput.getText();
            String selectedModifyPhone = modifyCustomerPhoneInput.getText();
            if (selectedModifyName.isEmpty() ||
                selectedAddressModify.isEmpty() ||   
                selectedModifyCity.isEmpty() ||  
                selectedModifyZip.isEmpty() ||   
                selectedModifyZip.length() > 5||    
                selectedModifyZip.length() < 5||    
                selectedModifyPhone.length() < 7 || 
                selectedModifyPhone.length() > 11 || 
                selectedModifyPhone.isEmpty()) {
                Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
                inputErrorAlert.setTitle("Field(s) left empty");
                inputErrorAlert.setHeaderText("Error");
                inputErrorAlert.setContentText(inputError);
                inputErrorAlert.showAndWait(); 
                return;
            
            } else {
                   String sqlStatement = "UPDATE customer SET customerName='" + modifyCustomerNameInput.getText()
                   +"', active=1, createDate=CURRENT_TIMESTAMP, createdBy='acontwgu', lastUpdate=CURRENT_TIMESTAMP "
                   + " WHERE CustomerID=" + selectedIndex;
                   int customerCity = modifyCityComboBox.getSelectionModel().getSelectedIndex() + 1;                       
                   String sqlStatement2 ="UPDATE address SET "
                   + "address='" + modifyCustomerAddressInput.getText() + "'"
                   + ", address2='" + modifyCustomerAddressInput2.getText() + "'"                  
//                 + ", cityId='" + modifyCityComboBox.getSelectionModel().getSelectedIndex() + 1 + "'"
                   + ", cityId=" + customerCity             
                   + ", postalCode='" + modifyCustomerZipInput.getText() + "'"
                   + ", phone='" + modifyCustomerPhoneInput.getText() + "'"                                             
                   +", createDate=CURRENT_TIMESTAMP, createdBy='acontwgu', lastUpdate=CURRENT_TIMESTAMP "
                   + " WHERE addressid=" + selectedIndex;                     
            stmt.executeUpdate(sqlStatement); 
            stmt.executeUpdate(sqlStatement2);          
            Customer customer = new Customer();
                    customer.setCustomerId(selectedIndex);
                    customer.setCustomerName(selectedName);
                    customer.setCustomerAddress(selectedAddress);
                     customer.setCustomerAddress2(selectedAddress2);                  
                    customer.setCustomerPhone(selectedPhone);
//                    customer.setCustomerCity(selectedCity);
                   customer.setCustomerCity(selectedCity);
                    
                    
                    customer.setCustomerZip(selectedZip);                 
                  confirmCustModLbl.setVisible(true);
                  //
//repopulate begins
//                  customerTableView.refresh();
                  //                    everyCustomer.add(customer);       
                  customerTableView.refresh();
                  customerTableView.getItems().clear();
//                  customerTableView.getItems().addAll(customer);
try {            
            DBConnection.makeConnection();
            Statement stmtModify = conn.createStatement();
            String sqlStatementModify = "SELECT customer.customerId, customer.customerName, address.address, address.address2, city.city, address.postalCode, address.phone"
                + " FROM customer INNER JOIN address ON customer.addressId = address.addressId "
                + "INNER JOIN city ON address.cityId = city.cityId";
            ResultSet result = stmtModify.executeQuery(sqlStatementModify);             
            while (result.next()) {
                        Customer customerModify = new Customer(
                        result.getInt("CustomerID"),
                        result.getString("CustomerName"),
                        result.getString("Address"),
                        result.getString("Address2"),                              
                        result.getString("City"),
                        result.getString("postalCode"),
                        result.getString("Phone")
                );                       
//                everyCustomer.add(customerModify);
//                customerTableView.setItems(everyCustomer);
                customerTableView.getItems().addAll(customerModify);

            }
            DBConnection.closeConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
                
                  
//
                    DBConnection.closeConnection();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
//            Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
//                        inputErrorAlert.setTitle("One or more Empty fields detected");
//                        inputErrorAlert.setHeaderText("Error");
//                        inputErrorAlert.setContentText("Please fill in the empty fields. ");
//                        inputErrorAlert.showAndWait();
        }        
    }
      @FXML
    void customerMainBackButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuOptions.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }      
      @FXML
    void clickDeleteCustomer(ActionEvent event) throws IOException {
        try { 
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement();                    
            int selectedIndex = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
            int deleteThisIndex = customerTableView.getSelectionModel().getSelectedIndex();
            String sqlStatement = "DELETE FROM customer WHERE CustomerID = " + String.valueOf(selectedIndex);
                //also delete appt for customer
            String deleteCustApptsql ="DELETE FROM appointment WHERE customerId= "+ String.valueOf(selectedIndex);            
            Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("Are you sure you want to delete this customer?");
//I used a lambda expression here to use 7 lines of code instead of 10. this is what my code would
//look like using the traditional approach :
//                alert.showAndWait();
//                if (result.isPresent()){                         
//                    result.get() == ButtonType.OK
//                try {
//                    stmt.executeUpdate(sqlStatement);
//                    stmt.executeUpdate(deleteCustApptsql); 
//                } catch (SQLException ex) {
//                  Logger.getLogger(CustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                }                 
                alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
                try {
                    stmt.executeUpdate(sqlStatement); 
                    stmt.executeUpdate(deleteCustApptsql);
                    customerTableView.getItems().remove(deleteThisIndex);                

                } catch (SQLException ex) {
                    Logger.getLogger(CustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
                }});          
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage()); 
        }                 	    
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                            customerTableView.getItems().clear();

        confirmCustModLbl.setText("Customer details have been succesfully modified");
        confirmCustModLbl.setVisible(false);
            modifyCityComboBox.setItems(cities);
            modifyInfoVbox.setVisible(false);
            customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
            customerNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            customerAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
            customerAddress2TableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress2"));         
            customerCityTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerCity"));
            customerZipTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerZip"));
            customerPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
        try {            
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT customer.customerId, customer.customerName, address.address, address.address2, city.city, address.postalCode, address.phone"
                + " FROM customer INNER JOIN address ON customer.addressId = address.addressId "
                + "INNER JOIN city ON address.cityId = city.cityId";
            ResultSet result = stmt.executeQuery(sqlStatement);  
//            customerTableView.getItems().clear();
            while (result.next()) {
                        Customer customer = new Customer(
                        result.getInt("CustomerID"),
                        result.getString("CustomerName"),
                        result.getString("Address"),
                        result.getString("Address2"),                              
                        result.getString("City"),
                        result.getString("postalCode"),
                        result.getString("Phone")
                );                       
//                  everyCustomer.add(customer);
//                  customerTableView.setItems(everyCustomer);
                    customerTableView.getItems().addAll(customer);
      
            }
            DBConnection.closeConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   
    }
     
        




        
          
        
    

