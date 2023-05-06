/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenements.Gui;

import Evenements.Entities.CategorieEvent;
import Evenements.Entities.Evenement;
import Evenements.Service.CategorieEventService;
import Evenements.Service.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class AddCategorieEventController implements Initializable {

    @FXML
    private TextField tfCategEAddNom;
    @FXML
    private Button CategEAddButton;
    @FXML
    private Button showCategEBT;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutCategE(ActionEvent event) throws IOException {
        
         CategorieEventService se = new CategorieEventService();
                       CategorieEvent p = new CategorieEvent();

        p.setNom(tfCategEAddNom.getText());
            
            se.ajouter(p);
            JOptionPane.showMessageDialog(null, "Categorie ajouté !");
    }
     @FXML
   private void showCategorieE(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageCategE.fxml"));
        AddEventController aec = loader.getController();
        Parent root = loader.load();
        showCategEBT.getScene().setRoot(root);
       
   }
   
   }

   

    
    

