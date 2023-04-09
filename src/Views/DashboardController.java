/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class DashboardController implements Initializable {
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
           fxml = FXMLLoader.load(getClass().getResource("/Views/AjoutAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }        
    }
            
    @FXML
    private void home(MouseEvent event) {
        
       
        
    }

    @FXML
    private void afficher(MouseEvent event) {
        
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/AfficherAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void sakarna(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void update(MouseEvent event) {
        
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/EditAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }
    
}
