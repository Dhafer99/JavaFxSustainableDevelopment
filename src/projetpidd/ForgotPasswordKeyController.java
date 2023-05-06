/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class ForgotPasswordKeyController implements Initializable {

    @FXML
    private TextField textField1;
    @FXML
    private Label validationMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleValidateButton(ActionEvent event) throws IOException {
        //System.out.println(projetpidd.mailingController.randomString);
        if(textField1.getText().equals(projetpidd.mailingController.randomString))
        {
            ProjetPiDD m = new ProjetPiDD();
            m.changeScene("NewPassword.fxml");
        }
        else
        {
            validationMessage.setText("Verifier votre clé !");
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("/projetpidd/mailing.fxml");
    }
    
}
