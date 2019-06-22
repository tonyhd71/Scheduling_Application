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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class CustomersByLocationController implements Initializable {
            private ObservableList<Customer> customersInNyC = FXCollections.observableArrayList();    
            private ObservableList<Customer> customersInPhoenix = FXCollections.observableArrayList();    
            private ObservableList<Customer> customersInLondon = FXCollections.observableArrayList();    

    @FXML
    private TableView<Customer> nYcTable;

    @FXML
    private TableColumn<Customer, Integer> idTableColumnNyC;

    @FXML
    private TableColumn<Customer, String> nYcNameColumn;

    @FXML
    private TableView<Customer> pATable;

    @FXML
    private TableColumn<Customer, Integer> idTableColumnPa;

    @FXML
    private TableColumn<Customer, String> PaNameColumn;

    @FXML
    private TableView<Customer> lEtable;

    @FXML
    private TableColumn<Customer, Integer> idTableColumnLe;

    @FXML
    private TableColumn<Customer, String> LeNameColumn;
    /**
     * Initializes the controller class.
     */
    @FXML
    void backBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reportsMenu.fxml"));
        Scene scene = new Scene(root);
       Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
       window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    idTableColumnNyC.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
                    nYcNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));                   
                    idTableColumnPa.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
                    PaNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));                   
                    idTableColumnLe.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
                    LeNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
                try {                    
                    DBConnection.makeConnection();
                    Statement stmt = conn.createStatement();
                    //String sqlStatement ="SELECT * FROM customer_tbl";
                    String sqlStatement ="SELECT customer.customerId, customer.customerName, city.city\n" +
                        " FROM customer INNER JOIN address ON customer.addressId = address.addressId \n" +
                        "INNER JOIN city ON address.cityId = city.cityId WHERE city.cityId=1";
            ResultSet result = stmt.executeQuery(sqlStatement);             
            while (result.next()) {
                //Customer customer = null;
                        Customer customer = new Customer(
                        result.getInt("CustomerID"),
                        result.getString("CustomerName")
                );                       
                customersInNyC.add(customer);
                nYcTable.setItems(customersInNyC);
                //return everyCustomer; 
            }
            Statement stmt2 = conn.createStatement();
            String sqlStatement2 ="SELECT customer.customerId, customer.customerName, city.city\n" +
                        " FROM customer INNER JOIN address ON customer.addressId = address.addressId \n" +
                        "INNER JOIN city ON address.cityId = city.cityId WHERE city.cityId=2";
            ResultSet result2 = stmt2.executeQuery(sqlStatement2); 

            while (result2.next()) {
                        Customer customer = new Customer(
                        result2.getInt("CustomerID"),
                        result2.getString("CustomerName")
                );                       
                customersInPhoenix.add(customer);
                pATable.setItems(customersInPhoenix);
            }
            Statement stmt3 = conn.createStatement();            
            String sqlStatement3 ="SELECT customer.customerId, customer.customerName, city.city\n" +
                        " FROM customer INNER JOIN address ON customer.addressId = address.addressId \n" +
                        "INNER JOIN city ON address.cityId = city.cityId WHERE city.cityId=3";
            ResultSet result3 = stmt3.executeQuery(sqlStatement3); 
            while (result3.next()) {
                //Customer customer = null;
                        Customer customer = new Customer(
                        result3.getInt("CustomerID"),
                        result3.getString("CustomerName")
                );                       
                customersInLondon.add(customer);
                lEtable.setItems(customersInLondon);
            }
//            DBConnection.closeConnection();
                    } 
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(CustomersByLocationController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomersByLocationController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }    
    
}
