package Associations.Views;

import Associations.Models.Association;
import Associations.Services.ServiceAssociation;

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
import Associations.Utils.MyCnx;

/**
 */
public class SongController {
    @FXML
    private ImageView img;
    @FXML
    private Label DonName;
    @FXML
    private Label Quantite;
    @FXML
    private Label Email;
    @FXML
    private Label Numero;
Association d = new Association();
private Association don;
    @FXML
    private Button BtnUpdate;
    @FXML
    private Button DeleteBtn;
    ServiceAssociation ps = new ServiceAssociation();
    @FXML
    private Label categ;
     HashMap<String, String> categoryUnits = new HashMap<>();

     public void setData(Association don) throws SQLException{
         
          BtnUpdate.setVisible(false);
    
     DeleteBtn.setVisible(false);
    
        categoryUnits.put("Appareil","Obj");
        categoryUnits.put("Argent", "D");
        categoryUnits.put("Nourriture", "Kg");
        categoryUnits.put("Habillement", "Piece");
        categoryUnits.put("Sang", "L");
        categoryUnits.put("Livres", "Livre");
        categoryUnits.put("Jouet", "Jouet");
     DonName.setText(don.getNom());
        Quantite.setText(Integer.toString(don.getCodePostal()));
        Email.setText(don.getMail());
        Numero.setText(Integer.toString(don.getNumero()));
   img.setImage(new Image("file:src\\uploads\\"+don.getImage()+".png"));

        
        if (don.getCategorie()== 0) {
    categ.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = MyCnx.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT name_ca FROM category_d WHERE id = ?");
ps.setInt(1, don.getCategorie());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAssociation.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    EditAssociationController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Association d) {
        this.d=d;
        don=d;
    }
@FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
    System.out.println(don);
        ps.supprimer(don);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
        FrontController aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    }


   
}

