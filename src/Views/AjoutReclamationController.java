/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Reclamation;
import ServicesReclamations.ReclamationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private TextField tf_etat;
    @FXML
    private TextField tf_motifdereclamation;
    @FXML
    private TextField tf_numtelephone;
    @FXML
    private TextField tf_email;
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
        java.sql.Date data_reclamation = java.sql.Date.valueOf(picker_date.getValue());
        String etat = tf_etat.getText();
        String motif_de_reclamation = tf_motifdereclamation.getText();
        String num_telephone = tf_numtelephone.getText();
        String email = tf_email.getText();
        String image = "hello";

        // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
         Reclamation p = new Reclamation(data_reclamation, etat, motif_de_reclamation,image ,Integer.parseInt(num_telephone), email);
        ReclamationService promotiondao = new ReclamationService();
         promotiondao.insert(p);
    }

}
