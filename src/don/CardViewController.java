/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import Entities.Don;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import Entities.Don;
import Service.ServiceDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import utils.db_connect;
/**
 * FXML Controller class
 *
 * @author 21629
 */
public class CardViewController implements Initializable {

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
Don d = new Don();
private Don don;
    @FXML
    private Button BtnUpdate;
    @FXML
    private Button DeleteBtn;
    ServiceDon ps = new ServiceDon();
    @FXML
    private Label categ;
     HashMap<String, String> categoryUnits = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setData(Don don) throws SQLException{
        categoryUnits.put("Appareil","Obj");
        categoryUnits.put("Argent", "D");
        categoryUnits.put("Nourriture", "Kg");
        categoryUnits.put("Habillement", "Piece");
        categoryUnits.put("Sang", "L");
        categoryUnits.put("Livres", "Livre");
        categoryUnits.put("Jouet", "Jouet");
     DonName.setText(don.getNameD());
        Quantite.setText(Integer.toString(don.getQuantite()));
        Email.setText(don.getEmail());
        Numero.setText(Integer.toString(don.getNumero()));
   img.setImage(new Image("file:src\\uploads\\"+don.getImage()+".png"));
   if (don.getCategory_d_id()== 0) {
    categ.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = db_connect.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT name_ca FROM category_d WHERE id = ?");
ps.setInt(1, don.getCategory_d_id());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
            String measurementUnit = categoryUnits.get(nomCategorie);
if (measurementUnit != null) {
    categ.setText(nomCategorie);
 Quantite.setText(Integer.toString(don.getQuantite())+" "+measurementUnit);
}
else{System.out.println("Can't");}
} else {
    categ.setText("Catégorie introuvable");
}
   img.setFitWidth(450);
img.setFitHeight(250);
   }

    @FXML
    private void ToUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    AddUpdateController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Don d) {
        this.d=d;
        don=d;
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
 
        ps.supprimer(don);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        SampleController aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    }
     
}
