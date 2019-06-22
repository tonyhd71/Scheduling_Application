/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public final class Customer {
    private final SimpleIntegerProperty idOfCustomer = new SimpleIntegerProperty();
    private final SimpleStringProperty nameOfCustomer = new SimpleStringProperty();
    private final SimpleStringProperty addressOfCustomer = new SimpleStringProperty();
    private final SimpleStringProperty address2OfCustomer = new SimpleStringProperty();  
    private final SimpleStringProperty cityOfCustomer = new SimpleStringProperty();
    private final SimpleStringProperty zipOfCustomer = new SimpleStringProperty();
    private final SimpleStringProperty phoneOfCustomer = new SimpleStringProperty();
    private static ObservableList<Customer> everyCustomer = FXCollections.observableArrayList();
    //new customer w 2 inputs below
    public Customer(int id, String name) {
            setCustomerId(id);
            setCustomerName(name);           
    }
    //new ends

        public Customer() {}
        public Customer(int id, String name, String address, String city, String zip, String phone) {
            setCustomerId(id);
            setCustomerName(name);
            setCustomerAddress(address);
            setCustomerCity(city);
            setCustomerZip(zip);
            setCustomerPhone(phone);
            
    }
    public Customer(int id, String name, String address, String address2, String city, String zip, String phone) {
            setCustomerId(id);
            setCustomerName(name);
            setCustomerAddress(address);
            setCustomerAddress2(address2);       
            setCustomerCity(city);
            setCustomerZip(zip);           
            setCustomerPhone(phone);
            
    }    
            public int getCustomerId() {
        return idOfCustomer.get();
    }
    
    public String getCustomerName() {
        return nameOfCustomer.get();
    }
    
    public String getCustomerAddress() {
        return addressOfCustomer.get();
    }
    public String getCustomerAddress2() {
        return address2OfCustomer.get();
    }
    
    public String getCustomerCity() {
        return cityOfCustomer.get();
    }
    public String getCustomerZip() {
        return zipOfCustomer.get();
    }
    public String getCustomerPhone() {
        return phoneOfCustomer.get();
    }
    
    
    
    public void setCustomerId(int customerId) {
        this.idOfCustomer.set(customerId);
    }
    
    public void setCustomerName(String customerName) {
        this.nameOfCustomer.set(customerName);
    }
    
    public void setCustomerAddress(String customerAddress) {
        this.addressOfCustomer.set(customerAddress);
    }
    public void setCustomerAddress2(String customerAddress2) {
        this.address2OfCustomer.set(customerAddress2);
    }
    
    public void setCustomerCity(String customerCity) {
        this.cityOfCustomer.set(customerCity);
    }
    public void setCustomerZip(String postalCode) {
        this.zipOfCustomer.set(postalCode);
    }
    public void setCustomerPhone(String customerPhone) {
        this.phoneOfCustomer.set(customerPhone);
    }
    
//    public void setCustomerZip(String customerZip) {
//        this.zipOfCustomer.set(customerZip);
//    }
    
    public static void updateCustomer(int index, Customer customer) {
        everyCustomer.set(index, customer);
    }
    public static ObservableList<Customer> getCustomer() {
        return everyCustomer;
    }
    public static String isCustomerInputValid(String name, String address, String city, String zip, String phone, String inputError) {
        if (name == null || address == null || city == null || phone == null || zip == null ) {
            inputError = " field cannot be left blank.";
        }
//        if (inventory < 1) {
//            inputError = "The inventory must be greater than 0.";
//        }
//        if (price < 1) {
//            inputError = "The price cannot be less than $0";
//        }
//        if (min > max) {
//            inputError = "The inventory minimum must be less than the inventory maximum.";
//        }
//        if (inventory < min || inventory > max) {
//            inputError = "Part inventory must be between minimum and maximum values.";
//        }
        return inputError;
    }
    
 }                

