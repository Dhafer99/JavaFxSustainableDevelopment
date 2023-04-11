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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
}
