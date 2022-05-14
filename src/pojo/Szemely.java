package pojo;

import javafx.beans.property.SimpleStringProperty;

/**
 * Egyszerű JAVA objektumok a SZEMÉLYEK táblázat rekordjaihoz.
 * @author Dávid
 */

public class Szemely {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty nev;
    private final SimpleStringProperty kategoria;
    private final SimpleStringProperty telefonszam;
    private final SimpleStringProperty lakcim;
   
    

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
    public String getKategoria() {
        return kategoria.get();
    }
    public void setKategoria(String kat) {
        kategoria.set(kat);
    }
    public String getTelefonszam() {
        return telefonszam.get();
    }
    public void setTelefonszam(String szam) {
        telefonszam.set(szam);
    }    
    public String getLakcim() {
        return lakcim.get();
    }
    public void setLakcim(String cim) {
        lakcim.set(cim);
    }
   
//</editor-fold>
    
    public Szemely(){
        this.id = new SimpleStringProperty("");
        this.nev = new SimpleStringProperty("");
        this.kategoria = new SimpleStringProperty("");
        this.telefonszam = new SimpleStringProperty("");
        this.lakcim = new SimpleStringProperty("");
        
        }
    
    public Szemely(String neve, String kat, String szam, String cim){
        this.nev = new SimpleStringProperty(neve);
        this.kategoria = new SimpleStringProperty(kat);
        this.telefonszam = new SimpleStringProperty(szam);
        this.lakcim = new SimpleStringProperty(cim);
        this.id = new SimpleStringProperty("");
        
        }
    
    public Szemely(Integer id, String neve, String kat, String szam, String cim){
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.nev = new SimpleStringProperty(neve);
        this.kategoria = new SimpleStringProperty(kat);
        this.telefonszam = new SimpleStringProperty(szam);
        this.lakcim = new SimpleStringProperty(cim);
        
        }
    
    public Szemely(String neve){
        this.nev = new SimpleStringProperty(neve);
        this.id = new SimpleStringProperty("");
        this.kategoria = new SimpleStringProperty("");
        this.telefonszam = new SimpleStringProperty("");
        this.lakcim = new SimpleStringProperty("");
        }
}
