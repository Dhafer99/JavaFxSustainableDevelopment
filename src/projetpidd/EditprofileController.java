/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Model.User;
import Services.ServiceUser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class EditprofileController implements Initializable {
String imagePath="";
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField type;
    @FXML
    private TextField num_telephone;
@FXML
    private ImageView pic;
    @FXML
    private Button ReturnBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         User user = projetpidd.ProjetPiDD.user;
          final String imageURI4 = new File(user.getImage()).toURI().toString();
       pic.setImage(new Image(imageURI4));
   
        nom.setText(user.getNom());
        email.setText(user.getEmail());
        prenom.setText(user.getPrenom());
        type.setText(user.getType());
        num_telephone.setText(user.getNumTelephone());
    }    
     @FXML
    private void addImage(ActionEvent event) {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image2 = SwingFXUtils.toFXImage(bufferedImage, null);
            //imageIn.setFill(new ImagePattern(image2));
            imagePath = selectedFile.toURI().toURL().toString();
             pic.setImage(image2);
        } catch (IOException ex) {
            Logger.getLogger(EditprofileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
     private void editUser(ActionEvent event) throws IOException, SQLException {
       
           
           
          
           
            User userx = new User(email.getText(),projetpidd.ProjetPiDD.user.getPassword(), num_telephone.getText(),type.getText(), nom.getText(), prenom.getText(),imagePath);
           System.out.println("***************"+userx);
            
               
                ServiceUser.getInstance().editUser(userx);
                
                
                //  FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
            

            
            
       
                
    }
     @FXML
     private void Return (ActionEvent event) throws IOException{
      /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Operation.fxml"));
        EditprofileController aec = loader.getController();
        Parent root = loader.load();
        ReturnBtn.getScene().setRoot(root);*/
      ProjetPiDD n = new ProjetPiDD();
      n.changeScene("LoggedIn.fxml");
     }
    
}
