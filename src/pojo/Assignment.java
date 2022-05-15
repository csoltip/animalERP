package pojo;

import javafx.beans.property.SimpleStringProperty;

/**
 * Plain old JAVA object for the ASSIGNMENTS table's records.
 * @author Dávid
 */

public class Assignment {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty status;
    private final SimpleStringProperty subject;
    private final SimpleStringProperty assignee;
    private final SimpleStringProperty modifiedAt;
    

//<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getId() {
        return id.get();
    }
    public void setID(String id) {
        this.id.set(id);
    }
    public String getStatus() {
        return status.get();
    }
    public void setStatusz(String status) {
        this.status.set(status);
    }
    public String getSubject() {
        return subject.get();
    }
    public void setSubject(String subject) {
        this.subject.set(subject);
    }
    public String getAssignee() {
        return assignee.get();
    }
    public void setAssignee(String assignee) {
        this.assignee.set(assignee);
    }
    public String getModifiedAt() {
        return modifiedAt.get();
    }
    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt.set(modifiedAt);
    }
//</editor-fold>
    
    public Assignment(){
        this.id = new SimpleStringProperty();
        this.status = new SimpleStringProperty("");
        this.subject = new SimpleStringProperty("");
        this.assignee = new SimpleStringProperty("");
        this.modifiedAt = new SimpleStringProperty("");
        }
    
    
    public Assignment(String status, String subject, String assignee){
        this.id = new SimpleStringProperty("");
        this.modifiedAt = new SimpleStringProperty("");
        this.status = new SimpleStringProperty(status);
        this.subject = new SimpleStringProperty(subject);
        this.assignee = new SimpleStringProperty(assignee);
        }
 
    public Assignment(Integer id, String status, String subject, String assignee, String modifiedAt){
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.status = new SimpleStringProperty(status);
        this.subject = new SimpleStringProperty(subject);
        this.assignee = new SimpleStringProperty(assignee);
        this.modifiedAt = new SimpleStringProperty(modifiedAt);
        }
}
