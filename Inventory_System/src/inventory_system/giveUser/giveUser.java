/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system.giveUser;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class giveUser {
    
    private final StringProperty id;
    private final StringProperty mac_address;
    private final StringProperty brand;
    private final StringProperty model;
    private final StringProperty version;
    private final StringProperty addition_date;
    private final StringProperty user_name;
    private final StringProperty user_phone;
    private final StringProperty worker_name;
    private final StringProperty date_assigned;
    

    //Constructor
    public giveUser(String id, String mac_address, String brand, String model, String version, String addition_date,String user_name,String user_phone,String worker_name,String date_assigned) {

        this.id = new SimpleStringProperty(id);
        this.mac_address = new SimpleStringProperty(mac_address);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.version = new SimpleStringProperty(version);
        this.addition_date = new SimpleStringProperty(addition_date);
        this.user_name=new SimpleStringProperty(user_name);
        this.user_phone=new SimpleStringProperty(user_phone);
        this.worker_name=new SimpleStringProperty(worker_name);
        this.date_assigned=new SimpleStringProperty(date_assigned);

    }

    //Getters
    public String getId() {
        return id.get();
    }

    public String getMac_address() {
        return mac_address.get();
    }

    public String getBrand() {
        return brand.get();
    }

    public String getModel() {
        return model.get();
    }

    public String getVersion() {
        return version.get();
    }

    public String getAddition_date() {
        return addition_date.get();
    }
    
     public String getUser_name(){
         return user_name.get();
     }
     
     public String getUser_phone(){
         return user_phone.get();
     }
     
     public String getWorker_name(){
         return worker_name.get();
     }
     
     public String getDate_assigned(){
         return date_assigned.get();
     }
     
     
    //Setters
    public void setId(String Value) {
        id.set(Value);
    }

    public void setMac_address(String Value) {
        mac_address.set(Value);
    }

    public void setBrand(String Value) {
        brand.set(Value);
    }

    public void setModel(String Value) {
        model.set(Value);
    }

    public void setVersion(String Value) {
        version.set(Value);
    }

    public void setAddition_date(String Value) {
        addition_date.set(Value);
    }
    
     public void setUser_name(String Value){
          user_name.set(Value);
     }
     
     public void setUser_phone(String Value){
         user_phone.set(Value);
     }
     
     public void settWorker_name(String Value){
         worker_name.set(Value);
     }
     
     public void setDate_assigned(String Value){
         date_assigned.set(Value);
     }

    //Property values
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty mac_addressProperty() {
        return mac_address;
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public StringProperty modelProperty() {
        return model;
    }

    public StringProperty versionProperty() {
        return version;
    }

    public StringProperty addition_dateProperty() {
        return addition_date;
    }
    
    public StringProperty user_nameProperty() {
        return user_name;
    }
    
    public StringProperty user_phoneProperty() {
        return user_phone;
    }
    
     public StringProperty worker_nameProperty() {
        return worker_name;
    }
     
     public StringProperty date_assignedProperty() {
        return date_assigned;
    }
     
     
    
    
    
}
    

