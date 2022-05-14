package animalerp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Állatvédő Egyesület irányítását segítő, rendszerező szoftver.
 * Funkciók: 
 * a szervezettel kapcsolatba lépett személyek adatainak tárolása, 
 * a szervezettel kapcsolatba lépett állatok adatainak tárolása, 
 * feladatok létrehozása és nyomonkövetése az egyesület tagjai számára.
 * @author Bailo Dávid
 * @version 1.6
 */

public class AnimalERP extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root, 400, 400);
        
        stage.setScene(scene);
        stage.setTitle("Állatvédő ERP");
        stage.setResizable(false);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
