/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.gui;

import Annonces.entities.Annonces;
import Annonces.services.AnnonceService;
import Annonces.services.UserService;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
  private ObservableList<Annonces> originalDonList;
private ObservableList<Annonces> filteredDonList;
    @FXML
    private TextField text;
    AnnonceService s= new AnnonceService();
    @FXML
    private Button addBtn;
    @FXML
    private Label restants;
    @FXML
    private HBox restantContainer;
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
//        pdp.setImage(new Image(projetpidd.ProjetPiDD.user.getImage()));
        
    try {
        try {
            // Get the list of Don objects
            originalDonList = FXCollections.observableArrayList(s.displayAll());
            filteredDonList = FXCollections.observableArrayList(originalDonList);
                    nb.setText(originalDonList.size() + " Annonce Disponible");

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
                    Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Annonces> personnes = s.displayAll();
        
        // sort the list by position in list in descending order
        Collections.sort(personnes, new Comparator<Annonces>() {
            @Override
            public int compare(Annonces d1,Annonces d2) {
                return personnes.indexOf(d2) - personnes.indexOf(d1);
            }
        });
        
        // clear the favoriteContainer
        recentlyPlayedContainer.getChildren().clear();
        
        // add the first three Don objects to the favoriteContainer
        int count = 0;
        for (Annonces d : personnes) {
            if (count >= 3) {
                break;
            }
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("song.fxml"));
            Pane pane = fxmlLoader.load();
            SongController cardViewController = fxmlLoader.getController();
            cardViewController.setData(d);
            SongController controller = fxmlLoader.getController();
            try {
                controller.receiveObject(d);
            } catch (SQLException ex) {
                Logger.getLogger(DonFController.class.getName()).log(Level.SEVERE, null, ex);
            }
            recentlyPlayedContainer.getChildren().add(pane);
            
            count++;
        }
    }   catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    try {
        
    List<Annonces> filteredAnnoncesList = new ArrayList<>();
    
    List<Annonces> rest = new ArrayList<>();
    UserService svuser= new UserService();
    List<String> userInterests = svuser.getUserInterests(projetpidd.ProjetPiDD.user.getNom());
      int c = 0;  

    for (Annonces annonce : originalDonList) { //récuperer la liste des annonces
                    int test = 0;  
         for (int i = 0; i < userInterests.size(); i++) { //chaque annonce on va comparer sa catégorie avec les catégories userInterests
         
             String element = userInterests.get(i);  
  
             if (element.equals(annonce.getCategorie().getNom()))
             { 
               filteredAnnoncesList.add(annonce);
             c++; 
             test=1;
             }    
    } 
         if (test==0) 
                 rest.add(annonce);
    }
       //filteredAnnoncesList.addAll(rest);
                     restants.setText("Autres annonces");
                   restants.setStyle("-fx-text-fill: black;");

                    nb.setText(c+ " Annonces qui pouuraient vous interesse");

                          updateUIII(rest);
    updateUII(filteredAnnoncesList);

} catch (SQLException ex) {
    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
}
}

private void filterDonList(String query) throws SQLException {
    filteredDonList.clear();
    for (Annonces d : originalDonList) {
        if (d.getAdresse().toLowerCase().contains(query.toLowerCase())) {
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

private void updateUI(ObservableList<Annonces> donList) throws SQLException, IOException {
    // Clear the container before populating it
    favoriteContainer.getChildren().clear();
  
    for (Annonces d : donList) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("song.fxml"));
    
        Pane pane = fxmlLoader.load();
        SongController cardViewController = fxmlLoader.getController();
        cardViewController.setData(d);
        SongController controller = fxmlLoader.getController();
        controller.receiveObject(d);
        favoriteContainer.getChildren().add(pane); 
        if(SongController.test== true){
        favoriteContainer.getChildren().clear();
        favoriteContainer.getChildren().add(pane); 
        }
    }
}
 

  /*  @FXML
    private void filter(ActionEvent event) throws IOException, SQLException {
        List<entities.Annonces> personnes = s.displayAll();

// sort the list by quantity in descending order
Collections.sort(personnes, new Comparator<entities.Annonces>() {
    @Override
    public int compare(entities.Annonces d1, entities.Annonces d2) {
        return Integer.compare(d2.getNombre_etoiles(), d1.getNombre_etoiles());
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
    }
*/
    @FXML
    private void AddNew(ActionEvent event) throws IOException{

         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutAnnonce.fxml"));
    AjoutAnnonceController aec = loader.getController();
        Parent root = loader.load();
        addBtn.getScene().setRoot(root);
  
}

    /*@FXML
    private void AddNew(MouseEvent event) {
    }*/
    public void updateUII(List<Annonces> annonces) throws IOException {
    favoriteContainer.getChildren().clear();

    for (Annonces annonce : annonces) {
         FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("song.fxml"));
            Pane pane = fxmlLoader.load();
            SongController CardviewController = fxmlLoader.getController();
            CardviewController.setData(annonce);
            SongController controller = fxmlLoader.getController();
        try {
            controller.receiveObject(annonce);
        } catch (SQLException ex) {
            Logger.getLogger(DonFController.class.getName()).log(Level.SEVERE, null, ex);
        }
            favoriteContainer.getChildren().add(pane);
        
        
       
        
    }
}

public void updateUIII(List<Annonces> annonces) throws IOException, SQLException {
    restantContainer.getChildren().clear();

    for (Annonces annonce : annonces) {
       FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("song.fxml"));
            Pane pane = fxmlLoader.load();
            SongController CardviewController = fxmlLoader.getController();
            CardviewController.setData(annonce);
            SongController controller = fxmlLoader.getController();
            controller.receiveObject(annonce);
            restantContainer.getChildren().add(pane);
        
        
       
        
    }
}

    @FXML
    private void AddNew(MouseEvent event) {
    }
}
