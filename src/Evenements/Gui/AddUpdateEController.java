/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenements.Gui;
import Evenements.Entities.CategorieEvent;
import Evenements.Entities.Evenement;
import Evenements.Service.EvenementService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
//import static don.FXMLController.ACCOUNT_SID;
//import static don.FXMLController.AUTH_TOKEN;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import Evenements.Service.CategorieEventService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import Evenements.utils.MyDB;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import projetpidd.ProjetPiDD;
/**
 * FXML Controller class
 *
 * @author 21629
 */

public class AddUpdateEController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private DatePicker tfdateDeb;
    @FXML
    private DatePicker tfdateFin;
    @FXML
    private TextField tflocalisation;
   
   
    @FXML
    private Button ImageAddChoose;
EvenementService ps = new EvenementService();
private Evenement d;
private int id ;
private int newEventId;
private File file; 
    private String lien="";
        public static final String ACCOUNT_SID = "AC904d482ced22b4c1943dfa6f347bc92b";
  public static final String AUTH_TOKEN = "cdd12b5234a3b840df4bdac908331f31";
    @FXML
    private ImageView eventAddImg;
    @FXML
    private Button Insert;
    @FXML
    private Button updateB;
    @FXML
    private ComboBox<CategorieEvent> combo;
    @FXML
    private VBox vbox;
        private String[] badWords = {"2g1c",
 "2 girls 1 cup",
 "acrotomophilia",
 "ahole",
 "anal",
 "anilingus",
 "anus",

 "bdsm",
 "beaver cleaver",
 "beaver lips",
 "bestiality",
 "bi curious",
 "big black",
 "big breasts",
 "big knockers",
 "big tits",
 "bimbos",
 "birdlock",
 "bitch",
 "black cock",
 "blonde action",
 "blonde on blonde action",
 "blow j",
 "blow your l",
 "blue waffle",
 "blumpkin",
 "bollocks",
 "bondage",
 "boner",
 "boob",
 "boobs",
 "booty call",
 "brown showers",
 "brunette action",
 "bukkake",
 "bulldyke",
 "bullet vibe",
 "bung hole",
 "bunghole",
 "busty",
 "butt",
 "buttcheeks",
 "butthole",
 "bassterds",
 "bastard",
 "bastards",
 "bastardz",
 "basterds",
 "basterdz",
 "Biatch",

 "zabourah",
 "zebi",
 "nami",
 "asba",
 "ynain",
 "yna3in",
 "zok",
 "3os",
 "3asba"
		}; // Add your list of bad words
