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
import javafx.scene.image.Image;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setData(Don don){
     DonName.setText(don.getNameD());
        Quantite.setText(Integer.toString(don.getQuantite()));
        Email.setText(don.getEmail());
        Numero.setText(Integer.toString(don.getNumero()));
   img.setImage(new Image("file:src\\uploads\\"+don.getImage()+".png"));
    }
    
}
