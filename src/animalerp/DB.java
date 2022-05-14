
package animalerp;

import pojo.Feladat;
import pojo.Allat;
import pojo.Szemely;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Lokális Derby adatbázis létrehozása és a táblák műveleteinek definiálása
 * @author Bailo Dávid
 */
public class DB {

    final String URL = "jdbc:derby:aerpDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    public Connection kapcsolat() {
        
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            System.out.println("Hiba0: " + ex);
        }
        
        return conn;
    }
    
    public DB() {

        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Létrejött a kapcsolat.");
        } catch (SQLException ex) {
            System.out.println("Hiba1: " + ex);
        }

        if (conn != null) {
            try {
                createStatement = conn.createStatement();
                System.out.println(" A CreateStatement létrehozva.");
            } catch (SQLException ex) {
                System.out.println("Hiba2: " + ex);
            }
        }

        try {
            dbmd = conn.getMetaData();
            System.out.println("Elérhetők a metaadatok.");
        } catch (SQLException ex) {
            System.out.println("Hiba3: " + ex);
        }
        
        // Belépés tábla  és admin felhasználó létrehozása
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "BELEPES", null);
            if (!rs.next()) {
                createStatement.execute("create table belepes(felhasznalo_id INT not null primary key GENERATED ALWAYS AS IDENTITY" +
                        "(START WITH 1, INCREMENT BY 1),felhasznalonev varchar(50), jelszo varchar(50))");
                String sql = "insert into belepes (felhasznalonev, jelszo) values(?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, "admin");
                preparedStatement.setString(2, "password");
                preparedStatement.execute();
                System.out.println("A belépési táblázat és admin felhasználó létrehozva.");
            }
        } catch (SQLException ex) {
            System.out.println("Hiba a belépési tábla elkészítésénél: " + ex);
        }
        
        // Személyek tábla létrehozása
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "SZEMELYEK", null);
            if (!rs.next()) {
                createStatement.execute("create table szemelyek(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),nev varchar(50), kategoria varchar(20), telefonszam varchar(20), lakcim varchar(50))");
                System.out.println("A Személyek táblázat létrehozva.");
            }
        } catch (SQLException ex) {
            System.out.println("Hiba a Személyek tábla elkészítésénél: " + ex);
        }
        
        //Állatok tábla létrehozása
        try {
            ResultSet rs2 = dbmd.getTables(null, "APP", "ALLATOK", null);
            if (!rs2.next()) {
                createStatement.execute("create table allatok(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),nev varchar(50), fajta varchar(20), nem varchar(10), gazda varchar(50), statusz varchar(20), rogzitesDatum varchar(20))");
                System.out.println("Az Állatok létrehozva.");
            }
        } catch (SQLException ex) {
            System.out.println("Hiba az ALLATOK tábla elkészítésénél: " + ex);
        }
        
        //Feladatok tábla létrehozása
        try {
            ResultSet rs3 = dbmd.getTables(null, "APP", "FELADATOK", null);
            if (!rs3.next()) {
                createStatement.execute("create table feladatok(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),statusz varchar(20), targy varchar(50), felelos varchar(50), modositva date)");
                System.out.println("A Feladatok létrehozva.");
            }
        } catch (SQLException ex) {
            System.out.println("Hiba a FELADATOK tábla elkészítésénél: " + ex);
        }
        
        
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Személyek táblázat">
    public ArrayList<Szemely> getOsszesSzemely() {
        String sql = "select * from szemelyek";
        ArrayList<Szemely> osszesSzemely = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            osszesSzemely = new ArrayList<>();
            while (rs.next()) {
                Szemely konkretSzemely = new Szemely(rs.getInt("id"), rs.getString("nev"), rs.getString("kategoria"), rs.getString("telefonszam"), rs.getString("lakcim"));
                osszesSzemely.add(konkretSzemely);
                System.out.println("Személyek összegyűjtve.");
            }
        } catch (SQLException ex) {
            System.out.println("Hiba az összes személy lekérdezésénél: " + ex);
        }
        return osszesSzemely;
    }
    
    public ArrayList<String> getTagSzemelyNev() {
        ArrayList<String> osszesSzemely = new ArrayList<String>();
        String sql = "select * from szemelyek where kategoria='Egyesületi tag'";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()) {
                osszesSzemely.add(rs.getString("nev"));
            }
        } catch (SQLException ex) {
            System.out.println("Hiba a tagok lekérdezésénél: " + ex);
        }
        return osszesSzemely;
    }
    
    public void ujSzemely(Szemely szemely) {
        try {
            String sql = "insert into szemelyek (nev, kategoria, telefonszam, lakcim) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, szemely.getNev());
            preparedStatement.setString(2, szemely.getKategoria());
            preparedStatement.setString(3, szemely.getTelefonszam());
            preparedStatement.setString(4, szemely.getLakcim());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Hiba az új személy felvételekor: " + ex);
        }
    }
    
    public void modositSzemely(Szemely szemely) {
        try {
            String sql = "update szemelyek set nev = ?, kategoria = ?, telefonszam = ?, lakcim = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, szemely.getNev());
            preparedStatement.setString(2, szemely.getKategoria());
            preparedStatement.setString(3, szemely.getTelefonszam());
            preparedStatement.setString(4, szemely.getLakcim());
            preparedStatement.setInt(5, Integer.parseInt(szemely.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Hiba a személy módosításánál: "+ex);
        }
    }
    
    public void torolSzemely(Szemely szemely) {
        try {
            String sql = "delete from szemelyek where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(szemely.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Hiba a személy törlésénél: "+ex);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Állatok táblázat">
    public ArrayList<Allat> getOsszesAllat() {
        String sql2 = "select * from allatok";
        ArrayList<Allat> osszesAllat = null;
        try {
            ResultSet rs2 = createStatement.executeQuery(sql2);
            osszesAllat = new ArrayList<>();
            while (rs2.next()) {
                Allat konkretAllat = new Allat(rs2.getInt("id"), rs2.getString("nev"), rs2.getString("fajta"),rs2.getString("nem"), rs2.getString("gazda"), rs2.getString("statusz"), rs2.getString("rogzitesDatum"));
                osszesAllat.add(konkretAllat);
                System.out.println("Állatok összegyűjtve.");
            }
        } catch (SQLException ex) {
            System.out.println("Hiba az összes állat lekérdezésénél: " + ex);
        }
        return osszesAllat;
    }
    
    
    public void ujAllat(Allat allat) {
        try {
            String sql2 = "insert into allatok (nev, fajta, nem, gazda, statusz) values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, allat.getNev());
            preparedStatement.setString(2, allat.getFajta());
            preparedStatement.setString(3, allat.getNem());
            preparedStatement.setString(4, allat.getGazda());
            preparedStatement.setString(5, allat.getStatusz());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Hiba az új állat létrehozásánál: " + ex);
        }
    }
    
    public void modositAllat(Allat allat) {
        try {
            String sql2 = "update allatok set nev = ?, fajta = ?, nem = ?, gazda = ?, statusz = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, allat.getNev());
            preparedStatement.setString(2, allat.getFajta());
            preparedStatement.setString(3, allat.getNem());
            preparedStatement.setString(4, allat.getGazda());
            preparedStatement.setString(5, allat.getStatusz());
            preparedStatement.setInt(6, Integer.parseInt(allat.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Hiba az állat módosításánál: "+ex);
        }
    }
    
    public void torolAllat(Allat allat) {
        try {
            String sql2 = "delete from allatok where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setInt(1, Integer.parseInt(allat.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Hiba az állat törlésénél: "+ex);
        }
    }
    
    public ArrayList<String> getAllatGazdaNev() {
        ArrayList<String> osszesSzemely = new ArrayList<String>();
        String sql = "select * from szemelyek where kategoria='Állattartó'";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()) {
                osszesSzemely.add(rs.getString("nev"));
            }
        } catch (SQLException ex) {
            System.out.println("Hiba az állattartók lekérdezésénél: " + ex);
        }
        return osszesSzemely;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Feladatok táblázat">
    public ArrayList<Feladat> getOsszesFeladat() {
        String sql3 = "select * from feladatok";
        ArrayList<Feladat> osszesFeladat = null;
        try {
            ResultSet rs3 = createStatement.executeQuery(sql3);
            osszesFeladat = new ArrayList<>();
            while (rs3.next()) {
                Feladat konkretFeladat = new Feladat(rs3.getInt("id"), rs3.getString("statusz"), rs3.getString("targy"), rs3.getString("felelos"), rs3.getString("modositva"));
                osszesFeladat.add(konkretFeladat);
                System.out.println("Feladatok összegyűjtve.");
            }
        } catch (SQLException ex) {
            System.out.println("Hiba az összes feladat lekérdezésénél: " + ex);
        }
        return osszesFeladat;
    }
    
    public void ujFeladat(Feladat feladat) {
        try {
            String sql3 = "insert into feladatok (statusz, targy, felelos) values(?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql3);
            preparedStatement.setString(1, feladat.getStatusz());
            preparedStatement.setString(2, feladat.getTargy());
            preparedStatement.setString(3, feladat.getFelelos());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Hiba a feladat hozzáadásánál: " + ex);
        }
    }
    
    public void modositFeladat(Feladat feladat) {
        try {
            String sql3 = "update feladatok set statusz = ?, targy = ?, felelos = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql3);
            preparedStatement.setString(1, feladat.getStatusz());
            preparedStatement.setString(2, feladat.getTargy());
            preparedStatement.setString(3, feladat.getFelelos());
            preparedStatement.setInt(4, Integer.parseInt(feladat.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Hiba a feladat módosításánál: "+ex);
        }
    }
    
    public void torolFeladat(Feladat feladat) {
        try {
            String sql3 = "delete from feladatok where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql3);
            preparedStatement.setInt(1, Integer.parseInt(feladat.getId()));
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Hiba a feladat törlésénél: "+ex);
        }
    }
    //</editor-fold>
    
}