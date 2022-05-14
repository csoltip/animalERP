package pojo;

import javafx.beans.property.SimpleStringProperty;

/**
 * Egyszerű JAVA objektumok az FELADATOK táblázat rekordjaihoz.
 * @author Dávid
 */

public class Feladat {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty statusz;
    private final SimpleStringProperty targy;
    private final SimpleStringProperty felelos;
    private final SimpleStringProperty modositva;
    

//<editor-fold defaultstate="collapsed" desc="Getterek és Setterek">
    public String getId() {
        return id.get();
    }
    public void setID(String identitas) {
        id.set(identitas);
    }
    public String getStatusz() {
        return statusz.get();
    }
    public void setStatusz(String stat) {
        statusz.set(stat);
    }
    public String getTargy() {
        return targy.get();
    }
    public void setTargy(String trgy) {
        targy.set(trgy);
    }
    public String getFelelos() {
        return felelos.get();
    }
    public void setFelelos(String felel) {
        felelos.set(felel);
    }
    public String getModositva() {
        return modositva.get();
    }
    public void setModositva(String mod) {
        modositva.set(mod);
    }
//</editor-fold>
    
    public Feladat(){
        this.id = new SimpleStringProperty();
        this.statusz = new SimpleStringProperty("");
        this.targy = new SimpleStringProperty("");
        this.felelos = new SimpleStringProperty("");
        this.modositva = new SimpleStringProperty("");
        }
    
    
    public Feladat(String stat, String trgy, String felel){
        this.id = new SimpleStringProperty("");
        this.modositva = new SimpleStringProperty("");
        this.statusz = new SimpleStringProperty(stat);
        this.targy = new SimpleStringProperty(trgy);
        this.felelos = new SimpleStringProperty(felel);
        }
 
    public Feladat(Integer id, String stat, String trgy, String felel, String mod){
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.statusz = new SimpleStringProperty(stat);
        this.targy = new SimpleStringProperty(trgy);
        this.felelos = new SimpleStringProperty(felel);
        this.modositva = new SimpleStringProperty(mod);
        }
}
