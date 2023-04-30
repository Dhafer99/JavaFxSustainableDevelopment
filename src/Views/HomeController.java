/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Views.SampleController;
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
 * @author ghofrane
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane root;
private Parent fxml;
    @FXML
    private Button btnfront;
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
    private void ajouter(MouseEvent event) {
        try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/AjoutReclamation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void afficher(MouseEvent event) {
        try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/AfficherReclamation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void gestion(MouseEvent event) {
        try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/Categorie.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void Front(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Front.fxml"));
        Controller aec = loader.getController();
        Parent root2 = loader.load();
        btnfront.getScene().setRoot(root2);
    }
    
}
