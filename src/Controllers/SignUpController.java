/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Services.ServiceUser;
import integration.Integration;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField num_telephone;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label erEmail;
    @FXML
    private Label errPassword;
    @FXML
    private Label errNum;
    @FXML
    private ComboBox<String> ComboType;
    @FXML
    private ImageView pic;
    @FXML
    private Button imageB;
    @FXML
    private Button SignUp;
    @FXML
    private Label errImage;
     private boolean allerror ;
public String imagePath="";
private TextFormatter<String> nomFormatter;
     private TextFormatter<String> prenomFormatter;
     private TextFormatter<String> emailFormatter;
     private TextFormatter<String> numTelephoneFormatter;
 Image defaultImage ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         allerror=true ;
         
        ComboType.setValue("Donneur");
        ComboType.getItems().addAll("Donneur", "Receveur");
        
        
        
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
    
    
    passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!isValidPassword(newValue)) {
            passwordField.setStyle("-fx-text-inner-color: red;");
             
              errPassword.setText("Verifier votre mot de passe !");
            errPassword.setStyle("-fx-text-fill: red;");
            allerror=true; 
           // showAlert("Invalid Password", "Password must contain at least 8 characters, including at least one letter and one digit");
        } else {
            passwordField.setStyle("-fx-text-inner-color: black;");
            allerror=false ;
           
             
        }
    });

    // add a listener to the confirm password field to validate the password match
    confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!isValidPasswordMatch(newValue)) {
            confirmPasswordField.setStyle("-fx-text-inner-color: red;");
          allerror=true ;
           // showAlert("Password Mismatch", "The passwords entered do not match");
        } else {
            confirmPasswordField.setStyle("-fx-text-inner-color: black;");
            passwordField.setStyle("-fx-text-inner-color: black;");
              errPassword.setText("");
              allerror=false ;
            
            
           
        }
    });
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
         try{
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
               errImage.setText("");
            System.out.println("Image URL: " + imagePath);
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        }catch(Exception e)
        {
         
           pic.setImage(defaultImage);
             errImage.setText("veuillez Saisir une photo de profil");
             imagePath="";
             errImage.setStyle("-fx-text-fill: red;");
           
        }
    }

    @FXML
    private void addUser(ActionEvent event) throws IOException, SQLException {
           if(allerror || imagePath=="")
        {
            showAlert("Verifier vos cordonnés !","il y a des champs invalides");
        }
        else if(ServiceUser.getInstance().searchUserByEmail(email.getText()))
       {
           showAlert("l utilisateur existe deja !","l utilisateur existe");
       }
        else{
                    User user = new User(email.getText(), passwordField.getText(), num_telephone.getText(), ComboType.getValue(), nom.getText(), prenom.getText(),imagePath);
                    ServiceUser.getInstance().addUser(user);
                    Integration m = new Integration ();
                    
                    m.changeScene("/Views/AccountCreated.fxml");
                            // initialize the password and confirm password fields
            

            
        }   
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Integration m = new Integration ();
        m.changeScene("/Views/login.fxml");
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
    if (!confirmPassword.equals(passwordField.getText())) {
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
