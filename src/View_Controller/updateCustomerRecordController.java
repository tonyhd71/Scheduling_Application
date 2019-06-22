/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;
import static View_Controller.Customer.getCustomer;
import static View_Controller.DBConnection.conn;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class updateCustomerRecordController implements Initializable {

    /**
     * Initializes the controller class.
     
     *         
     */
    
    @FXML
    private TextField customerNameModify;

    @FXML
    private TextField custAddressModify;
    @FXML
    private TextField custAddress2Modify;

    @FXML
    private ComboBox customerCityModify;

    @FXML
    private TextField customerZipModify;

    @FXML
    private TextField customerPhoneModify;

    @FXML
    private Button modifyCustomerButton;

    @FXML
    private Label addedCustomerConfirm;



    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            //Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            //int selectedCustomerIndex = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();
            //idAmountModify.setText("Part ID autoset to: " + idOfPart);
            // Customer customer = getCustomer().get(partIndex);
            //.get(partIndex);
//
//        customerNameModify.setText(customer.getCustomerName());
//        customerAddressModify.setText(customer.getCustomerAddress());
//        customerPhoneModify.setText(customer.getCustomerPhone());
//        customerCityModify.setText(customer.getCustomerCity());
//        customerZipModify.setText(customer.getCustomerZip());
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT customer.customerId, customer.customerName, address.address, address.address2, city.city, address.postalCode, address.phone"
                    + " FROM customer INNER JOIN address ON customer.addressId = address.addressId "
                    + "INNER JOIN city ON address.cityId = city.cityId";
            
            
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                customerNameModify.setText(sqlStatement);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updateCustomerRecordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(updateCustomerRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
