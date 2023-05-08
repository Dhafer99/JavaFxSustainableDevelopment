/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenements.Gui;

import Evenements.Service.EvenementService;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
  private ObservableList<Evenements.Entities.Evenement> originalDonList;
private ObservableList<Evenements.Entities.Evenement> filteredDonList;
    @FXML
    private TextField text;
    EvenementService s= new EvenementService();
    @FXML
    private Button addBtn;
    private FrontController parentController;
    @FXML
    private Button left;
    @FXML
    private Button right;
    @FXML
    private ScrollPane ScrollPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        left.setOnAction(event -> {
    double scrollAmount = ScrollPane.getWidth() / 2;
    double maxScroll = ScrollPane.getContent().getBoundsInLocal().getWidth() - favoriteContainer.getWidth();
    double currentScroll = ScrollPane.getHvalue() * maxScroll;
    ScrollPane.setHvalue(Math.max(0, (currentScroll - scrollAmount) / maxScroll));
});

right.setOnAction(event -> {
    double scrollAmount = ScrollPane.getWidth() / 2;
    double maxScroll = ScrollPane.getContent().getBoundsInLocal().getWidth() - favoriteContainer.getWidth();
    double currentScroll = ScrollPane.getHvalue() * maxScroll;
    ScrollPane.setHvalue(Math.min(1, (currentScroll + scrollAmount) / maxScroll));
});
    try {
        try {
            // Get the list of Don objects
            originalDonList = FXCollections.observableArrayList(s.afficher());
            filteredDonList = FXCollections.observableArrayList(originalDonList);
                    nb.setText(originalDonList.size() + " Evenement Disponible");

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
                  ex.printStackTrace();
                }
            });
            
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        List<Evenements.Entities.Evenement> personnes = s.afficher();
        
        // sort the list by position in list in descending order
        Collections.sort(personnes, new Comparator<Evenements.Entities.Evenement>() {
            @Override
            public int compare(Evenements.Entities.Evenement d1, Evenements.Entities.Evenement d2) {
                return personnes.indexOf(d2) - personnes.indexOf(d1);
            }
        });
        
        // clear the favoriteContainer
        recentlyPlayedContainer.getChildren().clear();
        
        // add the first three Don objects to the favoriteContainer
        int count = 0;
        for (Evenements.Entities.Evenement d : personnes) {
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
    for (Evenements.Entities.Evenement d : originalDonList) {
        if (d.getNom_event().toLowerCase().contains(query.toLowerCase())) {
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

private void updateUI(ObservableList<Evenements.Entities.Evenement> donList) throws SQLException, IOException {
    // Clear the container before populating it
    favoriteContainer.getChildren().clear();
  
    for (Evenements.Entities.Evenement d : donList) {
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
        List<Evenements.Entities.Evenement> personnes = s.afficher();

// sort the list by quantity in descending order
Collections.sort(personnes, new Comparator<Evenements.Entities.Evenement>() {
    @Override
    public int compare(Evenements.Entities.Evenement d1, Evenements.Entities.Evenement d2) {
        return Integer.compare(d2.getNb_participants(), d1.getNb_participants());
    }
});

//NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");

// display the sorted Don objects in the CardView
 favoriteContainer.getChildren().clear();
  
    for (Evenements.Entities.Evenement d : personnes) {
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
    private void filter2(ActionEvent event) throws SQLException, IOException {
        List<Evenements.Entities.Evenement> personnes = s.afficher();

// Sort the list by date in descending order
Collections.sort(personnes, new Comparator<Evenements.Entities.Evenement>() {
    @Override
    public int compare(Evenements.Entities.Evenement d1, Evenements.Entities.Evenement d2) {
        return d2.getDate_debut().compareTo(d1.getDate_fin());
    }
});

// Clear the container before adding new cards
favoriteContainer.getChildren().clear();

// Display the sorted Don objects in the CardView
for (Evenements.Entities.Evenement d : personnes) {
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
    private void AddNew(ActionEvent event) throws IOException{

         FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
    AddUpdateEController aec = loader.getController();
        Parent root = loader.load();
        addBtn.getScene().setRoot(root);
  
}

    @FXML
    private void AddNew(MouseEvent event) {
    }
    public void setFrontController(FrontController parentController) {
        this.parentController = parentController;
    }
    
}
