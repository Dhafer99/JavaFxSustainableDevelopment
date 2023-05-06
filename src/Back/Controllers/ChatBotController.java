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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class ChatBotController implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtInput;
    @FXML
    private Button btnSend;
    @FXML
    private Button Retour;
    @FXML
    private Label lblOutput;

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
    
}
