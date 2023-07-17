package rfid_educa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import rfid_educa.entidade.Pecas;
import rfid_educa.persistencia.PecasDAO;

public class ConsultaPecasController implements Initializable {
        
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Text lblConteudo;
    
    private List<Pecas> listaPecas;
    @FXML
    private ImageView imgPeca;
    
    private String base64String="";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limparCampos();
    }    

    @FXML
    private void pesquisar(ActionEvent event) throws IOException {                
        listaPecas = PecasDAO.consultar(txtPesquisa.getText());
        for(Pecas p : listaPecas){
            lblConteudo.setText(p.getDescricao());
            base64String = p.getImagem();
            String caminho = "C:/Users/flavi/OneDrive/Documentos/NetBeansProjects/RFID_EDUCA/src/rfid_educa/imagens/teste.png";
            try (FileOutputStream stream = new FileOutputStream(new File(caminho))) {
                stream.write(PecasDAO.decode(base64String));                
            }
            Image image = new Image(new FileInputStream(caminho));
            imgPeca.setImage(image);
        }        
        txtPesquisa.clear();
    }          
    
    private void limparCampos(){
        listaPecas = null;
        lblConteudo.setText("");
        txtPesquisa.clear();
        imgPeca.setId(null);
    }
}
