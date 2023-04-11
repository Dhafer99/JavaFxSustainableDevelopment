/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class UserProfileController implements Initializable {

    @FXML
    private Label noml;
    @FXML
    private Label prenoml;
    @FXML
    private Label emaill;
    @FXML
    private Label scorel;
    @FXML
    private Label etoilel;
    @FXML
    private Label typel;
    @FXML
    private ImageView imageuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = ProjetPiDD.user ;
                
        noml.setText(user.getNom());
        prenoml.setText(user.getPrenom());
        emaill.setText(user.getEmail());
        scorel.setText(Integer.toString(user.getScore()));
        etoilel.setText(Integer.toString(user.getNb_etoile()));
        typel.setText(user.getType());
        Image image = new Image(user.getImage());
        imageuser.setImage(image);
        
        
        
                }    

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("login.fxml");
    }

    @FXML
    private void toUpdate(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("editprofile.fxml");
    }
    
}
