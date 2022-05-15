package pojo;

import javafx.beans.property.SimpleStringProperty;

/**
 * Plain old java object for the ANIMALS table's records.
 * @author Dávid
 */

public class Animal {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty breed;
    private final SimpleStringProperty sex;
    private final SimpleStringProperty owner;
    private final SimpleStringProperty status;
    private final SimpleStringProperty recordedAt;
    

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
    public String getBreed() {
        return breed.get();
    }
    public void setFajta(String breed) {
        this.breed.set(breed);
    }
    public String getSex() {
        return sex.get();
    }
    public void setSex(String sex) {
        this.sex.set(sex);
    }
    public String getOwner() {
        return owner.get();
    }
    public void setOwner(String owner) {
        this.owner.set(owner);
    }    
    public String getStatusz() {
        return status.get();
    }
    public void setStatus(String status) {
        this.status.set(status);}
    public String getRecordedAt() {
        return recordedAt.get();
    }
    public void setRecordedAt(String recordedAt) {
        this.recordedAt.set(recordedAt);
    }
//</editor-fold>
    
    public Animal(){
        this.id = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.breed = new SimpleStringProperty("");
        this.sex = new SimpleStringProperty("");
        this.owner = new SimpleStringProperty("");
        this.status = new SimpleStringProperty("");
        this.recordedAt = new SimpleStringProperty("");
        }
    
    public Animal(String name, String breed, String sex, String owner, String status){
        this.id = new SimpleStringProperty("");
        this.recordedAt = new SimpleStringProperty("");
        this.name = new SimpleStringProperty(name);
        this.breed = new SimpleStringProperty(breed);
        this.sex = new SimpleStringProperty(sex);
        this.owner = new SimpleStringProperty(owner);
        this.status = new SimpleStringProperty(status);
        
        }
 
    public Animal(Integer id, String name, String breed, String sex, String owner, String status, String recordedAt){
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.name = new SimpleStringProperty(name);
        this.breed = new SimpleStringProperty(breed);
        this.sex = new SimpleStringProperty(sex);
        this.owner = new SimpleStringProperty(owner);
        this.status = new SimpleStringProperty(status);
        this.recordedAt = new SimpleStringProperty(recordedAt);
        }
    
}
