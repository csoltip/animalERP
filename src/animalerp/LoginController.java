package animalerp;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Bejelentkezés validálása
 * @author Bailo Dávid
 */

public class LoginController implements Initializable{

//<editor-fold defaultstate="collapsed" desc="FXML deklaráció">
    @FXML
    private TextField FelhasznaloSor;
    @FXML
    private PasswordField jelszoSor;
    @FXML
    private Button belepesGmb;
    @FXML
    private Button bezarasGmb;
    @FXML
    private Label hibaUzenet;
//</editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void belepesGmbAkcio(ActionEvent event){
        if( FelhasznaloSor.getText().isEmpty() == false && jelszoSor.getText().isEmpty() == false){
            belepesValidalas();
        }else{
            hibaUzenet.setText("Nem adtál meg felhasználónevet/jelszót!");
        }
    }
    
    public void bezarasGmbAkcio(ActionEvent event){
        Stage stage = (Stage) bezarasGmb.getScene().getWindow();
        stage.close();
    }
    
    public void belepesValidalas(){
        
        DB kapcsolatMost = new DB();
        Connection kapcsolatDB = kapcsolatMost.kapcsolat();
        String igazolas = "select count(1) from belepes where felhasznalonev=? and jelszo=?";
        try{
        PreparedStatement preparedStatement = kapcsolatDB.prepareStatement(igazolas);
        preparedStatement.setString(1, FelhasznaloSor.getText());
        preparedStatement.setString(2, jelszoSor.getText());
        ResultSet rs = preparedStatement.executeQuery();
        
            while(rs.next()){
                if( rs.getInt(1) == 1 ){
                    
                    Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
                    
                    Stage stage = (Stage) bezarasGmb.getScene().getWindow();
                    stage.setTitle("Állatvédő Egyesület Irányítási Rendszer");
                    stage.setScene(new Scene(root,980,850));
                    stage.setResizable(true);
                    stage.show();
                    stage.setMinWidth(stage.getWidth());
                    stage.setMinHeight(stage.getHeight());
                }else{
                    hibaUzenet.setText("Rossz belépési adatok");
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
