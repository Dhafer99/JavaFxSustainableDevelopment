package Annonces.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import Annonces.services.AnnonceService;
import java.util.List;
import Annonces.entities.Annonces;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 21629
 */
public class SampleController implements Initializable {

    @FXML
    private GridPane citiesGrid;
    AnnonceService s = new AnnonceService();
    @FXML
    private Label NbAnnonces;

    @FXML
    private Button filter;
    @FXML
    private ScrollPane NbAnnonce;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Annonces> personnes = s.displayAll();
            System.out.println(personnes);
            NbAnnonces.setText(String.valueOf(personnes.size()));
            int column = 0;
            int row = 1;
            for (Annonces d : personnes) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardview.fxml"));

                Pane pane = fxmlLoader.load();
                System.out.println("++++++++++++++++" + d.getCategorie());
                System.out.println(d.getId());
                CardviewController cardViewController = fxmlLoader.getController();
                cardViewController.setData(d);
                CardviewController A = fxmlLoader.getController();
                A.receiveObject(d);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                citiesGrid.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20, 20, 20, 20));
            }
        } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

}
