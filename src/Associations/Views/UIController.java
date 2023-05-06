/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Views;

import Associations.Views.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class UIController implements Initializable {

    @FXML
    private Button FrontBtn;
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
    private void home(MouseEvent event) {
    }


    @FXML
    private void Modifier(MouseEvent event) {
        try {
           fxml = FXMLLoader.load(getClass().getResource("EditAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void front(ActionEvent event) throws IOException {
       
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
        Controller aec = loader.getController();
        Parent root2 = loader.load();
        FrontBtn.getScene().setRoot(root2); 
    }


    @FXML
    private void categ(MouseEvent event) {
        
            try {
           fxml = FXMLLoader.load(getClass().getResource("categorie.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void web(MouseEvent event) {
           try {
           fxml = FXMLLoader.load(getClass().getResource("web.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }
    
}