private Stage primaryStage; 
    @FXML
    private TextField text;
    @FXML
    private Button updateB1;
    /**
     * Initializes the controller class.
     */
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  public String filterBadWords(String text) {
        for (String badWord : badWords) {
            if (text.toLowerCase().contains(badWord)) {
                String stars = "";
                for (int i = 0; i < badWord.length(); i++) {
                    stars += "*";
                }
                text = text.replaceAll(badWord, stars);
            }
        }
        return text;
    }
   public void addDynamicBadWordFilter(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String filteredText = filterBadWords(newValue);
            textField.setText(filteredText);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            CategorieEventService se = new CategorieEventService();
            List<CategorieEvent> personnes = se.afficher();
            combo.setItems(FXCollections.observableArrayList(personnes));
            combo.setConverter(new StringConverter<CategorieEvent>() {
                @Override
                public String toString(CategorieEvent categorie) {
                    return categorie.getNom();
                }
                @Override
                public CategorieEvent fromString(String nom) {
                    return null; // Ne pas utiliser pour la conversion vers une chaîne de caractères
                }
            });     } catch (SQLException ex) {
            Logger.getLogger(AddUpdateEController.class.getName()).log(Level.SEVERE, null, ex);
        }
       // addDynamicBadWordFilter(tfname);
        //addDynamicBadWordFilter(tflocalisation);
                


        /*Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }  
    @FXML
    
    private void UploadImageActionPerformed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            eventAddImg.setImage(imagee);
             PauseTransition pause = new PauseTransition(Duration.seconds(8));
        
        // set the action to be performed when the pause is finished
        pause.setOnFinished(Event -> {
            // your code to be executed after 5 seconds
eventAddImg.setImage(null);
        System.out.println("8 seconds have passed");
        });
        
        // start the pause
        pause.play();
        
        // your code to be executed immediately
        System.out.println("Waiting for 8 seconds...");
    
            eventAddImg.setFitWidth(200);
            eventAddImg.setFitHeight(200);
            eventAddImg.scaleXProperty();
            eventAddImg.scaleYProperty();
            eventAddImg.setSmooth(true);
            eventAddImg.setCache(true);                           

        try {
            // save image to PNG file
            this.lien = UUID.randomUUID().toString() + ".png"; // or ".jpg" if you prefer
File f = new File("C:\\xampp\\htdocs\\public\\" + this.lien);
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
                       
        } catch (IOException ex) {
            Logger.getLogger(AddUpdateEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
    }
    @FXML
    private void AjouterDon(ActionEvent Don) throws IOException, SQLException {
                   Connection cnx = MyDB.getInstance().getCnx();

         

        
        
        int category =combo.getSelectionModel().getSelectedItem().getId();
            EvenementService se = new EvenementService();
            
            Evenements.Entities.Evenement p = new Evenements.Entities.Evenement();
          
     
                  p.setNom_event(tfname.getText());
            p.setLocalisation(tflocalisation.getText());
            p.setImage_event(lien);
         p.setDate_debut( java.sql.Date.valueOf(tfdateDeb.getValue()));
                 p.setDate_fin( java.sql.Date.valueOf(tfdateFin.getValue()));
                 p.setCategoryId(category);
                 p.setId(id);
                 
                 
                 
                 
                   se.ajouter(p);
                  String query = "SELECT LAST_INSERT_ID()";
PreparedStatement statement = cnx.prepareStatement(query);
ResultSet resultSet = statement.executeQuery();
if (resultSet.next()) {
     newEventId = resultSet.getInt(1);
    System.out.println("The ID of the newly added event is: " + newEventId);
} else {
    System.out.println("Failed to retrieve the ID of the newly added event.");
}
        System.out.println(newEventId);
                   String query1 = "INSERT INTO user_evenement (user_id, evenement_id) VALUES (?, ?)";
PreparedStatement statement1 = cnx.prepareStatement(query1);
statement1.setInt(1, projetpidd.ProjetPiDD.user.getId());
statement1.setInt(2 , newEventId);

int rowsInserted = statement1.executeUpdate(); 

                   JOptionPane.showMessageDialog(null, "Event ajouté !");
                               ProjetPiDD m = new ProjetPiDD();
        m.changeScene("/don/Front.fxml");
      /*   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21629228940"),
        new PhoneNumber("+15673717088"), 
        "a New Don has been added").create();
     */
    }
public void receiveObject(Evenement d) {
        this.d=d;
        id=d.getId();
        tfname.setText(d.getNom_event()+"");
         Date date = d.getDate_debut();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // replace with your date format
String dateString = formatter.format(date);
Instant instant = Instant.parse(dateString + "T00:00:00Z");
LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
tfdateDeb.setValue(localDate);
 Date dateF = d.getDate_fin();
SimpleDateFormat formatterF = new SimpleDateFormat("yyyy-MM-dd"); // replace with your date format
String dateFString = formatter.format(dateF);
Instant instantF = Instant.parse(dateFString + "T00:00:00Z");
LocalDate localDateF = instantF.atZone(ZoneId.systemDefault()).toLocalDate();
tfdateFin.setValue(localDateF);

      //  datedeb.setText(String.valueOf(d.getDate_debut()));
       // dateF.setText(String.valueOf(d.getDate_fin()));
        tflocalisation.setText(d.getLocalisation());
       
   eventAddImg.setImage(new Image("file:http://localhost/public/"+d.getImage_event()));
   
 
    }
    @FXML
    private void ModifierEvent(ActionEvent event) {
     
             int category =combo.getSelectionModel().getSelectedItem().getId();
            EvenementService se = new EvenementService();
            Evenement p = new Evenement();
           p.setNom_event(tfname.getText());
            p.setLocalisation(tflocalisation.getText());
            p.setImage_event(lien);
         p.setDate_debut( java.sql.Date.valueOf(tfdateDeb.getValue()));
                 p.setDate_fin( java.sql.Date.valueOf(tfdateFin.getValue()));
                 p.setCategoryId(category);
                 p.setId(id);
            
//            
            se.modifier(p);

            JOptionPane.showMessageDialog(null, "Event modifié !");
                  
       
    }
 @FXML
    private void Retour(MouseEvent event) throws IOException {
       ProjetPiDD m = new ProjetPiDD();
        m.changeScene("/don/Front.fxml"); 
        
    }
}
