/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import Models.Association;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class CardViewController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label adresse;
    @FXML
    private Label numero;
    @FXML
    private Label mail;
    @FXML
    private Label codepostal;
    @FXML
    private Label ville;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Association association){
     nom.setText(association.getNom());
        numero.setText(Integer.toString(association.getNumero()));
        mail.setText(association.getMail());
        codepostal.setText(Integer.toString(association.getCodePostal()));
        ville.setText(association.getVille());
        adresse.setText(association.getAdresse());
        
        
    }

}
