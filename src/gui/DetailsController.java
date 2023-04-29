/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Annonces;
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
import services.AnnonceService;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
     
     public void receiveObject(Annonces d) {
        this.d=d;
        don=d;
    }
    
    
    
      @FXML
    private void submit(ActionEvent event) throws SQLException {
        d.setNombre_etoiles((int) rating.getRating());
        System.out.println("Rating given by user :" + rating.getRating());
        AnnonceService sv = new AnnonceService();
        sv.rating(d);
    }

    @FXML
    private void goToFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        
        Parent root = loader.load();
        Controller c = loader.getController();
        BtFront.getScene().setRoot(root);
    }
    
}
