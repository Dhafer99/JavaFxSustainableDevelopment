/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import Models.Association;
import ServiceAssociation.AssociationService;
import googledrive.UIController;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class SampleController implements Initializable {

    @FXML
    private Label NbDon;
    @FXML
    private GridPane citiesGrid;
AssociationService s= new AssociationService();
    private ImageView btnback;
    @FXML
    private ImageView bntback;
    @FXML
    private Button btnass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Association> personnes = s.displayAll();
            NbDon.setText(String.valueOf(personnes.size()));
            int column=0;
            int row = 1;
            for (Association d :personnes ){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/CardView.fxml"));
               
                    Pane pane = fxmlLoader.load();
               
                CardViewController cardViewController = fxmlLoader.getController();
                cardViewController.setData(d);
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
    private void back(ActionEvent event)throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/UI.fxml"));
        UIController aec = loader.getController();
        Parent root = loader.load();
        btnass.getScene().setRoot(root);

    }

 

   
}
