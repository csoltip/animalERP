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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * User validating
 * @author Bailo Dávid
 */

public class LoginController implements Initializable{

//<editor-fold defaultstate="collapsed" desc="FXML declaration">
    @FXML
    private TextField userField;
    @FXML
    private PasswordField pwdField;
    @FXML
    private Button exitButton;
    @FXML
    private Label errorMessage;
//</editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void loginButtonAction(ActionEvent event){
        if( userField.getText().isEmpty() == false && pwdField.getText().isEmpty() == false){
            loginValidation();
        }else{
            errorMessage.setText("You did not give a username and/or password!");
        }
    }
    
    public void exitButtonAction(ActionEvent event){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
    public void loginValidation(){
        
        DB validate = new DB();
        Connection validateConn = validate.connection();
        String igazolas = "select count(1) from login where username=? and password=?";
        try{
        PreparedStatement preparedStatement = validateConn.prepareStatement(igazolas);
        preparedStatement.setString(1, userField.getText());
        preparedStatement.setString(2, pwdField.getText());
        ResultSet rs = preparedStatement.executeQuery();
        
            while(rs.next()){
                if( rs.getInt(1) == 1 ){
                    
                    Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
                    
                    Stage stage = (Stage) exitButton.getScene().getWindow();
                    stage.setTitle("Animal Shelter Resource Planner");
                    stage.setScene(new Scene(root,980,850));
                    stage.setResizable(true);
                    stage.show();
                    stage.setMinWidth(stage.getWidth());
                    stage.setMinHeight(stage.getHeight());
                }else{
                    errorMessage.setText("Wrong credentials.");
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
