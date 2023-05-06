/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Views;

import Associations.Services.ServiceAssociation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 21629
 */
public class DonFController implements Initializable {

    @FXML
    public HBox recentlyPlayedContainer;
    @FXML
    private Label nb;
    @FXML
    public HBox favoriteContainer;
  private ObservableList<Associations.Models.Association> originalDonList;
private ObservableList<Associations.Models.Association> filteredDonList;
    @FXML
    private TextField text;
    ServiceAssociation s= new ServiceAssociation();
    private Button addBtn;
    @FXML
    private ImageView pdp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         pdp.setImage(new Image(projetpidd.ProjetPiDD.user.getImage()));
        
    try {
        try {
            // Get the list of Don objects
            originalDonList = FXCollections.observableArrayList(s.afficher());
            filteredDonList = FXCollections.observableArrayList(originalDonList);
                    nb.setText(originalDonList.size() + " Don Disponible");

            try {
                // Populate the GridPane with the card views
                updateUI(filteredDonList);
            } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Register an event listener on the search field
            text.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    filterDonList(newValue);
                } catch (SQLException ex) {
                   // Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (SQLException ex) {
            //Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Associations.Models.Association> personnes = s.afficher();
        
        // sort the list by position in list in descending order
        Collections.sort(personnes, new Comparator<Associations.Models.Association>() {
            @Override
            public int compare(Associations.Models.Association d1, Associations.Models.Association d2) {
                return personnes.indexOf(d2) - personnes.indexOf(d1);
            }
        });
        
        // clear the favoriteContainer
        recentlyPlayedContainer.getChildren().clear();
        
        // add the first three Don objects to the favoriteContainer
        int count = 0;
        for (Associations.Models.Association d : personnes) {
            if (count >= 3) {
                break;
            }
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("song.fxml"));
            Pane pane = fxmlLoader.load();
            SongController cardViewController = fxmlLoader.getController();
            cardViewController.setData(d);
            SongController controller = fxmlLoader.getController();
            controller.receiveObject(d);
            recentlyPlayedContainer.getChildren().add(pane);
            
            count++;
        }
    } catch (SQLException ex) {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
}

private void filterDonList(String query) throws SQLException {
    filteredDonList.clear();
    for (Associations.Models.Association d : originalDonList) {
        if (d.getNom().toLowerCase().contains(query.toLowerCase())) {
            filteredDonList.add(d);
        }
    }
    try {
        // Update the UI with the filtered list
        updateUI(filteredDonList);
    } catch (IOException ex) {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private void updateUI(ObservableList<Associations.Models.Association> donList) throws SQLException, IOException {
    // Clear the container before populating it
    favoriteContainer.getChildren().clear();
  
    for (Associations.Models.Association d : donList) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("song.fxml"));
    
        Pane pane = fxmlLoader.load();
        SongController cardViewController = fxmlLoader.getController();
        cardViewController.setData(d);
        SongController controller = fxmlLoader.getController();
        controller.receiveObject(d);
        favoriteContainer.getChildren().add(pane); 
       
    }
}
 

    @FXML
    private void filter(ActionEvent event) throws IOException, SQLException {
        /*
        List<Models.Association> personnes = s.afficher();

// sort the list by quantity in descending order
Collections.sort(personnes, new Comparator<Entities.Don>() {
    @Override
    public int compare(Entities.Don d1, Entities.Don d2) {
        return Integer.compare(d2.getQuantite(), d1.getQuantite());
    }
});

//NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");

// display the sorted Don objects in the CardView
 favoriteContainer.getChildren().clear();
  
    for (Entities.Don d : personnes) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("song.fxml"));
    
        Pane pane = fxmlLoader.load();
        SongController cardViewController = fxmlLoader.getController();
        cardViewController.setData(d);
        SongController controller = fxmlLoader.getController();
        controller.receiveObject(d);
        favoriteContainer.getChildren().add(pane); 
    }
*/
    }
    private void AddNew(ActionEvent event) throws IOException{

         FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAssociation.fxml"));
    EditAssociationController aec = loader.getController();
        Parent root = loader.load();
        addBtn.getScene().setRoot(root);
  
}
    
}
