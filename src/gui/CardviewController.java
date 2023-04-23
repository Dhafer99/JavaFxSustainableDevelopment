package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entities.Annonces;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import services.AnnonceService;


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
              
    FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
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
        d.setNombre_etoiles((int) rating.getRating());
        System.out.println("Rating given by user :" + rating.getRating());
        AnnonceService sv = new AnnonceService();
        sv.rating(d);
        
        
        
    }
    
}