package don;

import Entities.Don;
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
import utils.db_connect;

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
   img.setImage(new Image("file:src\\uploads\\"+don.getImage()+".png"));

        
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
        FrontController aec = loader.getController();
        Parent root = loader.load();
        ClaimBtn.getScene().setRoot(root);
    } else {
        Quantite.setText(Integer.toString(newQuantity)+" "+ms);
        ClaimBtn.setOnAction(t -> {
    Quantite.setText(Integer.toString(newQuantity)+" "+ms);
});
    }
}

    @FXML
    private void map(ActionEvent event) throws IOException {
         System.out.println(""+AddressToLatLng.getLatLongFromAddress(don.getLocalisation()));
         JsonObject response = getLatLongFromAddress(don.getLocalisation());
    double lat = response.get("lat").getAsDouble();
    double lon = response.get("lon").getAsDouble();
    System.out.println("Latitude: " + lat);
    System.out.println("Longitude: " + lon);
    Mapa temp = new Mapa(""+d.getNameD(),lat,lon);
    }
public void setLocation() throws IOException{

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

