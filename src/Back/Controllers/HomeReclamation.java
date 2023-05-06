/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

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

  
private Parent fxml;
    @FXML
    private AnchorPane root;
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
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/AjoutReclamation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void afficher(MouseEvent event) {
           try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/AfficherReclamation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void gestion(MouseEvent event) {
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/CategorieReclamation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void chart(MouseEvent event) {
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/ChartRec.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }
    
}
