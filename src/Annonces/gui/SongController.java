package Annonces.gui;

import Annonces.entities.Annonces;
//import service.AddressToLatLng;
//import static Service.AddressToLatLng.getLatLongFromAddress;
import Annonces.services.AnnonceService;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import Annonces.gui.updateAnnonceController;
import Annonces.gui.SampleController;
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
import Annonces.MyBD.DataBase;
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
    public static Boolean test = false;
    
  
    @FXML
    private Button Details;
    @FXML
    private Label moyenneLabel;
    

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

@FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
    System.out.println(don);
        ps.delete(don);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Controller aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    }

    
    
   public void receiveObject(Annonces d) throws SQLException {
        this.d=d;
        don=d;
        
        AnnonceService ps = new AnnonceService();
                float moyenne=0 ;
          moyenne =moyenne+ ps.getEtoile(d.getId(),"etoile_1")+ ps.getEtoile(d.getId(),"etoile_2")+ ps.getEtoile(d.getId(),"etoile_3")+ ps.getEtoile(d.getId(),"etoile_4")+ ps.getEtoile(d.getId(),"etoile_5");
               moyenne = moyenne/5 ;
               moyenneLabel.setText(Float.toString(moyenne));
             
               if(ps.checkRated(projetpidd.ProjetPiDD.user.getId(),d.getId() ))
               {
                 BtnUpdate.setVisible(true);
    
                     DeleteBtn.setVisible(true);
               }
               else
               {
                   BtnUpdate.setVisible(false);
    
                     DeleteBtn.setVisible(false);
               }
    } 

    @FXML
    private void post(ActionEvent event) throws MalformedURLException, IOException {
        Version apiVersion = Version.VERSION_16_0;
        
String accessToken = "EAADiVf1eruMBABkMpja05DF6uxfyBCS9K42M2gZACHJMNZAnxEYqqydye1pdsMvJXgTHtTPb9pIIZAoQayMXj16jAhyH93iYD8mMg54ZCNpw3KNoqPXmCbwA0t48gIpEJ1XhgqZBpOIkLcegMSoBDasHPMonb8zLkTlJhgTYXPAK0YdECCeFit0zkRPkKdngeUiKmbtvVjkeIeV8Gplbu";
 FacebookClient fbclient = new DefaultFacebookClient(accessToken,apiVersion);
//FacebookType response = fbclient.publish("me/feed",FacebookType.class,Parameter.with("message","text"));
String imagePath = d.getImage().substring("file:\\".length());
File image2 = new File(imagePath);
FacebookType response = fbclient.publish("me/photos", FacebookType.class,
        BinaryAttachment.with("intel", new FileInputStream(image2)),
        Parameter.with("message", d.getDescription()));

        System.out.println("fb.com/"+response.getId());
    } 
    
    @FXML
    private void Details(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
 Parent root = loader.load();
        Details.getScene().setRoot(root);
    DetailsController controller = loader.getController();
    controller.receiveObject(d);
   
    }
}
