package gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Annonces;
import entities.Categorie;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import services.AnnonceService;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import services.CategorieService;

public class AjoutAnnonceController implements Initializable {

    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_adresse;
    @FXML
    private DatePicker picker_date;
    @FXML
    private ImageView imageadd;
    @FXML
    private ComboBox<String> combo;
    private CategorieListData categeListdata = new CategorieListData();
    private File file;
    private String lien = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Categorie> categoriePromotions = categeListdata.getPersons();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Categorie categoriePromotion : categoriePromotions) {
            observableList.add(categoriePromotion.toString());
        }
        combo.setItems(observableList);
    }

    @FXML
    private void ajouter(MouseEvent event) {
        if (ValidateEmptyForm(tf_description, tf_adresse, picker_date)&&(ValidateDateDeb(picker_date))) {
            Categorie asso; // instance
            String nom = (String) combo.getValue(); // tekhou ell valeur mill combo box 
            CategorieService cdao = new CategorieService(); // instance service categorie 
            asso = cdao.getOneByName(nom); //
            java.sql.Date date_publication = java.sql.Date.valueOf(picker_date.getValue());
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date_publication);
            String description = tf_description.getText();
            String adresse = tf_adresse.getText();
            //String lien = imageadd.get;

            Annonces p = new Annonces(adresse, description, lien, formattedDate, asso);

            AnnonceService promotiondao = new AnnonceService();
            promotiondao.insert(p);
        }
        
    }

    private boolean ValidateEmptyForm(TextField description, TextField adresse, DatePicker d) {
        if (description.getText().equals("") || adresse.getText().equals("")
                || d.getValue() == null) {
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
    
    private boolean ValidateDateDeb(DatePicker d){
         if (d.getValue().isAfter(LocalDate.now())) {
            return true;
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(" Date début non valide");
             alert.showAndWait();
             
             return false;  
         }
     }
    

    @FXML

    private void UploadImageActionPerformed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (.JPG)", ".JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (.jpg)", ".jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (.PNG)", ".PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (.png)", ".png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            imageadd.setImage(imagee);
            imageadd.setFitWidth(200);
            imageadd.setFitHeight(200);
            imageadd.scaleXProperty();
            imageadd.scaleYProperty();
            imageadd.setSmooth(true);
            imageadd.setCache(true);

            try {
                // save image to PNG file
                this.lien = UUID.randomUUID().toString();
                File f = new File("src\\uploads\\" + this.lien + ".png");
                System.out.println(f.toURI().toString());
                ImageIO.write(image, "PNG", f);

            } catch (IOException ex) {
                Logger.getLogger(AjoutAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
    }

}
