package don;

import Donneur.Entities.Don;
import Donneur.Service.AddressToLatLng;
import static Donneur.Service.AddressToLatLng.getLatLongFromAddress;
import Donneur.Service.ServiceDon;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
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
import org.json.JSONObject;
import Donneur.utils.db_connect;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFrame;
import org.json.JSONException;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

/**
 */
public class SongController  {
    @FXML
    private ImageView img;
    @FXML
    private Label DonName;
    @FXML
    private Label Quantite;
    @FXML
    private Label Email;
    @FXML
    private Label Numero;
Don d = new Don();
private Don don;
    @FXML
    private Button BtnUpdate;
    @FXML
    private Button DeleteBtn;
    ServiceDon ps = new ServiceDon();
    @FXML
    private Label categ;
     HashMap<String, String> categoryUnits = new HashMap<>();
    @FXML
    private Button ClaimBtn;
    @FXML
    private Button MapBtn;
    public static Boolean test = false;
private static String ms ;
double toleranceMin = 0.00005;
double toleranceMax = 1.2;
 
     public void setData(Don don) throws SQLException{
         ServiceDon sv = new ServiceDon();
         System.out.println(projetpidd.ProjetPiDD.user.getType());
         if(projetpidd.ProjetPiDD.user.getType().equals("Receveur"))
         {
             ClaimBtn.setVisible(true);
             BtnUpdate.setVisible(false);
              DeleteBtn.setVisible(false);  
         }
         else
         {
              ClaimBtn.setVisible(false);
         }
          if(projetpidd.ProjetPiDD.user.getId()!=sv.searchDonById(don.getId()))
         {
            
             BtnUpdate.setVisible(false);
              DeleteBtn.setVisible(false);  
         }
         
        categoryUnits.put("Appareil","Obj");
        categoryUnits.put("Argent", "D");
        categoryUnits.put("Nourriture", "Kg");
        categoryUnits.put("Habillement", "Piece");
        categoryUnits.put("Sang", "L");
        categoryUnits.put("Livres", "Livre");
        categoryUnits.put("Jouet", "Jouet");
     DonName.setText(don.getNameD());
        Quantite.setText(Integer.toString(don.getQuantite()));
        Email.setText(don.getEmail());
        Numero.setText(Integer.toString(don.getNumero()));
   img.setImage(new Image("file:C:\\xampp\\htdocs\\public"+don.getImage()));

        
        if (don.getCategory_d_id()== 0) {
    categ.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = db_connect.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT name_ca FROM category_d WHERE id = ?");
ps.setInt(1, don.getCategory_d_id());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
            String measurementUnit = categoryUnits.get(nomCategorie);
            ms = measurementUnit;
if (measurementUnit != null) {
    categ.setText(nomCategorie);
 Quantite.setText(Integer.toString(don.getQuantite())+" "+measurementUnit);
}
else{System.out.println("Can't");}
} else {
    categ.setText("Catégorie introuvable");
}
   
   }
@FXML
    private void ToUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    AddUpdateController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Don d) {
        this.d=d;
        don=d;
    }
@FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
    System.out.println(don);
    test = true;
        ps.supprimer(don);  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
Parent root = loader.load();

AnchorPane myVBox = (AnchorPane) root.lookup("#route");

FXMLLoader includedLoader = new FXMLLoader(getClass().getResource("DonF.fxml"));
Node myAnchorPane = includedLoader.load();

myVBox.getChildren().add(myAnchorPane);

FrontController frontController = loader.getController();
DonFController donFController = includedLoader.getController();

donFController.setParentController(frontController);

DeleteBtn.getScene().setRoot(root);
    }
    @FXML
private void claim(ActionEvent event) throws SQLException, IOException {
    int originalQuantity = don.getQuantite();
    int newQuantity = (int) (originalQuantity * 0.9); // decrease quantity by 10%
    don.setQuantite(newQuantity);
    ps.modifier(don);
    if (newQuantity <= 0) {
        delete(event);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
Parent root = loader.load();

AnchorPane myVBox = (AnchorPane) root.lookup("#route");

FXMLLoader includedLoader = new FXMLLoader(getClass().getResource("DonF.fxml"));
Node myAnchorPane = includedLoader.load();

myVBox.getChildren().add(myAnchorPane);

FrontController frontController = loader.getController();
DonFController donFController = includedLoader.getController();

donFController.setParentController(frontController);

ClaimBtn.getScene().setRoot(root);
    } else {
        Quantite.setText(Integer.toString(newQuantity)+" "+ms);
        ClaimBtn.setOnAction(t -> {
    Quantite.setText(Integer.toString(newQuantity)+" "+ms);
});
    }
    int restquantity= (int) (don.getQuantite()*0.1) ;
    
    ps.insertClaim(don.getId(),ps.searchDonById(don.getId()),projetpidd.ProjetPiDD.user.getId(),don.getQuantite(),restquantity);
}

  
   @FXML
private void map(ActionEvent event) throws IOException {
    JsonObject response = getLatLongFromAddress(don.getLocalisation());
    double lat = response.get("lat").getAsDouble();
    double lon = response.get("lon").getAsDouble();
        JMapViewer mapViewer = new JMapViewer();

        // Set the default zoom level and center point of the map
        mapViewer.setDisplayPosition(new Coordinate(lat, lon), 11);

        // Add a marker to the map
        MapMarkerDot marker = new MapMarkerDot(new Coordinate(lat, lon));
        mapViewer.addMapMarker(marker);
mapViewer.zoomIn();


        

        // Create a new JFrame to display the map
        JFrame frame = new JFrame("JMapViewer Example");
        frame.setLayout(new BorderLayout());
        frame.add(mapViewer, BorderLayout.CENTER);
        frame.setSize(new Dimension(1000, 800));
        frame.setVisible(true);
}

public void setLocation() throws IOException, JSONException{

    // Obtain public IP address
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
            double latitude = jsonResponse.getDouble("lat");
            double longitude = jsonResponse.getDouble("lon");
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
       
 JsonObject res = getLatLongFromAddress(don.getLocalisation());
    double lat = res.get("lat").getAsDouble();
    double lon = res.get("lon").getAsDouble();
    if (Math.abs(latitude -lat ) >= toleranceMin && Math.abs( latitude- lat) <= toleranceMax ) {
while(toleranceMin==0.00005){   }
}        
}
    
}

