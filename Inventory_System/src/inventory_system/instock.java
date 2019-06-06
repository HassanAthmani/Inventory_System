/*
 a model class that hold setters and getters plus properties.
 */
package inventory_system;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class instock {

    private final StringProperty id;
    private final StringProperty mac_address;
    private final StringProperty brand;
    private final StringProperty model;
    private final StringProperty version;
    private final StringProperty addition_date;

    //Constructor
    public instock(String id, String mac_address, String brand, String model, String version, String addition_date) {

        this.id = new SimpleStringProperty(id);
        this.mac_address = new SimpleStringProperty(mac_address);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.version = new SimpleStringProperty(version);
        this.addition_date = new SimpleStringProperty(addition_date);

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

}
