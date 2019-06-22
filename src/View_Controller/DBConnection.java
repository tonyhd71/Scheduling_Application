/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class DBConnection {
    private static final String databasename ="U04Gxs";
    private static final String DB_URL ="jdbc:mysql://52.206.157.109/" + databasename;
    private static final String username="U04Gxs";
    private static final String password="53688233583";
    private static final String driver ="com.mysql.jdbc.Driver";
    static Connection conn;

    
    
    
    public static void makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(DB_URL, username, password);
        System.out.println("Connection Succesful");
    }    
        public static void closeConnection() throws ClassNotFoundException, SQLException {
            conn.close();
        System.out.println("Connection Closed");
           
        }  
    public static java.sql.Connection getConn(){
    
        return conn;
    }    
}
