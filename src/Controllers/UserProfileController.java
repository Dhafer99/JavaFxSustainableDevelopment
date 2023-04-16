/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class UserProfileController implements Initializable {

    @FXML
    private ImageView imageuser;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Retour(ActionEvent event) {
    }

    @FXML
    private void toUpdate(ActionEvent event) {
    }
    
}
