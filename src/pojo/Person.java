package pojo;

import javafx.beans.property.SimpleStringProperty;

/**
 * Plain old JAVA object for the ASSIGNMENTS table's records.
 * @author Dávid
 */

public class Person {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty category;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty address;
   
    

//<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getId() {
        return id.get();
    }
    public void setID(String id) {
        this.id.set(id);
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getCategory() {
        return category.get();
    }
    public void setCategory(String category) {
        this.category.set(category);
    }
    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }    
    public String getAddress() {
        return address.get();
    }
    public void setAddress(String address) {
        this.address.set(address);
    }
   
//</editor-fold>
    
    public Person(){
        this.id = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.category = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        
        }
    
    public Person(String name, String category, String phone, String address){
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.id = new SimpleStringProperty("");
        
        }
    
    public Person(Integer id, String name, String category, String phone, String address){
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        
        }
    
    public Person(String name){
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty("");
        this.category = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        }
}
