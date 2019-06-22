/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Tony
 */
public final class Appointment {
//     private int appointmentId;
//    private Customer customer;
//    private String title;
//    private String description;
//    private String location;
//    private String contact;
//    private String url;
    private final SimpleIntegerProperty appointmentId = new SimpleIntegerProperty();
    private final SimpleIntegerProperty customerId = new SimpleIntegerProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();
    private final SimpleStringProperty location = new SimpleStringProperty();
    private final SimpleStringProperty contact = new SimpleStringProperty();
    private final SimpleStringProperty url = new SimpleStringProperty();
    private final SimpleStringProperty start = new SimpleStringProperty();
    private final SimpleStringProperty end = new SimpleStringProperty();


    public Appointment() {}
//    public Appointment(int appointmentId, String location, String contact, String title, String description, Customer customer, String url) {
//        this.appointmentId = appointmentId;    
//        this.location = location;
//        this.contact = contact;
//        this.title = title;
//        this.description = description;
//        this.customer = customer;
//        this.url = url;
//    }
    public IntegerProperty appointmentIdProperty() {
        return appointmentId;
    }
    public IntegerProperty customerIdProperty() {
        return customerId;
    }
    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public StringProperty locationProperty() {
        return location;
    }
    public StringProperty contactProperty() {
        return contact;
    }   
     public StringProperty urlProperty() {
        return url;
    }
     public StringProperty startProperty() {
        return start;
    }
     public StringProperty endProperty() {
        return end;
    }
    
    public Appointment(int appointmentID, int customerId, String title, String description, String location, String contact, String url, String start, String end) {
        setAppointmentId(appointmentID);
        setAppointmentCustomerId(customerId);
        setAppointmentTitle(title);
        setAppointmentDescription(description);
        setAppointmentLocation(location);
        setAppointmentContact(contact);
        setAppointmentStart(start);
        setAppointmentUrl(url);
        setAppointmentEnd(end);

    }
    public Appointment(int appointmentID, int customerId, String title, String description, String location, String contact, String start, String end) {
        setAppointmentId(appointmentID);
        setAppointmentCustomerId(customerId);
        setAppointmentTitle(title);
        setAppointmentDescription(description);
        setAppointmentLocation(location);
        setAppointmentContact(contact);
        setAppointmentStart(start);
        setAppointmentEnd(end);

    }
    public Appointment(int appointmentID, int customerId, String title, String description, String location, String contact) {
        setAppointmentId(appointmentID);      
        setAppointmentCustomerId(customerId);
        setAppointmentTitle(title);
        setAppointmentDescription(description);
        setAppointmentLocation(location);
        setAppointmentContact(contact);
    }
    public Appointment(int appointmentID, String title, String description, String start, String end) {
        setAppointmentId(appointmentID);      
        setAppointmentTitle(title);
        setAppointmentDescription(description);
        setAppointmentStart(start);
        setAppointmentEnd(end);
    }
    public int getAppointmentId() {
        return appointmentId.get();
    }
    
    public int getAppointmentCustomerId() {
        return customerId.get();
    }
    
    public String getAppointmentLocation() {
        return location.get();
    }
    public String getAppointmentContact() {
        return contact.get();
    }
//    public String getAptStart() {
//        return aptStart.get();
//    }
    
    public String getAppointmentTitle() {
        return title.get();
    }
    
    public String getAppointmentDescription() {
        return description.get();
    }
    public String getAppointmentUrl() {
        return url.get();
    }
    public String getAppointmentStart() {
        return start.get();
    }
    public String getAppointmentEnd() {
        return end.get();
    }
    
    public Appointment(int appointmentID, String title, String description) {
        setAppointmentId(appointmentID);      
        setAppointmentTitle(title);
        setAppointmentDescription(description);


    }
    
    
    
    /**
     *
     * @param appointmentId
     */
    
    
//    public void setAptCustId(int aptCustId) {
//        this.aptCustId.set(aptCustId);
//    }
    
//    public void setAptEnd(String aptEnd) {
//        this.aptEnd.set(aptEnd);
//    }
//    
//    public void setAptStart(String aptTimeStart) {
//        this.aptStart.set(aptTimeStart);
//    } 
    public void setAppointmentId(int appointmentId) {
        this.appointmentId.set(appointmentId);
    }
    public void setAppointmentCustomerId(int customerId) {
        this.customerId.set(customerId);
    }
    public void setAppointmentLocation(String location) {
        this.location.set(location);
    }
    public void setAppointmentContact(String contact) {
        this.contact.set(contact);
    }
    
    public void setAppointmentTitle(String title) {
        this.title.set(title);
    }
    
    public void setAppointmentDescription(String description) {
        this.description.set(description);
    }
    public void setAppointmentUrl(String url) {
        this.url.set(url);
    }
    public void setAppointmentStart(String start) {
        this.start.set(start);
    }
    public void setAppointmentEnd(String end) {
        this.end.set(end);
    }
    
}
