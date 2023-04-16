package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entities.Annonces;
import javafx.scene.image.Image;

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
    
}