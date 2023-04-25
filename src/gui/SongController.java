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
import com.restfb.BinaryAttachment;
import java.io.File;
import java.io.FileInputStream;
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
        
String accessToken = "EAADiVf1eruMBAJNrB3ZBXRP2ISWcDtqAXyC2BZBk8qxUixIwuJjaaoayxnaMXzW1nGc3FUTLseNTZAha9dfC3xYZBPPq1a95sgUk0TF8cJ9sj4Do9qW16gKTpY6stMI07Hww9tUVOl910KC1vym9A268QZBBxidDc1cKsmWKOq22FmgpbfhVZBYMk5HSHQZAg8KRVQUsNRhb2AYVKPawRMq";
 FacebookClient fbclient = new DefaultFacebookClient(accessToken,apiVersion);
//FacebookType response = fbclient.publish("me/feed",FacebookType.class,Parameter.with("message","text"));
String imagePath = d.getImage().substring("file:\\".length());
File image2 = new File(imagePath);
FacebookType response = fbclient.publish("me/photos", FacebookType.class,
        BinaryAttachment.with("intel", new FileInputStream(image2)),
        Parameter.with("message", d.getDescription()));

        System.out.println("fb.com/"+response.getId());
    } 
    
    
}
