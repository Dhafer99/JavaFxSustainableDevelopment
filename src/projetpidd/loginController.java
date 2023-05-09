/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Annonces.MyBD.DataBase;
import Annonces.entities.Categorie;
import Annonces.services.CategorieService;
import Model.User;
import Services.ServiceUser;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
//import com.sun.javaws.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Souid
 */
public class loginController implements Initializable {

    @FXML
    private Label welcomelabel;
    @FXML
    private Button signup;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane AnchorRoot;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView test;
    
   
    
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
    
   
    
    @FXML
    public void userLogin(ActionEvent event) throws IOException, SQLException{
        try {
        checkLogin();
        }catch(Exception e )
        {
            errorLabel.setText("Verifier vos cordonnés!");
          //  e.printStackTrace();
        }
    }
    @FXML
     public void userSignUp(ActionEvent event) throws IOException{
        checkSignUp();
    }
     
    private void checkLogin() throws IOException, SQLException, AWTException{
        ProjetPiDD m = new ProjetPiDD();
       
        User user = ServiceUser.getInstance().searchUserByEmail(usernameField.getText(),passwordField.getText());
      
        
        if(user != null){
//            errorLabel.setText("success");
    System.out.println("+++++++++++++++++"+user.getRoles());
             projetpidd.ProjetPiDD.user=user ;
           m.changeScene("/Back/Views/UI.fxml");
            
            
            
        }
        
        
        if(user.getRoles().equals( "[\"ROLE_USER\"]"))
        {
            m.changeScene("/don/Front.fxml");
        }
        if(user.getRoles().equals( "[\"ROLE_ADMIN\"]"))
        {
           m.changeScene("/Back/Views/UI.fxml");
        }
        if(user.getBlocked() == true)
        {
             SystemTray tray = SystemTray.getSystemTray();

        // Create a tray icon
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("img/icons8_edit_account_50px.png"));
        trayIcon.setToolTip("Notification ");

        // Add a listener for when the user clicks on the tray icon
        

        // Add the tray icon to the system tray
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added");
            return;
        }

        // Display a notification
        trayIcon.displayMessage("Notification", "Votre Compte a été Blocké ! ", TrayIcon.MessageType.INFO);
        }
        
    }
    private void checkSignUp() throws IOException{
        
            
           Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = signup.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(AnchorRoot);
        });
        timeline.play();
        //ProjetPiDD m = new ProjetPiDD();
         //    m.changeScene("SignUp.fxml");
           
    }

    @FXML
    private void ForgotPasswordRedirect(MouseEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
             m.changeScene("mailing.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
      //  welcomelabel.getStyleClass().add("label-primary");
      //  root.getChildren().add(welcomelabel);
      welcomelabel.setStyle("--fx-font-style : Arial");
     
      vbox.setVgrow(test, Priority.ALWAYS);

    
    }

    private void chat(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("/views/LoginView.fxml");
    }
    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    

}

