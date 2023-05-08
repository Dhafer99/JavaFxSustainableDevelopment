/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamations.Views;

import Reclamations.Entite.Categorie_Rec;
import Reclamations.Entite.Reclamation;
import Reclamations.ServicesReclamations.CategorieRecService;
import Reclamations.ServicesReclamations.ReclamationService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class AjoutReclamationController implements Initializable {

    private File file;
    private String lien = "";
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
    private CategorieListdata CategorieListdata = new CategorieListdata();
    @FXML
    private AnchorPane addimage;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tf_numtelephone.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        if (!newValue.matches("\\d*")) {
            tf_numtelephone.setText(newValue.replaceAll("[^\\d]", ""));
        }
        
    }
    );
        
        
        List<Categorie_Rec> categoriePromotions = CategorieListdata.getPersons();
    ObservableList<String> observableList = FXCollections.observableArrayList();
    for (Categorie_Rec categoriePromotion : categoriePromotions

    
        ) {
            observableList.add(categoriePromotion.toString());
    }

    combo.setItems (observableList);
    // TODO
}

@FXML
        private void ajouter(MouseEvent event) {
        
        if(ValidateEmptyForm(tf_etat,tf_motifdereclamation,tf_numtelephone,tf_email)){

        Categorie_Rec Catrec; // instance
        String nom = (String) combo.getValue(); // te5ou ell valeur mill combo box 
        CategorieRecService cdao = new CategorieRecService(); // instance service categorie 
        Catrec = cdao.getOneByName(nom); //
        LocalDate currentdate = LocalDate.now();
        java.sql.Date data_reclamation = java.sql.Date.valueOf(currentdate);
        System.out.println(currentdate);
        //java.sql.Date data_reclamation = java.sql.Date.valueOf(picker_date.getValue());
        String etat = tf_etat.getText();
        String motif_de_reclamation = tf_motifdereclamation.getText();
        String num_telephone = tf_numtelephone.getText();
        String email = tf_email.getText();
        String image = lien;
         if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
       JOptionPane.showMessageDialog(null, "Symbole or caracter is missing  !");
         } if (!num_telephone.matches("\\d{8}")) {
   JOptionPane.showMessageDialog(null, "Must contain 8 numbers !");
         }else {
        // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
        Reclamation p = new Reclamation(data_reclamation, etat, motif_de_reclamation, image, Integer.parseInt(num_telephone), email,Catrec);
        ReclamationService promotiondao = new ReclamationService();
        promotiondao.insert(p,projetpidd.ProjetPiDD.user.getId());}
        
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
            BufferedImage imager = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(imager, null);
            image.setImage(imagee);
            image.setFitWidth(200);
            image.setFitHeight(200);
            image.scaleXProperty();
            image.scaleYProperty();
            image.setSmooth(true);
            image.setCache(true);                           

        try {
            // save image to PNG file
            this.lien=UUID.randomUUID().toString();
            File f=new File("src\\uploads\\" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(imager, "PNG",f);
                       
        



} catch (IOException ex) {
            Logger.getLogger(AjoutReclamationController.class

.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
    }
    
    private boolean ValidateEmptyForm(TextField etat , TextField motif_de_reclamation, TextField email,TextField num_telephone){
         if (etat.getText().equals("")  || motif_de_reclamation.getText().equals("") || 
                 email.getText().equals("") || num_telephone.getText().equals("")  ) {
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
