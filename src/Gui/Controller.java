package Gui;

import Service.EvenementService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Entities.Evenement;
import java.io.File;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Controller implements Initializable {
    private GridPane citiesGrid;
EvenementService s= new EvenementService();
    private Label NbDon;
    private Parent fxml;
    @FXML
    private Button addBtn;
    private TextField Text;
    private Button Menubtn;
    private ObservableList<Evenement> originalDonList;
private ObservableList<Evenement> filteredDonList;
    @FXML
    private HBox recentlyPlayedContainer;
    @FXML
    private HBox favoriteContainer;
    @FXML
    private TextField text;
    @FXML
    private Label label;
    @FXML
    private Label nb;
    @FXML
    private MediaView mediaV;
    @FXML
    private Pane pane;
    @FXML
    private Button addbtn;
    @FXML
    private Button Back;
   /* private File file ;
    private Media media;
    private MediaPlayer mediaPlayer;*/
    /**
     * Initializes the controller class.
     */
   @Override
public void initialize(URL url, ResourceBundle rb) {
  // String filePath = "C:\\Users\\21629\\Downloads\\aa.mp4";
//String uriString = "file:///" + filePath.replace("\\", "/");
//Media media = new Media(uriString);
//MediaPlayer mediaPlayer = new MediaPlayer(media);
//mediaV.setMediaPlayer(mediaPlayer);
//mediaV.setFitHeight(320);
//mediaV.setFitWidth(300);
//mediaPlayer.play();
    try {
        try {
            // Get the list of Don objects
            originalDonList = FXCollections.observableArrayList(s.afficher());
            filteredDonList = FXCollections.observableArrayList(originalDonList);
                    nb.setText(originalDonList.size() + " Evenements Disponible");

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
        List<Evenement> personnes = s.afficher();
        
        // sort the list by position in list in descending order
        Collections.sort(personnes, new Comparator<Evenement>() {
            @Override
            public int compare(Evenement d1, Evenement d2) {
                return personnes.indexOf(d2) - personnes.indexOf(d1);
            }
        });
        
        // clear the favoriteContainer
        recentlyPlayedContainer.getChildren().clear();
        
        // add the first three Don objects to the favoriteContainer
        int count = 0;
        for (Evenement d : personnes) {
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
    for (Evenement d : originalDonList) {
        if (d.getNom_event().toLowerCase().contains(query.toLowerCase())) {
            filteredDonList.add(d);
        } else if (d.getLocalisation().toLowerCase().contains(query.toLowerCase())) {
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

private void updateUI(ObservableList<Evenement> donList) throws SQLException, IOException {
    // Clear the container before populating it
    favoriteContainer.getChildren().clear();
  
    for (Evenement d : donList) {
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
private void Back(ActionEvent event) throws IOException{

         FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageEvent.fxml"));
    AffichageEventController aec = loader.getController();
        Parent root = loader.load();
        Back.getScene().setRoot(root);
  
}
@FXML
    private void filter(ActionEvent event) throws SQLException, IOException {
        List<Evenement> personnes = s.afficher();

// sort the list by quantity in descending order
Collections.sort(personnes, new Comparator<Evenement>() {
    @Override
    public int compare(Evenement d1, Evenement d2) {
        return Integer.compare(d2.getNb_participants(), d1.getNb_participants());
    }
});

//NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");

// display the sorted Don objects in the CardView
 favoriteContainer.getChildren().clear();
  
    for (Evenement d : personnes) {
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

    private void Menu(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageEvent.fxml"));
    AffichageEventController aec = loader.getController();
        Parent root = loader.load();
        Menubtn.getScene().setRoot(root);
    }


    @FXML
    private void handleLabelClick(MouseEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) label.getScene().getWindow();
    stage.setScene(scene);
    }
   
    
}
