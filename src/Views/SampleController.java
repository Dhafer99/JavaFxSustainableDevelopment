/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Reclamation;
import ServicesReclamations.ReclamationService;
import Views.AjoutModifController;
import Views.HomeController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class SampleController implements Initializable {

    @FXML
    private GridPane citiesGrid;
    ReclamationService s= new ReclamationService();
    @FXML
    private Label NbReclamation;
    @FXML
    private Button btnrec;
    @FXML
    private Button addBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            List<Reclamation> personnes = s.displayAll();
            System.out.println(personnes);
            NbReclamation.setText(String.valueOf(personnes.size()));
            int column=0;
            int row = 1;
            for (Reclamation d :personnes ){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/CardView.fxml"));
               
                    Pane pane = fxmlLoader.load();
               
                CardViewController cardViewController = fxmlLoader.getController();
                try {
                    cardViewController.setData(d);
                    cardViewController.receiveObject(d);
                    System.out.println(d);
                } catch (SQLException ex) {
                    Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(column == 3){
                column = 0;
                ++row;
                }
                citiesGrid.add(pane, column++, row);
                GridPane.setMargin(pane,new Insets(20,20,20,20));
            }
       
        } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/home.fxml"));
        HomeController aec = loader.getController();
        Parent root = loader.load();
        btnrec.getScene().setRoot(root);
    }

    @FXML
private void AddNew(ActionEvent event) throws IOException{

         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AjoutModif.fxml"));
    AjoutModifController aec = loader.getController();
        Parent root = loader.load();
        addBtn.getScene().setRoot(root);
  
}
    
}
