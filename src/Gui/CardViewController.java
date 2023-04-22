/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import Entities.Evenement;
import Service.EvenementService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import utils.MyDB;
/**
 * FXML Controller class
 *
 * @author 21629
 */
public class CardViewController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label EventName;
    @FXML
    private Label Adresse;
    @FXML
    private Label Date_deb;
    @FXML
    private Label Date_fin;
  @FXML
    private Label  
ShowCategory;
  @FXML
    private Button BtnUpdate;
   @FXML
    private Button DeleteBtn;
  
  Evenement d = new Evenement();
  EvenementService ps = new EvenementService();
  
private Evenement Evenement;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setData(Evenement event) throws SQLException{
     EventName.setText(event.getNom_event());
        Adresse.setText(event.getLocalisation());
        Date_deb.setText(String.valueOf(event.getDate_debut()));
        Date_fin.setText(String.valueOf(event.getDate_fin()));
   img.setImage(new Image("file:src\\uploads\\"+event.getImage_event()+".png"));
   if (event.getCategoryId() == 0) {
    ShowCategory.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = MyDB.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT nom_categ_event FROM categorie WHERE id = ?");
ps.setInt(1, event.getCategoryId());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
    ShowCategory.setText(nomCategorie);
} else {
    ShowCategory.setText("Catégorie introuvable");
}
   
   
    }
    
      public void receiveObject(Evenement d) {
        this.d=d;
        Evenement=d;
          System.out.println(d);
    }
       @FXML
    private void ToUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    AddUpdateEController controller = loader.getController();
    controller.receiveObject(d);
   
    }
    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
        System.out.println(Evenement);
        ps.supprimer(Evenement);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        SampleController aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
        
        
        
    }
    
}
