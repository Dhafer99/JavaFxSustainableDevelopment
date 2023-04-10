/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import Service.ServiceDon;
import java.util.List;
import Entities.Don;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author 21629
 */
public class SampleController implements Initializable {

    @FXML
    private GridPane citiesGrid;
ServiceDon s= new ServiceDon();
    @FXML
    private Label NbDon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Don> personnes = s.afficher();
            NbDon.setText(String.valueOf(personnes.size()));
            int column=0;
            int row = 1;
            for (Don d :personnes ){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CardView.fxml"));
               
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
        } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
}
