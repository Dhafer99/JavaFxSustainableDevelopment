/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Categorie_Rec;
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
import Back.Controllers.CategorieReclamationListdata;
import Back.Models.Reclamation;
import Back.Services.CategorieReclamationsService;
import Back.Services.ReclamationService;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class AjoutReclamationController implements Initializable {
private File file;
    private String lien = "";
    private TextField tf_etat;
    @FXML
    private TextField tf_motifdereclamation;
    @FXML
    private TextField tf_numtelephone;
    @FXML
    private TextField tf_email;
    @FXML
    private ChoiceBox<String> combo;
private CategorieReclamationListdata CategorieReclamationListdata = new CategorieReclamationListdata();
    @FXML
    private ImageView image;
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
      if(ValidateEmptyForm(tf_motifdereclamation,tf_numtelephone,tf_email)){

        Categorie_Rec Catrec; // instance
         String nom =  (String)combo.getValue(); // te5ou ell valeur mill combo box 
        CategorieReclamationsService cdao = new CategorieReclamationsService(); // instance service categorie 
        Catrec = cdao.getOneByName(nom ); 
        LocalDate currentdate = LocalDate.now();
        java.sql.Date data_reclamation = java.sql.Date.valueOf(currentdate);
        System.out.println(currentdate);
        // java.sql.Date data_reclamation = java.sql.Date.valueOf(picker_date.getValue());
        String etat = "En Cours";
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
        promotiondao.insert(p);
         JOptionPane.showMessageDialog(null, "Reclamation ajoutée !");
         if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        // Get the system tray instance
        SystemTray tray = SystemTray.getSystemTray();

        // Create a tray icon
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ghofr\\Desktop\\Java\\GestionReclamation\\src\\image\\icon.png"));
        trayIcon.setToolTip("Notification ");

        // Add a listener for when the user clicks on the tray icon
        

        // Add the tray icon to the system tray
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added");
            return;
        }

        // Display a notification
        trayIcon.displayMessage("Notification", "Reclamation ajoutée dans la categorie "+p.getCategorie_rec().getNom(), TrayIcon.MessageType.INFO);
    }
               //       FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
      //  SampleController aec = loader.getController();
       // Parent root = loader.load();
        //Insert.getScene().setRoot(root);
         }
    
    }
    private boolean ValidateEmptyForm( TextField motif_de_reclamation, TextField email,TextField num_telephone){
         if ( motif_de_reclamation.getText().equals("") || 
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
            File f=new File("http://localhost/public/" + this.lien );
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
}