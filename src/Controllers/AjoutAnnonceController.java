/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Annonces;
import Models.Categorie;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
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
    private File file;
    private String lien = "";
 //private CategorieListData categeListdata = new CategorieListData();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void ajouter(MouseEvent event) {
        
        
        
    }
    
}
