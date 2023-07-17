package rfid_educa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rfid_educa.entidade.Pecas;
import rfid_educa.persistencia.PecasDAO;

public class CadastroPecasController implements Initializable {

    @FXML
    private TextField txtRFID;
    @FXML
    private TextField txtDescricao;
    @FXML
    private ImageView imgPeca;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnLocImagem;
    
    private String base64String="";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtRFID.requestFocus();
    }

    @FXML
    private void salvarPeca(ActionEvent event) {
        Pecas pecas = new Pecas();
        pecas.setIdpeca(txtRFID.getText());
        pecas.setDescricao(txtDescricao.getText());
        pecas.setImagem(base64String);
        PecasDAO.inserir(pecas);
        limparCamposFormulario();
    }

    public void limparCamposFormulario() {
        txtRFID.clear();
        txtDescricao.clear();
        txtRFID.requestFocus();
        imgPeca.setImage(null);
    }

    @FXML
    private void selecionarImagem(ActionEvent event) throws IOException {        
        FileChooser selArquivo = new FileChooser();
        File arquivo = selArquivo.showOpenDialog(new Stage());
        Image image = new Image(new FileInputStream(arquivo));
        //converter imagem em Base64
        base64String = PecasDAO.encode(arquivo.toString());
        //mostrar imagem no imageview
        imgPeca.setImage(image);
    }
}
