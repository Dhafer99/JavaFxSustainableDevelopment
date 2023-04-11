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
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
    private TextField type;
    @FXML
    private TextField num_telephone;
@FXML
    private ImageView pic;
    @FXML
    private ComboBox<String> comboType;
     private TextFormatter<String> nomFormatter;
     private TextFormatter<String> prenomFormatter;
     private TextFormatter<String> emailFormatter;
     private TextFormatter<String> numTelephoneFormatter;
     
    private boolean allerror=true ;
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
         User user = projetpidd.ProjetPiDD.user;
          final String imageURI4 = new File(user.getImage()).toURI().toString();
       pic.setImage(new Image(imageURI4));
   
        nom.setText(user.getNom());
        email.setText(user.getEmail());
        prenom.setText(user.getPrenom());
        comboType.setValue(user.getType());
        num_telephone.setText(user.getNumTelephone());
        
        
        ////////////
        allerror=false ;
         
       
        comboType.getItems().addAll("Donneur", "Receveur");
        
        
        
          Image defaultImage = pic.getImage();
        
        
        
        nomFormatter = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (!newText.matches("[A-Za-z]*")) {
            return null;
        }
        return change;
    });
    nom.setTextFormatter(nomFormatter);
    // set up the prenom text formatter
    prenomFormatter = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (!newText.matches("[A-Za-z]*")) {
            return null;
        }
        return change;
    });
    prenom.setTextFormatter(prenomFormatter);
    // set up the num_telephone text formatter
    // define the TextFormatter to allow only numeric input
UnaryOperator<TextFormatter.Change> filter = change -> {
    String text = change.getText();
    if (text.matches("\\d*")) {
        return change;
    }
    return null;
};
TextFormatter<String> numTelephoneFormatter = new TextFormatter<>(filter);
num_telephone.setTextFormatter(numTelephoneFormatter);
    

email.textProperty().addListener((observable, oldValue, newValue) -> {
        // check if the new value is a valid email address
        boolean isValid = isValidEmail(newValue);

        // set the text color based on the validity
        if (isValid) {
            email.setStyle("-fx-text-fill: black;");
            erEmail.setText("");
            allerror=false ;
        } else {
            email.setStyle("-fx-text-fill: red;");
            erEmail.setText("Verifier votre email !");
            erEmail.setStyle("-fx-text-fill: red;");
            allerror=true ;
        }
             try {
                 if(ServiceUser.getInstance().searchUserByEmail(email.getText()))
                 {
                     erEmail.setText("Cette Utilistauer existe deja");
                 }} catch (SQLException ex) {
                 Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
             }
    });
     num_telephone.textProperty().addListener((observable, oldValue, newValue) -> {
        // check if the new value is a valid email address
        boolean isValid = isValidPhoneNumber(newValue);

        // set the text color based on the validity
        if (isValid) {
            num_telephone.setStyle("-fx-text-fill: black;");
            errNum.setText("");
            allerror=false ;
        } else {
            num_telephone.setStyle("-fx-text-fill: red;");
            errNum.setText("Verifier votre Numero !");
            errNum.setStyle("-fx-text-fill: red;");
            allerror=true ;
        }
    });
     
     
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
        catch(Exception e)
        {
         
           //pic.setImage(defaultImage);
             errImage.setText("veuillez Saisir une photo de profil");
             imagePath="";
             errImage.setStyle("-fx-text-fill: red;");
           
        }
    }
    @FXML
     private void editUser(ActionEvent event) throws IOException, SQLException {
       
           if(imagePath.equals(""))
           {
               imagePath=ProjetPiDD.user.getImage();
           }
           if(allerror )
        {
            showAlert("Verifier vos cordonnés !","il y a des champs invalides");
        }
        
        else{
           
            User userx = new User(email.getText(),projetpidd.ProjetPiDD.user.getPassword(), num_telephone.getText(),comboType.getValue(), nom.getText(), prenom.getText(),imagePath);
           System.out.println("***************"+userx);
            
               
                ServiceUser.getInstance().editUser(userx);
                
                ProjetPiDD m = new ProjetPiDD();
                if(ProjetPiDD.user.getRoles().equals("ROLE_ADMIN"))
                {
                    userx.setRoles("ROLE_ADMIN");
                    ProjetPiDD.user=userx ;
                    
                m.changeScene("LoggedIn.fxml");
                }
                else
                {
                    
                     m.changeScene("login.fxml");
                }
        }
                //  FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
            

            
            
       
                
    }


private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
private boolean isValidEmail(String email) {
    // regular expression for email validation
    String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    // compile the regular expression into a pattern
    Pattern pattern = Pattern.compile(emailRegex);
    // check if the email matches the pattern
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
// define the isValidPhoneNumber method to check if the number has exactly 8 digits
private boolean isValidPhoneNumber(String phoneNumber) {
    // remove any non-digit characters from the phone number
    phoneNumber = phoneNumber.replaceAll("\\D", "");
    // check if the phone number is exactly 8 digits long
    return phoneNumber.matches("^\\d{8}$");
}
    
}
