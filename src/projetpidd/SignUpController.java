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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class SignUpController implements Initializable {
 public String imagePath="";
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField mot_de_passe;
    @FXML
    private TextField confirmerMotDePasse;
    @FXML
    private TextField type;
    @FXML
    private TextField num_telephone;
    @FXML
    private ImageView pic;
     @FXML
    private Button imageB;
    /**
     * Initializes the controller class.
     */
     @FXML 
     private Button SignUp ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void addImage(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//            imageIn.setFill(new ImagePattern(image));
            
             pic.setImage(image);
              imagePath = selectedFile.toURI().toURL().toString();
            System.out.println("Image URL: " + imagePath);
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void addUser(ActionEvent event) throws IOException, SQLException {
        
        
                    User user = new User(email.getText(), mot_de_passe.getText(), num_telephone.getText(), type.getText(), nom.getText(), prenom.getText(),imagePath);
                    

                    
                    ServiceUser.getInstance().addUser(user);
                    

                   

                 }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("login.fxml");
    }
}
       
      
  

