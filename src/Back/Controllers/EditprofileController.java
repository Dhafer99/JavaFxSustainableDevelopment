/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class EditprofileController implements Initializable {

    @FXML
    private ImageView pic;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField num_telephone;
    @FXML
    private ComboBox<?> comboType;
    @FXML
    private Label erEmail;
    @FXML
    private Label errNum;
    @FXML
    private Label errImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editUser(ActionEvent event) {
    }

    @FXML
    private void addImage(ActionEvent event) {
    }
    
}
