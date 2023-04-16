/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.CategorieEvent;
import Services.CategorieEventService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class AddCategorieEventController implements Initializable {

    @FXML
    private TextField tfCategEAddNom;
    @FXML
    private Button CategEAddButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutCategE(ActionEvent event) {
          CategorieEventService se = new CategorieEventService();
                       CategorieEvent p = new CategorieEvent();

        p.setNom(tfCategEAddNom.getText());
            
            se.ajouter(p);
            JOptionPane.showMessageDialog(null, "Categorie ajouté !");
    }
    
}
