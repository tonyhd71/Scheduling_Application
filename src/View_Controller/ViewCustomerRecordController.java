/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class ViewCustomerRecordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Customer> customerTableView;
     @FXML
    private TableColumn<Customer, Integer> custRecordIdColm;

    @FXML
    private TableColumn<Customer, String> custRecordIdName;
    @FXML
    private TableColumn<Customer, String> customerAddressTableColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneTableColumn;

    @FXML
    private TableColumn<Customer, String> customerCityTableColumn;

    @FXML
    private TableColumn<Customer, String> customerZipTableColumn;

    @FXML
    private Button returnBackBtn;

    @FXML
    void returnBackClicked(ActionEvent event) {

    }
    int selectedIndex = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        custRecordIdColm.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
            custRecordIdName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            customerAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
            customerPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerPhone"));
            customerCityTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerCity"));
            customerZipTableColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerZip"));
        try {
            // TODO
            DBConnection.makeConnection();
            Statement stmt = conn.createStatement();

            String sqlStatement ="SELECT customer.customerId, customer.customerName, address.address, address.phone, city.city, address.postalCode"
                + " FROM customer INNER JOIN address ON customer.addressId = address.addressId WHERE CustomerID = " + String.valueOf(selectedIndex) + " INNER JOIN city ON address.cityId = city.cityId";
            ResultSet result = stmt.executeQuery(sqlStatement);             
            DBConnection.closeConnection();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewCustomerRecordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewCustomerRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
