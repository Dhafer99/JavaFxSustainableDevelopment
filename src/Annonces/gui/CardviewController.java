package Annonces.gui;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.FacebookType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import Annonces.entities.Annonces;
import java.io.IOException;

import java.net.MalformedURLException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import org.controlsfx.control.Rating;
import Annonces.services.AnnonceService;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;


public class CardviewController implements Initializable {

    @FXML
    private Label description;
    @FXML
    private Label adresse;
    @FXML
    private Label date_publication;
    private ImageView img;
    @FXML
    private ImageView image;
    private Annonces annonce ; 
    
    AnnonceService AS = new AnnonceService();
    @FXML
    private Button supprimer;
    Annonces d = new Annonces();
    @FXML
    private Button ToUpdate;
    
    @FXML
    private Rating rating ; 
    @FXML
    private Button submit;
    
    @FXML 
    private Button post ; 
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setData(Annonces annonce){
     description.setText(annonce.getDescription());
        adresse.setText(annonce.getAdresse());
        date_publication.setText(annonce.getDate_publication());
        //System.out.println(annonce.getImage());
                image.setImage(new Image(annonce.getImage()));
                
               System.out.println("file:src/uploads/"+annonce.getImage()+".png");
               

    }

    
    
    public void receiveObject(Annonces d) {
        this.d=d;
        annonce=d;
    }
    
   
    

    @FXML
    private void supp(ActionEvent event) throws IOException {
        System.out.println(annonce.getId());
              AS.delete(annonce);
              
    FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        SampleController aec = loader.getController();
        Parent root = loader.load();
        supprimer.getScene().setRoot(root);
    }

    @FXML
    private void ToUpdate(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("updateAnnonce.fxml"));
 Parent root = loader.load();
        ToUpdate.getScene().setRoot(root);
    updateAnnonceController controller = loader.getController();
    controller.receiveObject(d);
    }

    @FXML
    private void submit(ActionEvent event) throws SQLException {
      //  d.setNombre_etoiles((int) rating.getRating());
     //   System.out.println("Rating given by user :" + rating.getRating());
     //   AnnonceService sv = new AnnonceService();
     //   sv.rating(d);
        
        
        
    }

    @FXML
    private void post(ActionEvent event) throws MalformedURLException, IOException {
        Version apiVersion = Version.VERSION_16_0;
        
String accessToken = "EAADiVf1eruMBAJRv3sYkFJqiGjv7udWV5xsihToAMc12vICTz2pdPXW3yDhku1036bSQBOv1T6QnZB9iw4ZAeX9mgf9jfcVO0IFPl1n7usBpwrccwEdNgsAOZBw5ZBzye8dM511JoZC3fwl2zA4HgH7aJ4axZAMLZCcph2QDtb8PkSo5rElImqM4f5KpiK8TI54vtI3vpwIlgCiywN8YWeM";
 FacebookClient fbclient = new DefaultFacebookClient(accessToken,apiVersion);
FacebookType response = fbclient.publish("me/feed",FacebookType.class,Parameter.with("message","text"));
        System.out.println("fb.com/"+response.getId());
    }   
}