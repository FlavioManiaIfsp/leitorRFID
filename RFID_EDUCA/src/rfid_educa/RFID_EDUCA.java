package rfid_educa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RFID_EDUCA extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/rfid_educa/fxml/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("RFID Educacional - Java FX");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();         
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
