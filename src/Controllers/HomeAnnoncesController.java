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
public class HomeAnnoncesController implements Initializable {
private Parent fxml;
    @FXML
    private AnchorPane root1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(MouseEvent event) {
        
           try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/AjoutAnnonce.fxml"));
           root1.getChildren().removeAll();
           root1.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void afficher(MouseEvent event) {
    }

    @FXML
    private void categorie(MouseEvent event) {
    }
    
}
