/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Model.User;
import Services.ServiceUser;
import projetpidd.ProjetPiDD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class loginController implements Initializable {

    @FXML
    private Button login_button;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void userLogin(ActionEvent event) {
         try {
        checkLogin();
        }catch(Exception e )
        {
            errorLabel.setText("Verifier vos cordonnés !");
        }
    }

    @FXML
    private void userSignUp(ActionEvent event) throws IOException {
        checkSignUp();
    }
    private void checkLogin() throws IOException, SQLException{
        ProjetPiDD m = new ProjetPiDD ();
       
        User user = ServiceUser.getInstance().searchUserByEmail(usernameField.getText(),passwordField.getText());
        System.out.println("+++++++++++++++++"+user.getBlocked());
        
        if(user != null){
//            errorLabel.setText("success");
             projetpidd.ProjetPiDD.user=user ;
          //  m.changeScene("LoggedIn.fxml");
            
            m.changeScene("/Views/UI.fxml");
            
        }
        
        
        if(user.getRoles().equals("ROLE_USER"))
        {
            m.changeScene("/Views/UserProfile.fxml");
        }
        if(user.getRoles().equals("ROLE_ADMIN"))
        {
            m.changeScene("/Views/UI.fxml");
        }
        if(user.getBlocked() == true)
        {
            m.changeScene("/Views/UserBlocked.fxml");
        }
        
    }
    private void checkSignUp() throws IOException{
        
           ProjetPiDD m = new ProjetPiDD ();
             m.changeScene("/Views/SignUp.fxml");
           
    }
}
