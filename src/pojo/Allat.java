package pojo;

import javafx.beans.property.SimpleStringProperty;

/**
 * Egyszerű JAVA objektumok az ÁLLATOK táblázat rekordjaihoz.
 * @author Dávid
 */

public class Allat {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty nev;
    private final SimpleStringProperty fajta;
    private final SimpleStringProperty nem;
    private final SimpleStringProperty gazda;
    private final SimpleStringProperty statusz;
    private final SimpleStringProperty rogzitesDatum;
    

//<editor-fold defaultstate="collapsed" desc="Getterek és Setterek">
    public String getId() {
        return id.get();
    }
    public void setID(String identitas) {
        id.set(identitas);
    }
    public String getNev() {
        return nev.get();
    }
    public void setNev(String neve) {
        nev.set(neve);
    }
    public String getFajta() {
        return fajta.get();
    }
    public void setFajta(String faj) {
        fajta.set(faj);
    }
    public String getNem() {
        return nem.get();
    }
    public void setNem(String neme) {
        nem.set(neme);
    }
    public String getGazda() {
        return gazda.get();
    }
    public void setGazda(String gazdi) {
        gazda.set(gazdi);
    }    
    public String getStatusz() {
        return statusz.get();
    }
    public void setStatusz(String stat) {
        statusz.set(stat);}
    public String getRogzitesDatum() {
        return rogzitesDatum.get();
    }
    public void setRogzitesDatum(String datum) {
        rogzitesDatum.set(datum);
    }
//</editor-fold>
    
    public Allat(){
        this.id = new SimpleStringProperty("");
        this.nev = new SimpleStringProperty("");
        this.fajta = new SimpleStringProperty("");
        this.nem = new SimpleStringProperty("");
        this.gazda = new SimpleStringProperty("");
        this.statusz = new SimpleStringProperty("");
        this.rogzitesDatum = new SimpleStringProperty("");
        }
    
    public Allat(String neve, String fajta, String neme, String gazdi, String stat){
        this.id = new SimpleStringProperty("");
        this.rogzitesDatum = new SimpleStringProperty("");
        this.nev = new SimpleStringProperty(neve);
        this.fajta = new SimpleStringProperty(fajta);
        this.nem = new SimpleStringProperty(neme);
        this.gazda = new SimpleStringProperty(gazdi);
        this.statusz = new SimpleStringProperty(stat);
        
        }
 
    public Allat(Integer id, String neve, String fajta, String neme, String gazdi, String stat, String datum){
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.nev = new SimpleStringProperty(neve);
        this.fajta = new SimpleStringProperty(fajta);
        this.nem = new SimpleStringProperty(neme);
        this.gazda = new SimpleStringProperty(gazdi);
        this.statusz = new SimpleStringProperty(stat);
        this.rogzitesDatum = new SimpleStringProperty(datum);
        }
    
}
