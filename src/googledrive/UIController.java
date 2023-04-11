/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googledrive;

import don.SampleController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class UIController implements Initializable {
 private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    private Button FrontBtn;

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
           fxml = FXMLLoader.load(getClass().getResource("/Views/AjoutAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }    
    }

    @FXML
    private void Modifier(MouseEvent event) {
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/EditAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    private void Historique(MouseEvent event) {
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/AfficherAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }



    @FXML
    private void home(MouseEvent event) {
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/Menu.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }   
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
    private void categ(MouseEvent event) {
        
        
        
            try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/categorie.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }   
    }

    @FXML
    private void front(ActionEvent event)throws   IOException  {
        
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/sample.fxml"));
        SampleController aec = loader.getController();
        Parent root = loader.load();
        FrontBtn.getScene().setRoot(root);
        
    }
    
}
