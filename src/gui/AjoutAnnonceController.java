
package gui;

import entities.Annonces;
import services.AnnonceService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class AjoutAnnonceController implements Initializable {

    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_adresse;
    @FXML
    private DatePicker picker_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter(MouseEvent event) {
        java.sql.Date date_publication = java.sql.Date.valueOf(picker_date.getValue());
        String description = tf_description.getText();
        String adresse= tf_adresse.getText();
        
        String image = "hello";

      
        Annonces p = new Annonces(description, image, date_publication, adresse);
        AnnonceService promotiondao = new AnnonceService();
         promotiondao.insert(p);
    }

}
