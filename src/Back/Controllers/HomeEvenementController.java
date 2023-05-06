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
public class HomeEvenementController implements Initializable {
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
    private void add(MouseEvent event) {
    // AddEvent
       try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/AddEvent.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void affichage(MouseEvent event) {
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/affichageEvent.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void edit(MouseEvent event) {
        
           try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/editEvent.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
        
    }

    @FXML
    private void ajoutCateg(MouseEvent event) {
       try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/AddCategorieEvent.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void afficheCateg(MouseEvent event) {
               try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/AffichageCategE.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }
    
}
