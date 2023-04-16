package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import services.AnnonceService;
import java.util.List;
import entities.Annonces;
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
AnnonceService s= new AnnonceService();
    @FXML
    private Label NbDon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Annonces> personnes = s.displayAll();
            System.out.println(personnes);
            NbDon.setText(String.valueOf(personnes.size()));
            int column=0;
            int row = 1;
            for (Annonces d :personnes ){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cardview.fxml"));
               
                    Pane pane = fxmlLoader.load();
               // System.out.println("++++++++++++++++"+d.getImage());
                System.out.println(d.getId());
                CardviewController cardViewController = fxmlLoader.getController();
                cardViewController.setData(d);
                CardviewController A = fxmlLoader.getController();
                A.receiveObject(d);
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
    
}