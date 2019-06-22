/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static View_Controller.DBConnection.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class customerDataBase {
       /** 
    public static ObservableList<Customer> getEveryCustomer() throws ClassNotFoundException {
            // TODO
        try {  
            DBConnection.makeConnection();
            //Create Statement Object
            Statement stmt = conn.createStatement();
            String sqlStatement ="SELECT * FROM customer_tbl";            
            //Execute INSERT statement
            ResultSet result = stmt.executeQuery(sqlStatement);             
            //System.out.println(result.getString("CustomerName") + ", ");
            
            //Get all Records from ResultSet Object
            while (result.next()) {
                /**
                System.out.print(result.getInt("CustomerID") + ", ");
                System.out.print(result.getString("CustomerName") + ", ");
                System.out.print(result.getString("Address") + ", ");
                System.out.print(result.getString("Phone"));  
                System.out.println();     
                */
        
        /**
                Customer customer = new Customer(  
                    result.getInt("CustomerId"), 
                    result.getString("CustomerName"), 
                    result.getString("Address"),
                    result.getString("Phone"),        
                    result.getString("City"),
                    result.getString("Zip"));
                everyCustomer.add(customer);
                DBConnection.closeConnection();
                 //return everyCustomer; 
            }
            return everyCustomer;

        }   catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;

        }
    }
            
                                                             
*/
        
    
    
    
}
