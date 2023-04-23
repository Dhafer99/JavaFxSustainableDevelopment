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
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.db_connect;

/**
 */
public class SongController {
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
        ps.supprimer(don);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Controller aec = loader.getController();
        Parent root = loader.load();
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
    } else {
        Quantite.setText(Integer.toString(newQuantity));
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
    Mapa temp = new Mapa("first Map",lat,lon);
    }

   
}

