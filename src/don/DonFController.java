/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import Donneur.Service.ServiceDon;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import Donneur.Entities.Don;
import static Donneur.Service.AddressToLatLng.getLatLongFromAddress;
import com.google.gson.JsonObject;
import don.SongController ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.JsonObject;
/*import Entities.Don;
import Service.AddressToLatLng;
import static Service.AddressToLatLng.getLatLongFromAddress;
import Service.ServiceDon;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import don.AddUpdateController;
import don.SampleController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JFrame;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.Parameters.Routing;
import com.graphhopper.util.shapes.GHPoint;
import static don.Don.loadFXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import utils.db_connect;*/
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
  private ObservableList<Don> originalDonList;
private ObservableList<Don> filteredDonList;
    @FXML
    private TextField text;
    ServiceDon s= new ServiceDon();
    @FXML
    private Button addBtn;
    @FXML
    private HBox newContainer;
    private FrontController parentController;
    @FXML
    private ImageView pdp;
private double FILTER_RADIUS=20.0;
    public void setParentController(FrontController parentController) {
        this.parentController = parentController;
    }
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
             
            }
            
            // Register an event listener on the search field
            text.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    filterDonList(newValue);
                } catch (SQLException ex) {
                    
                }
            });
            
        } catch (SQLException ex) {
           
        }
        List<Don> personnes = s.afficher();
        
        // sort the list by position in list in descending order
        Collections.sort(personnes, new Comparator<Don>() {
            @Override
            public int compare(Don d1, Don d2) {
                return personnes.indexOf(d2) - personnes.indexOf(d1);
            }
        });
        
        // clear the favoriteContainer
        recentlyPlayedContainer.getChildren().clear();
        
        // add the first three Don objects to the favoriteContainer
        int count = 0;
        for (Don d : personnes) {
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
// sort the list by quantity in descending order
int user_id_current = projetpidd.ProjetPiDD.user.getId();
List<Don> filteredDonations = new ArrayList<>();

for (Don donation : personnes) {
    try {
        int user_id_donation = s.searchDonById(donation.getId());
        if (user_id_donation == user_id_current) {
            filteredDonations.add(donation);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DonFController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        System.out.println("LISTE TRIEEEE" +filteredDonations);
//NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");

// display the sorted Don objects in the CardView
 newContainer.getChildren().clear();
  
    for (Don d : filteredDonations) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("song.fxml"));
            
            Pane pane = fxmlLoader.load();
            SongController cardViewController = fxmlLoader.getController();
            cardViewController.setData(d);
            SongController controller = fxmlLoader.getController();
            controller.receiveObject(d); 
            newContainer.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(DonFController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DonFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    } catch (SQLException ex) {
      
    }   catch (IOException ex) {
        
        }
    

    
}

private void filterDonList(String query) throws SQLException {
    filteredDonList.clear();
    for (Don d : originalDonList) {
        if (d.getNameD().toLowerCase().contains(query.toLowerCase())) {
            filteredDonList.add(d);
        }
    }
    try {
        // Update the UI with the filtered list
        updateUI(filteredDonList);
    } catch (IOException ex) {
       
    }
}

private void updateUI(ObservableList<Don> donList) throws SQLException, IOException {
    // Clear the container before populating it
    favoriteContainer.getChildren().clear();
  
    for (Don d : donList) {
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
 

    @FXML
    private void filter(ActionEvent event) throws IOException, SQLException {
        List<Don> personnes = s.afficher();

// sort the list by quantity in descending order
Collections.sort(personnes, new Comparator<Don>() {
    @Override
    public int compare(Don d1,Don d2) {
        return Integer.compare(d2.getQuantite(), d1.getQuantite());
    }
});

//NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");

// display the sorted Don objects in the CardView
 favoriteContainer.getChildren().clear();
  
    for (Don d : personnes) {
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
    AddUpdateController aec = loader.getController();
        Parent root = loader.load();
        addBtn.getScene().setRoot(root);
  
}

    @FXML
    private void AddNew(MouseEvent event) {
    }

    @FXML
    private void filterLoc(ActionEvent event) {
    }
   
    
}
