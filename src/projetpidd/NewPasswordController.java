/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class NewPasswordController implements Initializable {

    
    @FXML
    private Label errPassword;
  
    private boolean allerror ;
    @FXML
    private PasswordField Mdp;
    @FXML
    private PasswordField CMdp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Mdp.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!isValidPassword(newValue)) {
            Mdp.setStyle("-fx-text-inner-color: red;");
             
              errPassword.setText("Verifier votre mot de passe !");
            errPassword.setStyle("-fx-text-fill: red;");
            allerror=true; 
           // showAlert("Invalid Password", "Password must contain at least 8 characters, including at least one letter and one digit");
        } else {
            Mdp.setStyle("-fx-text-inner-color: black;");
            allerror=false ;
           
             
        }
    });

    // add a listener to the confirm password field to validate the password match
    CMdp.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!isValidPasswordMatch(newValue)) {
            CMdp.setStyle("-fx-text-inner-color: red;");
          allerror=true ;
           // showAlert("Password Mismatch", "The passwords entered do not match");
        } else {
            CMdp.setStyle("-fx-text-inner-color: black;");
            Mdp.setStyle("-fx-text-inner-color: black;");
              errPassword.setText("");
              allerror=false ;
            
            
           
        }
    });
    }    

    @FXML
    private void Retour(ActionEvent event) {
    }
    private boolean isValidPassword(String password) {
    // validate the password format
    if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
        return false;
    }
    return true;
}

private boolean isValidPasswordMatch(String confirmPassword) {
    // check that the confirm password matches the password
    if (!confirmPassword.equals(Mdp.getText())) {
        return false;
    }
    return true;
}
private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void ValidatePassword(ActionEvent event) throws SQLException, IOException {
        if(allerror)
        {
            showAlert("Verifier votre mot de passe !","Vérifier Que Votre mot de passe soit similaire");
        }
        else
        {
                String hashedPassword = BCrypt.hashpw(Mdp.getText(), BCrypt.gensalt());
   
            ServiceUser.getInstance().changePassword(mailingController.emailsent,hashedPassword );
            ProjetPiDD m = new ProjetPiDD();
            m.changeScene("login.fxml");
        }
    }
    
}
