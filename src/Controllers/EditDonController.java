/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Don;
import Services.ServiceDon;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class EditDonController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tflocalisation;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnumero;
    @FXML
    private TableView<Don> tvdon;
    @FXML
    private TableColumn<Don, Integer> tvname;
    @FXML
    private TableColumn<Don, String> tvquantite;
    @FXML
    private TableColumn<Don, String> tvdescription;
    @FXML
    private TableColumn<Don, String> tvlocalisation;
    @FXML
    private TableColumn<Don, String> tvemail;
    @FXML
    private TableColumn<Don, Integer> tvnumero;
    @FXML
    private Button ImageAddChoose;
    @FXML
    private ImageView eventAddImg;
    @FXML
    private ImageView eventAddImg1;
    @FXML
    private ImageView eventAddImg11;
    @FXML
    private TextField filtertext;
    @FXML
    private Button FrontBtn;
    ServiceDon ps = new ServiceDon();
    private File file; 
    private String lien="";
     private ImageView ImageRead;
  private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     * Initializes the controller class.
     */
    public static final String ACCOUNT_SID = "AC904d482ced22b4c1943dfa6f347bc92b";
  public static final String AUTH_TOKEN = "b9fba2dc9693993d02d1a8bdc9b65f83";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
    }    
public void afficher(){
       try {
            // TODO
            
            List<Don> personnes = ps.afficher();
           

            ObservableList<Don> olp = FXCollections.observableArrayList(personnes);
            tvdon.setItems(olp);
            
            tvname.setCellValueFactory(new PropertyValueFactory("NameD"));
            tvquantite.setCellValueFactory(new PropertyValueFactory("quantite"));
            tvdescription.setCellValueFactory(new PropertyValueFactory("description"));
             tvlocalisation.setCellValueFactory(new PropertyValueFactory("localisation"));
             tvemail.setCellValueFactory(new PropertyValueFactory("email"));
              tvnumero.setCellValueFactory(new PropertyValueFactory("numero"));
              FilteredList<Don> filter = new FilteredList<>(olp, b->true);
        filtertext.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getNameD().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (event.getLocalisation().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else 
            return false;
        });
        });
        SortedList<Don> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tvdon.comparatorProperty());
        
        tvdon.setItems(sort);
/*FilteredList<Don> filteredData = new FilteredList<Don>(olp, b -> true);
filtertext.textProperty().addListener((observable,oldValue,newValue)->{
filteredData.setPredicate(Don->{
if(newValue.isEmpty()||newValue== null){return true;}
String filtertext = newValue.toLowerCase();
if(Don.getNameD().toLowerCase().indexOf(filtertext))
})
});*/

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
       
    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
        Don don= tvdon.getSelectionModel().getSelectedItem();
       id=don.getId();
        tfname.setText(don.getNameD()+"");
        tfquantite.setText(Integer.toString(don.getQuantite()));
        tfdescription.setText(don.getDescription());
        tflocalisation.setText(don.getLocalisation());
        tfemail.setText(don.getEmail());
        tfnumero.setText(Integer.toString(don.getNumero()));
   eventAddImg.setImage(new Image("file:src\\uploads\\"+don.getImage()+".png"));
    }

    @FXML
    private void AjouterDon(ActionEvent event) {
         ServiceDon se = new ServiceDon();
            
            Don p = new Don();
            p.setNameD(tfname.getText());
            p.setQuantite(Integer.valueOf(tfquantite.getText()));
            p.setImage(lien);
                
         p.setDescription(tfdescription.getText());
                 p.setLocalisation(tflocalisation.getText());
                 p.setEmail(tfemail.getText());
                 p.setNumero(Integer.valueOf(tfnumero.getText()));


          
            se.ajouter(p);
           
            JOptionPane.showMessageDialog(null, "Don ajouté !");
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21629228940"),
        new PhoneNumber("+15673717088"), 
        "a New Don has been added").create();
            afficher();
           
    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException {
         ServiceDon se = new ServiceDon();
             Don p = new Don();
            p.setNameD(tfname.getText());
            p.setQuantite(Integer.valueOf(tfquantite.getText()));
            p.setImage(lien);
         p.setDescription(tfdescription.getText());
                 p.setLocalisation(tflocalisation.getText());
                 p.setEmail(tfemail.getText());
                 p.setNumero(Integer.valueOf(tfnumero.getText()));
p.setId(id);

          
            se.modifier(p);

            JOptionPane.showMessageDialog(null, "Don modifié !");
             afficher();
           

    }

    @FXML
    private void deleteEvent(MouseEvent event) throws SQLException {
        
        Don don= tvdon.getSelectionModel().getSelectedItem();
       
ps.supprimer(don);
        afficher();
 
    }

    @FXML
    private void UploadImageActionPerformed(ActionEvent event) {
        /*
         FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            eventAddImg.setImage(imagee);
             PauseTransition pause = new PauseTransition(Duration.seconds(8));
        
        // set the action to be performed when the pause is finished
        pause.setOnFinished(Event -> {
            // your code to be executed after 5 seconds
eventAddImg.setImage(null);
        System.out.println("8 seconds have passed");
        });
        
        // start the pause
        pause.play();
        
        // your code to be executed immediately
        System.out.println("Waiting for 8 seconds...");
    
            eventAddImg.setFitWidth(200);
            eventAddImg.setFitHeight(200);
            eventAddImg.scaleXProperty();
            eventAddImg.scaleYProperty();
            eventAddImg.setSmooth(true);
            eventAddImg.setCache(true);                           

        try {
            // save image to PNG file
            this.lien=UUID.randomUUID().toString();
            File f=new File("src\\uploads\\" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
                       
        } catch (IOException ex) {
            Logger.getLogger(EditDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
*/
    }

    @FXML
    private void Map(ActionEvent event) {
    }

    @FXML
    private void Empty(ActionEvent event) {
        id=0;
        tfname.setText("");
        tfquantite.setText("");
        tfdescription.setText("");
        tflocalisation.setText("");
        tfemail.setText("");
        tfnumero.setText("");
   eventAddImg.setImage(null); 
        
        
    }

    @FXML
    private void Chart(ActionEvent event) {
    }

    @FXML
    private void Front(ActionEvent event) {
    }
    
}
