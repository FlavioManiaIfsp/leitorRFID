package rfid_educa.persistencia;

import agenda.persistencia.Conexao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import rfid_educa.entidade.Pecas;

public class PecasDAO {
    public static void mostrar(String valor){
        System.out.println("Valor: "+valor);
    }         
    
    public static ArrayList<Pecas> consultar(String valor){
        ArrayList<Pecas> pecas = new ArrayList<Pecas>();
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT * FROM letras WHERE idletra = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, valor);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Pecas p = new Pecas();
                p.setIdpeca(resultado.getString("idletra"));
                p.setDescricao(resultado.getString("descricao"));
                p.setImagem(resultado.getString("imagem"));
                pecas.add(p);
            }
            resultado.close();
            stmt.close();
            conexao.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        return pecas;
    }
    
    public static boolean inserir(Pecas pecas) {
        try {
            Connection conexao = Conexao.getConnection();
            String SQL = "INSERT INTO letras(idletra, descricao, imagem) values(?, ?, ?)";
            PreparedStatement comando = conexao.prepareStatement(SQL);
            comando.setString(1, pecas.getIdpeca());
            comando.setString(2, pecas.getDescricao());
            comando.setString(3, pecas.getImagem());
            //comando.setByte(3,pecas.getImagem());   
            //comando.setByte(3, pecas.getImagem());
            comando.execute();
            comando.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }    
    
    //codificar a imagem em Base64, para armazenar no banco de dados
    public static String encode(String imagePath) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(imagePath));
        return Base64.getEncoder().encodeToString(data);
    }

    //decodificar a imagem em Base64 para mostrar no imageview
    public static byte[] decode(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
        
}
