package Views;

import Entite.Reclamation;
import ServicesReclamations.ReclamationService;
import Views.AjoutModifController;
import Views.SampleController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utiles.MyCnx;

/**
 */
public class SongController {
 
Reclamation d = new Reclamation();
private Reclamation don;
    @FXML
    private Button BtnUpdate;
    @FXML
    private Button DeleteBtn;
    ReclamationService ps = new ReclamationService();
    @FXML
    private Label categ;
     HashMap<String, String> categoryUnits = new HashMap<>();
    @FXML
    private Button ClaimBtn;
    @FXML
    private Button MapBtn;
    @FXML
    private ImageView img;
    @FXML
    private Label Date;
    @FXML
    private Label etat;
    @FXML
    private Label motif;
    @FXML
    private Label numero;
    @FXML
    private Label email;

     public void setData(Reclamation don) throws SQLException{
        /*categoryUnits.put("Appareil","Obj");
        categoryUnits.put("Argent", "D");
        categoryUnits.put("Nourriture", "Kg");
        categoryUnits.put("Habillement", "Piece");
        categoryUnits.put("Sang", "L");
        categoryUnits.put("Livres", "Livre");
        categoryUnits.put("Jouet", "Jouet");*/
     Date.setText(don.getData_reclamation().toString());
        etat.setText(don.getEtat());
        motif.setText(don.getMotif_de_reclamation());
        numero.setText(Integer.toString(don.getNum_telephone()));
        email.setText(don.getEmail());
   img.setImage(new Image("file:src\\uploads\\"+don.getImage()+".png"));

        
        if (don.getCategorie_rec().getId()== 0) {
    categ.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = MyCnx.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT nom FROM categorie_rec WHERE id = ?");
ps.setInt(1, don.getCategorie_rec().getId());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
           
if (nomCategorie != null) {
    categ.setText(nomCategorie);
}
else{System.out.println("Can't");}
} else {
    categ.setText("Catégorie introuvable");
}
   
   }
@FXML
    private void ToUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutModif.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    AjoutModifController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Reclamation d) {
        this.d=d;
        don=d;
    }
@FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
    System.out.println(don);
        ps.delete(don.getId());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Controller aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    }   
}

