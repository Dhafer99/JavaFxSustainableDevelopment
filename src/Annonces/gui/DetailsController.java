/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.gui;

import Annonces.entities.Annonces;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.controlsfx.control.Rating;
import Annonces.services.AnnonceService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DetailsController implements Initializable {
@FXML
    private Rating rating ; 
    Annonces d = new Annonces();
    private Annonces don;
      AnnonceService ps = new AnnonceService();
    @FXML
    private Button submit;
    @FXML
    private Button BtFront;
    @FXML
    private Label DescriptionLabel;
    @FXML
    private Label etoile1;
    @FXML
    private Label etoile2;
    @FXML
    private Label etoile3;
    @FXML
    private Label etoile4;
    @FXML
    private Label etoile5;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }   
    
    
     
     public void receiveObject(Annonces d) throws SQLException {
        this.d=d;
        don=d;
          if(ps.checkRated(projetpidd.ProjetPiDD.user.getId(),d.getId()))
        {
            submit.setVisible(false);
        }
        else
        {
            submit.setVisible(true);
        }
        DescriptionLabel.setText(d.getDescription());
        etoile1.setText(Integer.toString(ps.getEtoile(d.getId(),"etoile_1")));
         etoile2.setText(Integer.toString(ps.getEtoile(d.getId(),"etoile_2")));
          etoile3.setText(Integer.toString(ps.getEtoile(d.getId(),"etoile_3")));
           etoile4.setText(Integer.toString(ps.getEtoile(d.getId(),"etoile_4")));
            etoile5.setText(Integer.toString(ps.getEtoile(d.getId(),"etoile_5")));
    }
    
    
    
      @FXML
    private void submit(ActionEvent event) throws SQLException {
        d.setNombre_etoiles((int) rating.getRating());
        System.out.println("Rating given by user :" + rating.getRating());
        ps.inserRating(projetpidd.ProjetPiDD.user.getId(),d.getId());
        AnnonceService sv = new AnnonceService();
          System.out.println("ID ANNONCE"+d.getId());
       if((int)rating.getRating()== 1)
       {
           sv.rating("etoile_1", 1, d.getId());
       }
       if(rating.getRating()== 2.0)
       {
           sv.rating("etoile_2", 2, d.getId());
       }
       if(rating.getRating()== 3.0)
       {
           sv.rating("etoile_3", 3, d.getId());
       }
       if(rating.getRating()== 4.0)
       {
           sv.rating("etoile_4", 4, d.getId());
       }
       if(rating.getRating()== 5.0)
       {
           sv.rating("etoile_5", 5, d.getId());
       }
    }

    @FXML
    private void goToFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        
        Parent root = loader.load();
        Controller c = loader.getController();
        BtFront.getScene().setRoot(root);
    }
    
}
