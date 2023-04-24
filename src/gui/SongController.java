package gui;

import entities.Annonces;
//import service.AddressToLatLng;
//import static Service.AddressToLatLng.getLatLongFromAddress;
import services.AnnonceService;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import gui.updateAnnonceController;
import gui.SampleController;
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
import MyBD.DataBase;
import org.controlsfx.control.Rating;

/**
 */
public class SongController {
    @FXML
    private Label description;
    @FXML
    private Label adresse;
    @FXML
    private Label date_publication;
    private ImageView img;
    @FXML
    private ImageView image;
Annonces d = new Annonces();
private Annonces don;
    @FXML
    private Button BtnUpdate;
    @FXML
    private Button DeleteBtn;
    AnnonceService ps = new AnnonceService();
    @FXML
    private Button ClaimBtn;
     @FXML
    private Rating rating ; 
    

      public void setData(Annonces annonce){
     description.setText(annonce.getDescription());
        adresse.setText(annonce.getAdresse());
        date_publication.setText(annonce.getDate_publication());
        //System.out.println(annonce.getImage());
                image.setImage(new Image(annonce.getImage()));
                
               System.out.println("file:src/uploads/"+annonce.getImage()+".png");
               

    }
@FXML
    private void ToUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateAnnonce.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    updateAnnonceController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Annonces d) {
        this.d=d;
        don=d;
    }
@FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
    System.out.println(don);
        ps.delete(don);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Controller aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    }

    @FXML
    private void claim(ActionEvent event) {
    }
    
    
      @FXML
    private void submit(ActionEvent event) throws SQLException {
        d.setNombre_etoiles((int) rating.getRating());
        System.out.println("Rating given by user :" + rating.getRating());
        AnnonceService sv = new AnnonceService();
        sv.rating(d);
        
        
        
    }

    @FXML
    private void post(ActionEvent event) throws MalformedURLException, IOException {
        Version apiVersion = Version.VERSION_16_0;
        
String accessToken = "EAADiVf1eruMBAHFXDAO77s5nSGovczjA473BWQyqziF05bPZCsZCqRSQQq6OStc8dfKMmubFHLZBZAeQiz7HH4BpFg7PDeZBGiv9xtGKhRkDnzOaETbmQZAYHdtTFJDhadeGZCMnTL59r7WxsgA8ZAqIFbsn652c2344LheZCm7WkAlwjqLcuZCbUY3UmjeQiTwnAQ8tDnj7sJTfHYO7IgxPMJ";
 FacebookClient fbclient = new DefaultFacebookClient(accessToken,apiVersion);
FacebookType response = fbclient.publish("me/feed",FacebookType.class,Parameter.with("message","text"));
        System.out.println("fb.com/"+response.getId());
    } 
    
    
}
