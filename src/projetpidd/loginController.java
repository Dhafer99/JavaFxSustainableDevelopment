/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Model.User;
import Services.ServiceUser;
import com.sun.javaws.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Souid
 */
public class loginController {
    
    
    public loginController(){
        
    }
     @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorLabel;
    
    @FXML 
    private Button login_button ;
    
   
    
    public void userLogin(ActionEvent event) throws IOException, SQLException{
        try {
        checkLogin();
        }catch(Exception e )
        {
            errorLabel.setText("Verifier vos cordonnés !");
        }
    }
     public void userSignUp(ActionEvent event) throws IOException{
        checkSignUp();
    }
     
    private void checkLogin() throws IOException, SQLException{
        ProjetPiDD m = new ProjetPiDD();
       
        User user = ServiceUser.getInstance().searchUserByEmail(usernameField.getText(),passwordField.getText());
        System.out.println("+++++++++++++++++"+user.getBlocked());
        
        if(user != null){
//            errorLabel.setText("success");
             projetpidd.ProjetPiDD.user=user ;
            m.changeScene("LoggedIn.fxml");
            
            
            
        }
        
        
        if(user.getRoles().equals("ROLE_USER"))
        {
            m.changeScene("UserProfile.fxml");
        }
        if(user.getRoles().equals("ROLE_ADMIN"))
        {
            m.changeScene("LoggedIn.fxml");
        }
        if(user.getBlocked() == true)
        {
            m.changeScene("UserBlocked.fxml");
        }
        
    }
    private void checkSignUp() throws IOException{
        
            ProjetPiDD m = new ProjetPiDD();
             m.changeScene("SignUp.fxml");
           
    }
    
    

}

