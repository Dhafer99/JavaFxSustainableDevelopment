/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashbord;

import integration.Integration;
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
public class UIController implements Initializable {

    @FXML
    private AnchorPane root;
private Parent fxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Reclamation(MouseEvent event) {
        
        try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/HomeReclamation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void association(MouseEvent event) {
        
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/HomeAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void Annonces(MouseEvent event) {
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/HomeAnnonces.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void Don(MouseEvent event) {
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/HomeDon.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void event(MouseEvent event) {
        
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/HomeEvenement.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void user(MouseEvent event) throws IOException {
        //Integration m = new Integration();
        //m.changeScene("/Views/LoggedIn.fxml");
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/LoggedIn.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }
    
}
