/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;
import Models.Association;
import Models.categorieA;
import ServiceAssociation.AssociationService;
import ServiceAssociation.categorieService;
import Views.categeListdata;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class AjoutAssociationController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfcode_postal;
    @FXML
    private TextField tfville;
private File file; 
    private String lien="";
     public String imagePath="";
    public static final String ACCOUNT_SID = "AC7baee01e459dc347a9e9f0a9b8f744c5";
  public static final String AUTH_TOKEN = "89da9ac629f6cf7374346a905c91de41";
  
  private categeListdata categeListdata = new categeListdata();
    @FXML
    private ChoiceBox<String> combo;
    @FXML
    private ImageView eventAddImg;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
       
        tfnumero.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfnumero.setText(newValue.replaceAll("[^\\d]", ""));            
            }
        });
        
         tfcode_postal.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfcode_postal.setText(newValue.replaceAll("[^\\d]", ""));            
            }
        });
         /*
         UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {

                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-7]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                
                if (t.isAdded()) {
                    if (t.getControlText().contains(".")) {
                        if (t.getText().matches("[^0-7]")) {
                            t.setText("");
                        }
                    } else if (t.getText().matches("[^0-7.]")) {
                        t.setText("");
                    }
                }
                
                return t;
            }
        };
      tfnumero.setTextFormatter(new TextFormatter<>(filter)); 
        */
        
           // liste ell categorie
        List<categorieA> categoriePromotions = categeListdata.getPersons();
ObservableList<String> observableList = FXCollections.observableArrayList();
for (categorieA categoriePromotion : categoriePromotions) {
    observableList.add(categoriePromotion.toString()); 
}
combo.setItems(observableList);
// end
    } 
    /*
    private void contole(Association t ){
              if (t.getNom().isEmpty() ||  t.getNumero().isEmpty() || t.getMail().isEmpty() || t.getAdresse().isEmpty() || t.getCodePostal() || t.getVille().isEmpty() ) {
 {
            System.err.println("Remplir tout les champs ");
            return;
       
        
    }
}*/    
    
      private boolean ValidateEmptyForm(TextField nom, TextField mail){
         if (nom.getText().equals("")  || mail.getText().equals("") )
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir tous les champs");
             alert.showAndWait();
             
             return false;  
        } else {
             return true;  
         }
     }
   
    @FXML
    private void Ajout(MouseEvent event) { 
      
   if(ValidateEmptyForm(tfnom,tfmail)){
        
        
categorieA asso; // instance
           String nom_categ = (String) combo.getValue(); // te5ou ell valeur mill combo box 
        categorieService cdao=new categorieService(); // instance service categorie 
        asso= cdao.getOneByName(nom_categ); // 
        
 
    String nom=tfnom.getText();
    String numero=tfnumero.getText();
    String mail=tfmail.getText();
    String adresse=tfadresse.getText();
    String code_postal=tfcode_postal.getText();
    String ville=tfville.getText();
    String image =lien;   
   // String image=pic.
    
    
    
   // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Association p=new Association(nom,Integer.parseInt(numero),mail,adresse,Integer.parseInt(code_postal),image,ville,asso);
    AssociationService promotiondao=new AssociationService();
    
   // raja3ha fill validation 
   
   /*
    JOptionPane.showMessageDialog(null, "ajouter un event   !");
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21698773438"),
        new PhoneNumber("+12764098996"), 
        "une nouvelle association est ajouter  ").create();
       */             
                    
    promotiondao.insert(p);   
    String message1 = "une nouvelle association est ajouter.";
       promotiondao.notifyUser(message1);

    }
  

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
            // PauseTransition pause = new PauseTransition(Duration.seconds(8));
        
        // set the action to be performed when the pause is finished
       /*     pause.setOnFinished(Event -> {
            // your code to be executed after 5 seconds
eventAddImg.setImage(null);
        System.out.println("8 seconds have passed");
        });
        */
        // start the pause
       // pause.play();
        
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
            this.lien=UUID.randomUUID().toString();
            File f=new File("src\\uploads\\" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
                       
        } catch (IOException ex) {
            Logger.getLogger(AjoutAssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        
        
    }
    
}
    }
