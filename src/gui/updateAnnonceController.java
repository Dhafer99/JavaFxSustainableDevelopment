/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Annonces;
import entities.Categorie;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.AnnonceService;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class updateAnnonceController implements Initializable {
    @FXML
    private ComboBox<Categorie> combo;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_adresse;
    @FXML
    private DatePicker picker_date;
    @FXML
    private ImageView eventAddImg;
    @FXML
    private Button frontBTN;
    
    AnnonceService ps = new AnnonceService();
private Annonces d;
private int id ;
private File file; 
    private String lien="";
    private Button updateB;
   String imagep ;
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        CategorieService se = new CategorieService();
        List<Categorie> personnes = se.displayAll();
        combo.setItems(FXCollections.observableArrayList(personnes));
        combo.setConverter(new StringConverter<Categorie>() {
            @Override
            public String toString(Categorie categorie) {
                return categorie.getNom();
            }
            @Override
            public Categorie fromString(String nom) {
                return null; // Ne pas utiliser pour la conversion vers une chaîne de caractères
            }
        });
      
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
             //PauseTransition pause = new PauseTransition(Duration.seconds(8));
        
        // set the action to be performed when the pause is finished
       // pause.setOnFinished(Event -> {
            // your code to be executed after 5 seconds
//eventAddImg.setImage(null);
     //   System.out.println("8 seconds have passed");
    //    });
        
        // start the pause
     //   pause.play();
        
        // your code to be executed immediately
        //System.out.println("Waiting for 8 seconds...");
    
            eventAddImg.setFitWidth(200);
            eventAddImg.setFitHeight(200);
            eventAddImg.scaleXProperty();
            eventAddImg.scaleYProperty();
            eventAddImg.setSmooth(true);
            eventAddImg.setCache(true);                           

        try {
            // save image to PNG file
            this.lien=UUID.randomUUID().toString();
            File f=new File(  this.lien );
            System.out.println(f.toURI().toString());
                 imagep = file.toURI().toURL().toString();
            ImageIO.write(image, "PNG",f);
                       
        } catch (IOException ex) {
            //Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
    }
     @FXML
    private void update(ActionEvent event) throws IOException {
          try {
              
              System.out.println("***********************");
            AnnonceService se = new AnnonceService();
            Annonces p = new Annonces();
            p.setDescription(tf_description.getText());
            p.setAdresse(tf_adresse.getText());
            
            java.sql.Date date_publication = java.sql.Date.valueOf(picker_date.getValue());
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date_publication);
            p.setDate_publication(formattedDate);
            p.setId(id);
            p.setCategorie(combo.getValue());
            p.setImage(imagep);
            
              
            se.update(p);

           // JOptionPane.showMessageDialog(null, "Annonce modifiée !");
          //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sample.fxml"));
           // SampleController aec = loader.getController();
              // updateB.getScene().setRoot(root);
              Stage primaryStage= new Stage () ;
             Parent parentPage = FXMLLoader.load(getClass().getResource("/gui/sample.fxml"));
        
            Scene scene = new Scene(parentPage);
        primaryStage.setScene(scene);
        primaryStage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(updateAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveObject(Annonces d) {
        this.d=d;
        id=d.getId();
        tf_description.setText(d.getDescription()+"");
        tf_adresse.setText(d.getAdresse());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(d.getDate_publication(), formatter);
        picker_date.setValue(date);
        eventAddImg.setImage(new Image(d.getImage()));
          combo.setValue(d.getCategorie());
           imagep = d.getImage();
        
  
    }
    
    

    

    @FXML
    private void front(ActionEvent event) {
    }

   
    
    
}
