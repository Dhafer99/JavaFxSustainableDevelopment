/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Categorie_Rec;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Controllers.CategorieReclamationListdata;
import Models.Reclamation;
import Services.CategorieReclamationsService;
import Services.ReclamationService;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
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
    @FXML
    private ChoiceBox<String> combo;
private CategorieReclamationListdata CategorieReclamationListdata = new CategorieReclamationListdata();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tf_numtelephone.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        if (!newValue.matches("\\d*")) {
            tf_numtelephone.setText(newValue.replaceAll("[^\\d]", ""));
        }
        
    }
    );
        
        
      //  List<Categorie_Rec> categoriePromotions = CategorieReclamationListdata.getPersons();
      List<Categorie_Rec> categoriePromotions = CategorieReclamationListdata.getPersons();
    ObservableList<String> observableList = FXCollections.observableArrayList();
    for (Categorie_Rec categoriePromotion : categoriePromotions

    
        ) {
            observableList.add(categoriePromotion.toString());
    }

    combo.setItems (observableList);
    }    

    @FXML
    private void ajouter(MouseEvent event) {
         if(ValidateEmptyForm(tf_etat,tf_motifdereclamation,tf_numtelephone,tf_email,picker_date)){

        Categorie_Rec Catrec; // instance
        String nom = (String) combo.getValue(); // te5ou ell valeur mill combo box 
        CategorieReclamationsService cdao = new CategorieReclamationsService(); // instance service categorie 
        Catrec = cdao.getOneByName(nom); //
        java.sql.Date data_reclamation = java.sql.Date.valueOf(picker_date.getValue());
        String etat = tf_etat.getText();
        String motif_de_reclamation = tf_motifdereclamation.getText();
        String num_telephone = tf_numtelephone.getText();
        String email = tf_email.getText();
        String image = "hello";
         if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
       JOptionPane.showMessageDialog(null, "Symbole or caracter is missing  !");
         } if (!num_telephone.matches("\\d{8}")) {
   JOptionPane.showMessageDialog(null, "Must contain 8 numbers !");
         }else {
        // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
        Reclamation p = new Reclamation(data_reclamation, etat, motif_de_reclamation, image, Integer.parseInt(num_telephone), email,Catrec);
        ReclamationService promotiondao = new ReclamationService();
        promotiondao.insert(p);}
    

    }
    }
    private boolean ValidateEmptyForm(TextField etat , TextField motif_de_reclamation, TextField email,TextField num_telephone, DatePicker data_reclamation){
         if (etat.getText().equals("")  || motif_de_reclamation.getText().equals("") || 
                 email.getText().equals("") || num_telephone.getText().equals("")||data_reclamation.getValue()==null  ) {
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
}