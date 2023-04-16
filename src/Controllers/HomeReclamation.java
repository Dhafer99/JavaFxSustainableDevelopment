/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class HomeReclamation implements Initializable {

    @FXML
    private AnchorPane root1;
private Parent fxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(MouseEvent event) {
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/AjoutReclamation.fxml"));
           root1.getChildren().removeAll();
           root1.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void afficher(MouseEvent event) {
           try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/AfficherReclamation.fxml"));
           root1.getChildren().removeAll();
           root1.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void gestion(MouseEvent event) {
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/CategorieReclamation.fxml"));
           root1.getChildren().removeAll();
           root1.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }
    
}
