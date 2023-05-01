/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import static Service.AddressToLatLng.getLatLongFromAddress;
import Service.ServiceDon;
import com.google.gson.JsonObject;
import static com.restfb.types.whatsapp.platform.SendMessage.Type.location;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import org.json.JSONObject;
/**
 * FXML Controller class
 *
 * @author 21629
 */
public class DonFController implements Initializable {

    @FXML
    public  HBox recentlyPlayedContainer;
    @FXML
    private Label nb;
    @FXML
     public HBox favoriteContainer;


    private FrontController parentController;
  private ObservableList<Entities.Don> originalDonList;
private ObservableList<Entities.Don> filteredDonList;
    @FXML
    private TextField text;
    ServiceDon s= new ServiceDon();
    @FXML
    private Button addBtn;
    Don don = new Don();
    private Double FILTER_RADIUS=30.0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                    Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Entities.Don> personnes = s.afficher();
        
        // sort the list by position in list in descending order
        Collections.sort(personnes, new Comparator<Entities.Don>() {
            @Override
            public int compare(Entities.Don d1, Entities.Don d2) {
                return personnes.indexOf(d2) - personnes.indexOf(d1);
            }
        });
        
        // clear the favoriteContainer
        recentlyPlayedContainer.getChildren().clear();
        
        // add the first three Don objects to the favoriteContainer
        int count = 0;
        for (Entities.Don d : personnes) {
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
    for (Entities.Don d : originalDonList) {
        if (d.getNameD().toLowerCase().contains(query.toLowerCase())) {
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

private void updateUI(ObservableList<Entities.Don> donList) throws SQLException, IOException {
    // Clear the container before populating it
    favoriteContainer.getChildren().clear();
  
    for (Entities.Don d : donList) {
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
        List<Entities.Don> personnes = s.afficher();

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
    }
    @FXML
    private void AddNew(ActionEvent event) throws IOException{

         FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
    AddUpdateController aec = loader.getController();
        Parent root = loader.load();
        addBtn.getScene().setRoot(root);
  
}
@FXML
private void filterLoc(ActionEvent event) throws IOException, SQLException  {
    // Get the user's location
    URL url = new URL("http://ip-api.com/json/");
    URLConnection connection = url.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuilder response = new StringBuilder();
    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }
    in.close();
    
    JSONObject jsonResponse = new JSONObject(response.toString());
    double userLatitude = jsonResponse.getDouble("lat");
    double userLongitude = jsonResponse.getDouble("lon");
  
    // Filter donations based on the distance from the user's location
   // Filter donations based on the distance from the user's location
List<Entities.Don> donations = s.afficher().stream()
        .filter(d -> {
            try {
                JsonObject location = getLatLongFromAddress(d.getLocalisation());
                double donLatitude = location.get("lat").getAsDouble();
                double donLongitude = location.get("lon").getAsDouble();
                double distance = calculateDistance(userLatitude, userLongitude, donLatitude, donLongitude);
                return distance <= FILTER_RADIUS;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        })
        .sorted(Comparator.comparingInt(Entities.Don::getQuantite).reversed())
        .collect(Collectors.toList());


    // Update the UI with the filtered donations
    favoriteContainer.getChildren().clear();
    for (Entities.Don d : donations) {
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

public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Radius of the earth in km
    double dLat = deg2rad(lat2 - lat1);  // deg2rad below
    double dLon = deg2rad(lon2 - lon1);
    double a = 
      Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
      Math.sin(dLon/2) * Math.sin(dLon/2)
      ; 
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
    double d = R * c; // Distance in km
    return d;
}

public static double deg2rad(double deg) {
    return deg * (Math.PI/180);
}
public void refreshFavoriteContainer() {
    try {
        // Get the list of Don objects
        originalDonList = FXCollections.observableArrayList(s.afficher());
        filteredDonList = FXCollections.observableArrayList(originalDonList);
        nb.setText(originalDonList.size() + " Don Disponible");

        // Clear the favoriteContainer
        favoriteContainer.getChildren().clear();

        // Populate the favoriteContainer with the updated list of Don objects
        for (Entities.Don d : filteredDonList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("song.fxml"));
            Pane pane = fxmlLoader.load();
            SongController cardViewController = fxmlLoader.getController();
            cardViewController.setData(d);
            SongController controller = fxmlLoader.getController();
            controller.receiveObject(d);
            favoriteContainer.getChildren().add(pane);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 public void setFrontController(FrontController parentController) {
        this.parentController = parentController;
    }

}
