package animalerp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This software is destined for helping the cooperation of an animal rescuing association.
 * Functions: 
 * storing the organization's personal contacts,
 * storing data of animals under the organization's care,
 * creating and tracking assignments for the members.
 * @author David Bailo
 */

public class AnimalERP extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root, 400, 400);
        
        stage.setScene(scene);
        stage.setTitle("Animal Shelter RP");
        stage.setResizable(false);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
