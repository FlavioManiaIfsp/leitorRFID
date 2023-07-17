package rfid_educa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaPrincipalController implements Initializable {

    @FXML
    private Button btnAcao;
    @FXML
    private Button btnGravar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void consultaLetras(ActionEvent event) throws IOException {
        abrirFormulario("ConsultaPecas", "RFID Educacional - Java FX");
    }
    
    @FXML
    private void inserirLetras(ActionEvent event) throws IOException {
        abrirFormulario("CadastroPecas", "RFID Educacional - Java FX");
    }    
    
    public void abrirFormulario(String formulario, String titulo) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/rfid_educa/fxml/"+formulario+".fxml"));      
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }     


    
}
