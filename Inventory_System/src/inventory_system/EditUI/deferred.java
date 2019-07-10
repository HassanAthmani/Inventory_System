/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system.EditUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class deferred {
     private final StringProperty id;
    private final StringProperty mac_address;
    private final StringProperty brand;
    private final StringProperty model;
    private final StringProperty version;
    private final StringProperty addition_date;
    private final StringProperty deferred_date;
    private final StringProperty reason;

    //Constructor
    public deferred(String id, String mac_address, String brand, String model, String version, String addition_date, String deferred_date,String reason) {

        this.id = new SimpleStringProperty(id);
        this.mac_address = new SimpleStringProperty(mac_address);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.version = new SimpleStringProperty(version);
        this.addition_date = new SimpleStringProperty(addition_date);
        this.deferred_date = new SimpleStringProperty(deferred_date);
        this.reason = new SimpleStringProperty (reason);

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
    
    public String getDeferred_date(){
        return deferred_date.get();
    }
    
   public String getReason(){
       return reason.get();
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
    
    public void setDeferred_date(String Value){
        deferred_date.set(Value);
    }
    
    public void setReason(String Value){
        reason.set(Value);
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
    
    public StringProperty deferred_dateProperty(){
        return deferred_date;
    }
    
    public StringProperty reason(){
        return reason;
    }
    
}
